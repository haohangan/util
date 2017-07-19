package simple.http;

public class Cal {//http://blog.csdn.net/yl2isoft/article/details/16367901
	static double EARTH_RADIUS = 6378.137;// 地球半径 公里

	static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);

		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
//		s = Math.round(s * 10000) / 10000;
		return s;
	}
	
	static double GetDistance(String loc1,String loc2){
		String[] arr1 = loc1.split(",");
		String[] arr2 = loc2.split(",");
		double lat1 = Double.parseDouble(arr1[0]);
		double lng1 = Double.parseDouble(arr1[1]);
		double lat2 = Double.parseDouble(arr2[0]);
		double lng2 = Double.parseDouble(arr2[1]);
		return GetDistance(lat1,lng1,lat2,lng2);
	}
	
	public static void main(String[] args) {
		double distance = GetDistance("120.227735,30.269100","120.211397,30.339878");
		System.out.println(distance);
	}
}
