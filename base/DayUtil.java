package dc.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月16日 下午2:19:42 类说明
 */
public class DayUtil {

	public static List<String> getDateRange(Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> list = new ArrayList<String>();
		String startStr = sdf.format(start);
		if (start.equals(end)) {
			list.add(startStr);
			return list;
		}
		String endStr = sdf.format(end);
		int startDay = Integer.parseInt(startStr.substring(
				startStr.lastIndexOf("-") + 1, startStr.length()));
		int endDay = Integer.parseInt(endStr.substring(
				endStr.lastIndexOf("-") + 1, endStr.length()));
		String prefix = startStr.substring(0, startStr.lastIndexOf("-"));
		for (int i = startDay; i <= endDay; i++) {
			String dateStr = null;
			if (i < 10) {
				dateStr = "-0" + i;
			} else {
				dateStr = "-" + i;
			}
			list.add(prefix + dateStr);
		}
		return list;
	}

	public static Date getFirstDay(String year_month) throws ParseException {
		String format = year_month + "-01 00:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.parse(format);
	}

	public static Date getLastDay(String year_month) throws ParseException {
		String[] arr = year_month.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(arr[0]));
		calendar.set(Calendar.MONTH, Integer.parseInt(arr[1]) - 1);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		String format = year_month + "-" + lastDay + " 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.parse(format);
	}

	public static String getLastDayStr(String year_month) throws ParseException {
		String[] arr = year_month.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(arr[0]));
		calendar.set(Calendar.MONTH, Integer.parseInt(arr[1]) - 1);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		String format = year_month + "-" + lastDay + " 23:59:59";
		return format;
	}

	public static String getFirstDayStr(String year_month)
			throws ParseException {
		String format = year_month + "-01 00:00:00";
		return format;
	}

	public static void main(String[] args) throws ParseException {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// List<String> list = getDateRange(sdf.parse("2015-02-02"),
		// sdf.parse("2015-02-28"));
		// for (String d : list) {
		// System.out.println(d);
		// }
		System.out.println(getFirstDay("2015-03"));
		System.out.println(getLastDayStr("2015-02"));
	}
}
