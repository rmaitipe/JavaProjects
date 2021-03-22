package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeStampConversionUtil {

	Logger logger = LoggerFactory.getLogger(TimeStampConversionUtil.class);
	private String DATE_FORMAT_TZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	public static void main(String args []){
		String dateString ="2020-01-01T07:00:00.000Z";
		TimeStampConversionUtil util=new TimeStampConversionUtil();
		Date  dateVal = util.convertStringToDate(dateString);
		LocalDate localVal = util.convertDateToLocalDate(dateVal);
	}
	
	public Date convertStringToDate(String dateString){
		Date retVal =null;
		SimpleDateFormat parser = new SimpleDateFormat(DATE_FORMAT_TZ);
		try {
			retVal = parser.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		logger.info(retVal.toString());
		return retVal;
	}
	
	public LocalDate convertDateToLocalDate(Date date){
		Instant instant = Instant.parse(new SimpleDateFormat(DATE_FORMAT_TZ,Locale.getDefault()).format(date));
		LocalDate localDate =instant.atZone(ZoneId.of("UTC")).toLocalDate();
		logger.info(localDate.toString());
		return localDate;
		
	}
	
	public LocalDate convertCalendarToLocalDate(){
		Calendar calendar= Calendar.getInstance();
		LocalDate localDate =LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
		return localDate;
	}
			
}
