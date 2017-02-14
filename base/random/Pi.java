package jt.random;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Pi {

	/**
	 * ���ؿ��巨��PI
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		List<Point> list = new ArrayList<>();
		for (int i = 0; i < 1000000; i++) {
			list.add(createPoint());
		}

		try {
			double pi = calPi(list);
			System.out.println("ours:" + pi);
			System.out.println("maths:" + Math.PI);
			// ours:3.14016
			// maths:3.141592653589793    ǰ��λ���ֺ��ȣ�3.14
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static double calPi(List<Point> list) throws Exception {
		if (list == null || list.isEmpty()) {
			throw new Exception("�����飬�޷�����PI");
		}
		int count = 0;
		for (Point p : list) {
			if (p.isInCircle()) {
				count++;
			}
		}
		return (count * 4.0) / list.size();
	}

	static SecureRandom sr = new SecureRandom();//"1234567890qwertyuiopasdfghjklzxcvbnm".getBytes()
	// ����һ���������

	public static double random() {
		return sr.nextDouble();
	}

	public static Point createPoint() {
		double x = random();
		double y = random();
		return new Point(x, y);
	}

	static class Point {
		double x;
		double y;

		public Point(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

		public boolean isInCircle() {
			double z = x * x + y * y;
			if (z <= 1.0D) {
				return true;
			}
			return false;
		}
	}
}
