import datetime as dt

import numpy as np
import polars as pl


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


def main():
    df = gen_flow()
    acc_ct_df = cal_ct(df, dt.date(2025, 4, 22))
    df_print(acc_ct_df.filter(pl.col("product") == 'Apple'))


if __name__ == '__main__':
    main()
