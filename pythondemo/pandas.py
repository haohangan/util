import pandas as pd


def main():
    flow_df = pd.read_excel("process_ct.ods", engine="odf")
    # 每个product内按照flow_name排序
    flow_df = flow_df.sort_values(by=["product", "flow_name"])
    # 累加，求出cum ct
    flow_df['acc_ct'] = flow_df.groupby('product')['ct'].cumsum()
    # lag,下移一位，求出cum起始ct,并且空值补0
    flow_df['acc_start_ct'] = flow_df.groupby(["product"])["acc_ct"].shift(1).fillna(0)
    print(flow_df)


if __name__ == '__main__':
    main()
