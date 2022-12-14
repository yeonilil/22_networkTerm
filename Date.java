package network_term;

import java.util.Calendar;
import java.text.SimpleDateFormat;


public class Date {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	Calendar c1 = Calendar.getInstance();

	String strToday = sdf.format(c1.getTime());

	//return strToday;

	//System.out.println("Today=" + strToday);


	public String getDate() {
		return strToday;
	}
}
