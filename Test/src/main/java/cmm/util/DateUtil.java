package cmm.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil extends DateUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    private static TimeZone mySTZ = (TimeZone) TimeZone.getTimeZone("JST");
    private static Locale lc = new Locale("Locale.KOREAN", "Locale.KOREA");
 	public static String [] HOUR_CODE_ARRAY = {"08","09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"};
 	public static String [] HOUR_CODE_ARRAY2 = {"01", "02", "03", "04", "05", "06", "07","08","09","10", "11", "12", "13", "14", "15","16", "17", "18", "19","20","21","22","23"};
 	public static String [][] HOUR_CODE_ONE_ARRAY = {{"10","11"}, {"11", "12"},{"14", "15"}, {"15","16"}, {"16", "17"}};
 	public static String [][] HOUR_CODE_TWO_ARRAY = {{"10","12"}, {"14", "16"},{"16", "17"}};
 	public static String [][] HOUR_CODE_THREE_ARRAY = {{"","12",""}, {"14", "16"},{"16", "17"}};
  	public static String [][] HOUR_CODE_DAY_ARRAY = {{"10","17"}};
 	public static String [] MINUTE_CODE_ARRAY = {"00","10", "20", "30", "40", "50"};
 	public static String [] MINUTE_CODE_ARRAY2 = {"00","30"};
	public static String PATTERN_DATETIME_TO_STRING = "yyyy-MM-dd HH:mm:ss";
	public static String PATTERN_DATE_TO_STRING = "yyyy-MM-dd";
	public static String DEFAULT_DATE_DELIMITER = "-";
	
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	public final static String DATE_DB_FORMAT = "yyyyMMdd";
	public final static String DATEYEAR_FORMAT = "yyyy";
	public final static String DATEMONTH_FORMAT = "yyyy-MM";
	public final static String DATEMONTH_FORMAT2 = "yyyyK-MMK";
	public final static String DATEMONTH_DB_FORMAT = "yyyyMM";
	public final static String DATEMONTH_ONLY_FORMAT = "MM";
	public final static String DATEHOUR_FORMAT = "yyyy-MM-dd HH:mm";
	public final static String DATEHOUR_FORMAT2 = "yyyy-MM-dd HH";
	public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public final static String DATETIME_STRING_FORMAT = "yyyyMMddHHmmss";

	public final static String TIMEHOUR_FORMAT = "HH:mm";
	public final static String TIMEHOUR_FORMAT2 = "HHmm";
	public final static String TIMEHOUR_FORMAT3 = "HH";
	public final static String TIMESECOND_FORMAT = "HH:mm:ss";

	public final static String DELIMITER_DATE = "-";
	public final static String DELIMITER_TIME = ":";
	public final static String DELIMITER_DATETIME = " ";
	public static final String DELIMITER_PERIOD = "~";

	
	  public static int getYearCurrent() {
	    Calendar today = Calendar.getInstance(mySTZ, lc);
	    int year = today.get(Calendar.YEAR);
	
	    return year;
	  }
	  
	  public static int getMonthCurrent() {
	    Calendar today = Calendar.getInstance(mySTZ, lc);
	    int month = today.get(Calendar.MONTH) +1;
	
	    return month;
	  }
	  
	  public static int getDayIntCurrent() {
	    Calendar today = Calendar.getInstance(mySTZ, lc);
	    int day = today.get(Calendar.DAY_OF_MONTH);
	
	    return day;
	  }

	public static Date getDayCurrent() {
		Calendar today = Calendar.getInstance(mySTZ, lc);
		return new Date(today.getTimeInMillis());
	}

	@SuppressWarnings("static-access")
	public static Date getAddDayCurrent(int period) {
		Calendar today = new GregorianCalendar(mySTZ,lc);
		Calendar resultDay = new GregorianCalendar (mySTZ,lc);
		resultDay.add(today.DATE, period);
		return new Date(resultDay.getTimeInMillis());
	}

	@SuppressWarnings("static-access")
	public static Date getDayAddDay(Date date, int day) {
		if (date == null) return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.DATE, day);
		return cal.getTime();
	}

	public static String getNowString(String dtFormat) {
		Calendar today = Calendar.getInstance(mySTZ, lc);
		String szResult = "";
		if (dtFormat != null) {
			szResult = new SimpleDateFormat(dtFormat).format(today.getTime());
		}
		return szResult;
	}

	 public static String getDateString(java.util.Date dt, SimpleDateFormat dtFormat) {
	     String szResult = "";
	     if (dt != null && dtFormat != null) {
	         szResult = dtFormat.format(dt);
	     }
	     return szResult;
	 }

	public static String date2str(Date date, String format) {
		if (date == null || StringUtils.isEmpty(format)) return null;
		return new SimpleDateFormat(format).format(date);
	}

	public static Date getFirstDayOfMonth(String strMonth) {
		Calendar resultDay = new GregorianCalendar (mySTZ,lc);
		
		int intYear = Integer.parseInt(strMonth.substring(0,4));
		int intMonth = Integer.parseInt(strMonth.substring(4,6))-1;	// -1을 해야 현재월
		int intDay = 1;
		
		resultDay.set(intYear, intMonth, intDay);
		return new Date(resultDay.getTimeInMillis());
	}

	@SuppressWarnings("static-access")
	public static Date getLastDayOfMonth(String strMonth) {
		Calendar resultDay = Calendar.getInstance(mySTZ, lc);
		int intYear = Integer.parseInt(strMonth.substring(0,4));
		int intMonth = Integer.parseInt(strMonth.substring(4,6))-1;	// -1을 해야 현재월
		int intDay = 1;
		resultDay.set(intYear, intMonth, intDay);
		resultDay.add(resultDay.MONTH, +1);	// 다음달
		resultDay.add(resultDay.DATE, -1);	// 하루빼
		return new Date(resultDay.getTimeInMillis());
	}

	public static Calendar str2cal(String str, String format) {
		Calendar cal = null;
		if (StringUtils.isEmpty(str)) return cal;
		
		try {
			DateFormat formatter = new SimpleDateFormat(format); 
			Date date = (Date)formatter.parse(str); 
			
			cal = Calendar.getInstance();
			cal.setTime(date);
		} catch (NullPointerException e) {
			LOGGER.error("fail in str2cal() str="+str+", format="+format, e);
		} catch (IllegalArgumentException e) {
			LOGGER.error("fail in str2cal() str="+str+", format="+format, e);
		} catch (Exception e) {
			LOGGER.error("fail in str2cal() str="+str+", format="+format, e);
		}
		return cal;
	}

	
	
	@SuppressWarnings("static-access")
	public static Date getDayPreMonth() throws IOException, InvocationTargetException, SQLException{
		Calendar today = new GregorianCalendar(mySTZ,lc);
		Calendar resultDay = new GregorianCalendar (mySTZ,lc);
		resultDay.add(today.MONTH, -1);
		return new Date(resultDay.getTimeInMillis());
	}
	
	@SuppressWarnings("static-access")
	public static Date getPreMonth(int period) throws IOException, InvocationTargetException, SQLException {
		Calendar today = new GregorianCalendar(mySTZ,lc);
		Calendar resultDay = new GregorianCalendar (mySTZ,lc);
		resultDay.add(today.MONTH, period);
		return new Date(resultDay.getTimeInMillis());
	}

	@SuppressWarnings("static-access")
	public static Date getDayNextMonth() throws IOException, InvocationTargetException, SQLException {
		Calendar today = new GregorianCalendar(mySTZ,lc);
		Calendar resultDay = new GregorianCalendar (mySTZ,lc);
		resultDay.add(today.MONTH, 1);
		return new Date(resultDay.getTimeInMillis());
	}

	@SuppressWarnings("static-access")
	public static Date getDayAddMonth(Calendar cal, int month) throws IOException, InvocationTargetException, SQLException {
		if (cal == null) return null;
		Calendar tmp = Calendar.getInstance();
		tmp.setTime(cal.getTime());
		tmp.add(cal.MONTH, month);
		return tmp.getTime();
	}

	public static Date getDayAddMonth(int month) throws Exception {
		Calendar today = new GregorianCalendar(mySTZ,lc);
		return getDayAddMonth(today, month);
	}

	public static Date getDayAddMonth(String str, String format, int month) throws IOException, InvocationTargetException, SQLException {
		Calendar today = str2cal(str, format);
		return getDayAddMonth(today, month);
	}


	
	public static String[] getBetweenDays(String startD, String endD) throws ParseException, IOException, InvocationTargetException, SQLException {
		List<String> list = new ArrayList<>();
		
		if( StringUtils.isBlank(startD) || StringUtils.isBlank(endD)){
			return null;
		}
		startD = startD.replaceAll("-", "").trim();
		endD = endD.replaceAll("-", "").trim();
		
		if( startD.length() > 8 || endD.length() > 8) {
			return null;
		}
		
		String start = startD;
		String end = endD;
		
	    DateFormat inDf = new SimpleDateFormat("yyyyMMdd");
	    
	    //getBetweenDays("20160930" , "20161003") = {"30,금,9월,20160930", "2,일,10월,20161002" , "1,토,10월,20161001" , "3,월,10월,20161003"}
	    //DateFormat outDf = new SimpleDateFormat("d,E,MMMM,yyyyMMdd", Locale.US);
	    
	    // getBetweenDays("20160930" , "20161003") = {"20160930", "20161002" , "20161001" , "20161003"}
	    DateFormat outDf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
	    
	    Calendar startCal = Calendar.getInstance();
	    Calendar endCal = Calendar.getInstance();
	    startCal.setTime(inDf.parse(start));
	    endCal.setTime(inDf.parse(end));
	    endCal.add(Calendar.DAY_OF_MONTH, +1);
	    while (endCal.after(startCal)) {
	    	 String date = outDf.format(startCal.getTime()).toUpperCase();
	    	startCal.add(Calendar.DAY_OF_MONTH, +1);
	    	list.add(date);
	    }
	    return list.toArray(new String[list.size()]);
	}
	
	public static Date currentDate()
	{
		return new Date();
	}
	
	public static String currentDateHh()
	{
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH"); 
		return sdf.format(dt).toString();
	}
	
	public static String getAddYearFormat(int year, String format)
	{
		return dateToString(addYears(currentDate(), year), format);
	}
	
	public static String getAddMonthFormat(int month, String format)
	{
		return dateToString(addMonths(currentDate(), month), format);
	}
	
	public static String getAddDayFormat(int day, String format)
	{
		return dateToString(addDays(currentDate(), day), format);
	}
	public static String dateToString(Date date, String format)
	{
		if (date == null) {
			return "";
		}
		return DateFormatUtils.format(date, format);
	}

	public static String getAddHourFormat(int h,String format) {
		 Calendar cal = Calendar.getInstance(mySTZ, lc);
		 cal.add(Calendar.HOUR, h);	
		 return DateFormatUtils.format(cal.getTime(), format);
	}
	
	public static Date stringToDate(String str, String format) throws Exception {
		Date date = null;
		if (StringUtils.isEmpty(str) || StringUtils.isEmpty(format)) return date;
		
		Calendar cal = str2cal(str, format);
		if (cal == null) return date;
		
		date = cal.getTime();
		return date;
	}
}
