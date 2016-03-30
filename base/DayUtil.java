package ti.date;

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
		int startDay = Integer.parseInt(startStr.substring(startStr.lastIndexOf("-") + 1, startStr.length()));
		int endDay = Integer.parseInt(endStr.substring(endStr.lastIndexOf("-") + 1, endStr.length()));
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

	/**
	 * 获取当前月份的第一天
	 * 
	 * @param year_month
	 *            example:2016-02
	 * @return
	 * @throws ParseException
	 */
	public static Date getFirstDay(String year_month) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date d = sdf.parse(year_month);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取当前月的最后一天，返回日期为Date
	 * 
	 * @param year_month
	 *            example:2016-02
	 * @return
	 * @throws ParseException
	 */
	public static Date getLastDay(String year_month) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date d = sdf.parse(year_month);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 有問題
	 * 
	 * @param year_month
	 * @return
	 * @throws ParseException
	 */
	public static String getLastDayStr(String year_month) throws ParseException {
		String[] arr = year_month.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(arr[0]));
		calendar.set(Calendar.MONTH, Integer.parseInt(arr[1]) - 1);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(calendar.getTime()));
		String format = year_month + "-" + lastDay + " 23:59:59";
		return format;
	}

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// List<String> list = getDateRange(sdf.parse("2015-02-02"),
		// sdf.parse("2015-02-28"));
		// for (String d : list) {
		// System.out.println(d);
		// }
		 System.out.println(sdf.format(getLastDay("2015-02")));
		// System.out.println(getLastDayStr("2015-02"));
		// System.out.println(getLastDayStr("2015-02"));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2015);
		calendar.set(Calendar.MONTH, 2);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.out.println(sdf.format(calendar.getTime()));
	}
}
