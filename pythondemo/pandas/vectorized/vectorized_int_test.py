import pandas as pd

from common.time_util import log_time


def gen_flow(size: int):
    df = pd.DataFrame()
    df["num1"] = [idx for idx in range(1, size + 1)]
    df["num2"] = [idx for idx in range(1, size + 1)]
    return df


@log_time
def cal_vectorized(df: pd.DataFrame):
    df["ration"] = df["num1"] / df["num2"]


@log_time
def cal_non_vectorized(df: pd.DataFrame):
    def cal_ratio(row):
        return row["num1"] / row["num2"]

    df["ration"] = df.apply(cal_ratio, axis=1)


def main():
    df = gen_flow(300000)
    cal_vectorized(df)
    cal_non_vectorized(df)  # pd_print(df)


if __name__ == "__main__":
    main()
