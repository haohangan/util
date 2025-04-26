"""
测试string类型的矢量化效率
"""
import random
import string

import pandas as pd
from memory_profiler import profile

from common.df_util import pd_print
from common.time_util import log_time


def gen_email():
    domain = ["gmail.com", "yahoo.com", "hotmail.com", "outlook.com"]
    username_length = random.randint(5, 10)
    username = ''.join(random.choices(string.ascii_lowercase, k=username_length))
    domain_name = random.choice(domain)
    return username + "@" + domain_name


def gen_df(size: int):
    df = pd.DataFrame()
    df["name"] = [gen_email() for index in range(0, size)]
    # df["name"] = pd.Series(df['name'], dtype=pd.StringDtype())
    df["name"] = df["name"].astype(pd.StringDtype())
    return df


@log_time
def cal_len_vectorized(df: pd.DataFrame):
    df["len"] = df["name"].str.split("@").apply(len)


@log_time
def cal_len_apply(df: pd.DataFrame):
    def cal_len(s):
        return len(s.split("@"))

    df["len"] = df["name"].apply(cal_len)


# @profile
def main():
    df = gen_df(1000000)
    # pd_print(df)
    cal_len_vectorized(df)
    cal_len_apply(df)
    # pd_print(df)
    # print(df.dtypes)


if __name__ == "__main__":
    main()
