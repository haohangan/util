import numpy as np
import polars as pl


def df_print(df: pl.DataFrame):
    """打印Dataframe"""
    if df.is_empty():
        print("empty DataFrame")
    with pl.Config() as cfg:
        cfg.set_tbl_rows(-1)
        print(df)


def main():
    df_list = []
    product_names = ['Apple', 'Samsung', 'Kogan', 'TCL', 'Lenovo', 'OPPO', 'Realme', 'Redmi', 'Walton', 'Positivo']
    for product in product_names:
        df = pl.DataFrame()
        # 随机生成3000个step
        steps = ["step" + str(index) for index in range(1, 3001)]
        df = df.with_columns(pl.Series(name="flow_name", values=steps))
        # 随机生成ct
        df = df.with_columns(pl.lit(np.random.rand(df.height) * 2).alias("ct"))
        # 设置product 名称
        df = df.with_columns(pl.lit(product).alias("product"))
        df = df.select("product", "flow_name", "ct")
        df_list.append(df)
    # 合并到一个dataframe中
    flow_df = pl.concat(df_list)
    df_print(flow_df)


if __name__ == '__main__':
    main()
