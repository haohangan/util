import pandas as pd
import polars as pl


def pl_print(df: pl.DataFrame):
    """打印Dataframe"""
    if df.is_empty():
        print("empty DataFrame")
    with pl.Config() as cfg:
        cfg.set_fmt_float("full")
        cfg.set_tbl_rows(-1)
        cfg.set_tbl_cols(-1)
        cfg.set_tbl_width_chars(-1)
        print(df)


def pd_print(df: pd.DataFrame):
    """打印Dataframe"""
    if df.empty:
        print("empty DataFrame")
    with pd.option_context('display.max_rows', None, 'display.max_columns', None):
        print(df)
