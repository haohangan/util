import datetime as dt
import multiprocessing
import time
from asyncio.coroutines import iscoroutinefunction

import numpy as np
import polars as pl


def log_time(func):
    """打印方法执行时间"""

    def fun(*args, **kwargs):
        t = time.perf_counter()
        result = func(*args, **kwargs)
        print(f'func {func.__name__} cost time:{time.perf_counter() - t:.8f} s')
        return result

    async def fun_async(*args, **kwargs):
        t = time.perf_counter()
        result = await func(*args, **kwargs)
        print(f'func {func.__name__} cost time:{time.perf_counter() - t:.8f} s')
        return result

    if iscoroutinefunction(func):
        return fun_async
    return fun


def df_print(df: pl.DataFrame):
    """打印Dataframe"""
    if df.is_empty():
        print("empty DataFrame")
    with pl.Config() as cfg:
        cfg.set_fmt_float("full")
        cfg.set_tbl_rows(-1)
        cfg.set_tbl_cols(-1)
        cfg.set_tbl_width_chars(-1)
        print(df)


@log_time
def gen_flow():
    """生成flow"""
    df_list = []
    product_names = ['Apple', 'Samsung', 'Kogan', 'TCL', 'Lenovo', 'OPPO', 'Realme', 'Redmi', 'Walton', 'Positivo']
    for product in product_names:
        df = pl.DataFrame()
        # 随机生成3000个step
        steps = ["step_" + str(index).zfill(4) for index in range(1, 3001)]
        df = df.with_columns(pl.Series(name="flow_name", values=steps))
        # 随机生成ct
        df = df.with_columns(pl.lit(np.random.rand(df.height) * 0.1).alias("ct"))
        # 设置product 名称
        df = df.with_columns(pl.lit(product).alias("product"))
        # 调整列顺序
        df = df.select("product", "flow_name", "ct")
        df_list.append(df)
    # 合并到一个dataframe中
    return pl.concat(df_list)


# @log_time
def cal_ct(df: pl.DataFrame, start_day: dt.date) -> pl.DataFrame:
    # 每个product内按照flow_name排序，并累加求出cum_ct
    cum_df = df.with_columns(acc_ct=pl.col('ct').cum_sum().over('product', order_by='flow_name')).sort(
        ["product", "flow_name"])
    # lag,下移一位，求出cum起始ct,并且空值补0
    cum_df = cum_df.with_columns(pre_acc_ct=pl.col('acc_ct').shift(1, fill_value=0).over('product'))
    # 根据两个ct时间，计算开始日期和结束日期
    cum_df = cum_df.with_columns(load_day=pl.lit(start_day))
    cum_df = cum_df.with_columns(
        start_day=pl.col('load_day').dt.offset_by(pl.col("pre_acc_ct").floor().cast(pl.Int32).cast(pl.String) + 'd'),
        end_day=pl.col('load_day').dt.offset_by(pl.col("acc_ct").floor().cast(pl.Int32).cast(pl.String) + 'd'))
    # 调整列顺序
    return cum_df.select(['product', 'flow_name', 'ct', 'pre_acc_ct', 'acc_ct', 'load_day', 'start_day', 'end_day'])


@log_time
def cal_acc_ct_sync(start_date: dt.date, period: int):
    """计算一年的step"""
    res = []
    df = gen_flow()
    for num in range(period):
        load_date = start_date + dt.timedelta(days=num)
        acc_ct_df = cal_ct(df, load_date)
        res.append(acc_ct_df)
    return pl.concat(res)


@log_time
def cal_acc_ct_async(start_date: dt.date, period: int):
    """计算一年的step"""
    results = []
    df = gen_flow()
    params = [(df, start_date + dt.timedelta(days=num)) for num in range(period)]
    with multiprocessing.Pool() as pool:
        results = pool.starmap(cal_ct, params)
    return pl.concat(results)


def main():
    start_day = dt.date(2025, 1, 1)
    days = 400
    # flow_df = cal_acc_ct_sync(start_day, days) # 6秒
    flow_df = cal_acc_ct_async(start_day, days)  # 9秒
    print(flow_df.shape)


if __name__ == '__main__':
    main()
