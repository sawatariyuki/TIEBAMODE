package tieba.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class MAIN {
	public static void main(String[] args) {
		Timestamp t = new Timestamp(new Date().getTime());
		int y = TimeUtils.getAgeFromBirthday(t);
		System.out.println(y);
		
	}
}
