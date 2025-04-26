import numpy as np
import pandas as pd
import polars as pl

from common.time_util import log_time


@log_time
def pl_gen_flow() -> pl.DataFrame:
    df_list = []
    product_names = ['Apple', 'Samsung', 'Kogan', 'TCL', 'Lenovo', 'OPPO', 'Realme', 'Redmi', 'Walton', 'Positivo']
    for product in product_names:
        df = pl.DataFrame()
        # 随机生成3000个step
        steps = ["step_" + str(index).zfill(4) for index in range(1, 3001)]
        df = df.with_columns(pl.Series(name="flow_name", values=steps))
        # 随机生成ct
        df = df.with_columns(pl.lit(np.random.rand(df.height) * 2).alias("ct"))
        # 设置product 名称
        df = df.with_columns(pl.lit(product).alias("product"))
        # 调整列顺序
        df = df.select("product", "flow_name", "ct")
        df_list.append(df)
    # 合并到一个dataframe中
    return pl.concat(df_list)


@log_time
def pd_gen_flow(size: int) -> pd.DataFrame:
    df_list = []
    product_names = ['Apple', 'Samsung', 'Kogan', 'TCL', 'Lenovo', 'OPPO', 'Realme', 'Redmi', 'Walton', 'Positivo']
    for product in product_names:
        df = pd.DataFrame()
        steps = ["step_" + str(index).zfill(4) for index in range(1, size + 1)]
        product_names = [product] * size
        df['product'] = product_names
        df['flow_name'] = steps
        df['ct'] = np.random.random(size) * 2
        df_list.append(df)
    # 合并到一个dataframe中
    return pd.concat(df_list)
