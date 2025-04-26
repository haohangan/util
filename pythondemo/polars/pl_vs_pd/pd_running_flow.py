import datetime as dt

import pandas as pd

import gen_flow
from common.time_util import log_time


@log_time
def cal_ct(df: pd.DataFrame, start_day: dt.date) -> pd.DataFrame:
    # 每个product内按照flow_name排序，并累加求出cum_ct
    df = df.sort_values(by=["product", "flow_name"])
    df["acc_ct"] = df.groupby(by=["product"])["ct"].cumsum()
    # lag,下移一位，求出cum起始ct,并且空值补0
    df["pre_acc_ct"] = df.groupby(["product"])["acc_ct"].shift(1).fillna(0)
    # 根据两个ct时间，计算开始日期和结束日期
    df["load_day"] = start_day
    df["load_day"] = pd.to_datetime(df["load_day"])
    df["start_day"] = df["load_day"] + pd.to_timedelta(df["pre_acc_ct"].astype(int), unit='D')
    df["end_day"] = df["load_day"] + pd.to_timedelta(df["acc_ct"].astype(int), unit='D')
    # 调整列顺序
    return df[['product', 'flow_name', 'ct', 'pre_acc_ct', 'acc_ct', 'load_day', 'start_day', 'end_day']]


@log_time
def cal_acc_ct(start_date: dt.date, period: int):
    """计算一年的step"""
    res = []
    df = gen_flow.pd_gen_flow(3000)
    for num in range(period):
        load_date = start_date + dt.timedelta(days=num)
        acc_ct_df = cal_ct(df, load_date)
        # pd_print(acc_ct_df)
        # print(acc_ct_df.dtypes)
        res.append(acc_ct_df)
    return pd.concat(res)


def main():
    start_day = dt.date(2025, 1, 1)
    days = 400
    flow_df = cal_acc_ct(start_day, days)  # 6秒
    print(flow_df.shape)  # flow_df.to_csv("res.csv")


if __name__ == '__main__':
    main()
