package tieba.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	/***
	 * 判断两个Timestamp类型的时间是否为同一天
	 * @param time1	时间1
	 * @param time2	时间2
	 * @return true 是同一天
	 */
	public static boolean isTheSameDate(Timestamp time1, Timestamp time2) {
		if(time1 != null && time2 != null) {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(time1);
			int y1 = c1.get(Calendar.YEAR);
			int m1 = c1.get(Calendar.MONTH);
			int d1 = c1.get(Calendar.DATE);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(time2);
			int y2 = c2.get(Calendar.YEAR);
			int m2 = c2.get(Calendar.MONTH);
			int d2 = c2.get(Calendar.DATE);
			if (y1 == y2 && m1 == m2 && d1 == d2) {
				return true;
			}
		} else {
			if (time1 == null && time2 == null) {
				return true;
			}
		}
		return false;
	}
	
	public static Integer getAgeFromBirthday(Timestamp timestamp){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(timestamp);
		int y1 = c1.get(Calendar.YEAR);
		Calendar c2 = Calendar.getInstance();
		Timestamp now = new Timestamp(new Date().getTime());
		c2.setTime(now);
		int y2 = c2.get(Calendar.YEAR);
		return y2-y1;
	}
}
