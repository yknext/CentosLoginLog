package microserver.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
	public static Map<String,Integer> MONTH = new HashMap<>();
	static {
		MONTH.put("Jan", 1);
		MONTH.put("Feb", 2);
		MONTH.put("Mar", 3);
		MONTH.put("Apr", 4);
		MONTH.put("May", 5);
		MONTH.put("Jun", 6);
		MONTH.put("Jul", 7);
		MONTH.put("Aug", 8);
		MONTH.put("Sep", 9);
		MONTH.put("Oct", 10);
		MONTH.put("Nov", 11);
		MONTH.put("Dec", 12);
	}
	
	public static SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 时间转换
	 * @param month Jan
	 * @param day 1
	 * @param time 19:19:47
	 * @return date
	 */
	public static Date DateForamt(String month,String day,String time)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, MONTH.get(month)-1);
		calendar.set(Calendar.DAY_OF_MONTH,Integer.valueOf(day));
		String yyyyMMdd = simple.format(calendar.getTime());
		try {
			return simpleDate.parse(yyyyMMdd+" "+time);
		} catch (ParseException e) {
			return new Date();
		}
	}
	
}
