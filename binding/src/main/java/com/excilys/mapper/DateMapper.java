package com.excilys.mapper;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateMapper {
	
	public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
	
	public static LocalDate sqlDateToLocalDate(Date date) {
		LocalDate result;
		if(date == null) {
			result = null;
		}else {
			result = LocalDate.parse(date.toString(),format);
		}
		return result;
		
	}
	
	public static LocalDate stringToLocalDate(String date) {
		LocalDate result;
		if(date == null) {
			result = null;
		}else if(date.equals("null") || date.equals("")) {
			result = null;
		}else {
			result = LocalDate.parse(date,format);
		}
		return result;
	}

	
	public static String LocalDateToString(LocalDate date) {
		String result;
		if(date == null) {
			result = null;
		}else{
			result = date.toString();
		}
		
		return result;
	}
	
	public static String sqlDateToString(Date date) {
		String result;
		if(date == null) {
			result = null;
		}else{
			result = date.toString();
		}
		
		return result;
	}
	
	public static Date localDateTosqlDate(LocalDate date) {
		Date result;
		if(date == null) {
			result = null;
		}else{
			result = Date.valueOf(date.toString());
		}
		
		return result;
	}
}
