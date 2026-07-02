package com.wu.billersplus.connector.cashin.pago.efectivo.online.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

	public final static String yyyyMMdd = "yyyyMMdd";
	public final static String HHmmss = "HHmmss";
	public final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public final static String yyyyMMddSL = "yyyy/MM/dd";
	public final static String yyMMddHHmmss = "yyMMddHHmmss";
	public final static String JULIAN_DATE = "yyDDD";


	public static Date getDate(String dateString, String format) {
		SimpleDateFormat formatter = null;
		Date date = null;
		if(dateString != null && !dateString.isEmpty()){
			try {
	        	formatter = new SimpleDateFormat(format);
	            date = formatter.parse(dateString);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		}
        return date;
	}

	public static String getDate(Date date, String format){
		String dateReturn = null;
		SimpleDateFormat simpleDateFormat = null;
		if(date != null){
			simpleDateFormat = new SimpleDateFormat(format);
			dateReturn = simpleDateFormat.format(date);
		}
		return dateReturn;
	}

	public static String getJulianDate(Date date) {
		String result = null;
		SimpleDateFormat f = null;
		Date today = new Date();

        f = new SimpleDateFormat(JULIAN_DATE);
        if (date != null)
        	result = f.format(date);
        else
        	result = f.format(today);
        return result;
	}

	public static DateTime getDataTimeZone(DateTime data, String timeZone) {
		if (timeZone != null)
			return data.toDateTime(DateTimeZone.forID(timeZone));
		else
			return data;
	}

	public static String getDate(DateTime data, String format) {
		DateTimeFormatter hora = DateTimeFormat.forPattern(format);
		return hora.print(data);
	}

	public static Date addSecond(Date date, String second){
		Calendar calendar = null;

		calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, Integer.parseInt(second));
		return calendar.getTime();
	}
}
