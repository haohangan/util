package com.eva.core.string;

public class SplitString {
	public static void main(String[] args) {
		double money = 100.00D;// 收入
		Double serviceCharge = 0.00D;
		String str1 = "50.00D,80.00D,90.00D";
		String str2 = "0.07D,0.05D,0.03D,0.01D";

		String[] str1Str = str1.split(",");
		String[] str2Str = str2.split(",");
		int r1 = str1Str.length;
		int r2 = str2Str.length;
		double[] str1Dou = new double[r1];
		double[] str2Dou = new double[r2];

		for (int i = 0; i < r1; i++) {
			str1Dou[i] = Double.parseDouble(str1Str[i]);
		}

		for (int i = 0; i < r2; i++) {
			str2Dou[i] = Double.parseDouble(str2Str[i]);
		}

		try {
			serviceCharge = getChargeFee(money, str1Dou, str2Dou);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("费用为：" + serviceCharge);
	}

	public static Double getChargeFee(double money, double[] ranges,
			double[] scales) throws Exception {
		if (ranges.length != scales.length - 1) {
			throw new Exception("错误的参数！");
		}
		Double fee = null;
		int times = ranges.length;
		for (int i = 0; i < times; i++) {
			if (i == 0) {// i==0的情况
				if (money < 0) {
					break;// 跳出循环
				} else {
					if (money <= ranges[0]) {
						fee = (money - 0) * scales[0];
						break;// 跳出循环
					} else {
						fee = (ranges[0] - 0) * scales[0];
					}
				}
			} else if (i == times - 1) {

				if (money <= ranges[i]) {
					fee = fee + (money - ranges[i]) * scales[i];
					break;// 跳出循环
				} else {
					fee = fee + (ranges[i] - ranges[i - 1]) * scales[i];
					fee = fee + (money - ranges[i]) * scales[i + 1];
				}

			} else {
				if (money <= ranges[i]) {
					fee = fee + (money - ranges[i]) * scales[i];
					break;// 跳出循环
				} else {
					fee = fee + (ranges[i] - ranges[i - 1]) * scales[i];
				}
			}
		}
		return fee;
	}

}
