import polars as pl


def df_print(df: pl.DataFrame):
    """打印Dataframe"""
    if df.is_empty():
        print("empty DataFrame")
    with pl.Config() as cfg:
        cfg.set_tbl_rows(-1)
        print(df)


def main():
    df = pl.read_ods(source="process_ct.ods", sheet_name="Sheet1")
    # 每个product内按照flow_name排序，并累加求出cum_ct
    cum_df = df.with_columns(acc_ct=pl.col('ct').cum_sum().over('product', order_by='flow_name')).sort(
        ["product", "flow_name"])
    # lag,下移一位，求出cum起始ct,并且空值补0
    cum_df = cum_df.with_columns(pre_acc_ct=pl.col('acc_ct').shift(1, fill_value=0).over('product'))
    # 调整列顺序
    cum_df = cum_df.select(['product', 'flow_name', 'ct', 'pre_acc_ct', 'acc_ct'])
    df_print(cum_df)


if __name__ == "__main__":
    main()
