import datetime as dt

import polars as pl

import gen_flow
from common.time_util import log_time


@log_time
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
def cal_acc_ct(start_date: dt.date, period: int):
    """计算一年的step"""
    res = []
    df = gen_flow.pl_gen_flow()
    for num in range(period):
        load_date = start_date + dt.timedelta(days=num)
        acc_ct_df = cal_ct(df, load_date)
        res.append(acc_ct_df)
    return pl.concat(res)


def main():
    start_day = dt.date(2025, 1, 1)
    days = 400
    flow_df = cal_acc_ct(start_day, days)  # 6秒
    print(flow_df.shape)


if __name__ == '__main__':
    main()
