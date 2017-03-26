package com.chinasofti.guguanjia.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间工具类
 */
public class DateUtils {
	/**
	 * 得到当然时间"yyyy-MM-dd "
	 * @return
	 */
	public static Date getCurrDate() {

		Date currentTime = new Date();
		String currentTimeStr=dateToString(currentTime);
		return string2Date(currentTimeStr);

	}
	/**
	 * 得到当然时间"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String getCurrDateStr() {

		Date currentTime = new Date();

		return dateToString(currentTime);

	}

	/**
	 * "yyyyMMddHHmmss "转为时间类型
	 * @param sdate
	 * @return
	 */
	public static Date getCurrDate(String sdate) {		
		SimpleDateFormat dateFormater = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		try {
			return dateFormater.parse(sdate);
		} catch (Exception e) {
			return null;
		}

	}
	/**
	 * 时间转换为"yyyy-MM-dd HH:mm:ss"
	 * @param sdate
	 * @return
	 */
	public static String dateToString(Date sdate) {
		SimpleDateFormat dateFormater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			return dateFormater.format(sdate);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * "yyyy-MM-dd HH:mm:ss"转换为时间
	 * @param sdate
	 * @return
	 */
	public static Date stringToDate(String sdate) {
		SimpleDateFormat dateFormater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			return dateFormater.parse(sdate);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * "yyyy-MM-dd"转换为时间
	 * @param sdate
	 * @return
	 */
	public static Date string2Date(String sdate) {
		SimpleDateFormat dateFormater = new SimpleDateFormat(
				"yyyy-MM-dd");
		try {
			return dateFormater.parse(sdate);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 时间转换为"yyyy-MM-dd"
	 * @param sdate
	 * @return
	 */
	public static String date2String(Date sdate) {
		SimpleDateFormat dateFormater = new SimpleDateFormat(
				"yyyy-MM-dd");
		try {
			return dateFormater.format(sdate);
		} catch (Exception e) {
			return null;
		}
	}
	public static String dateTime2String(long stime){
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
		return format.format(stime);
	}
	public static String dateTimeToString(long stime){
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		return format.format(stime);
	}
	/**
	 * 字符串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	/**
	 * 判断时间差,以天为单位
	 * @param sdate
	 * @param edate
	 * @return
	 */
	public static long dayDiffer(Date sdate,Date edate){
		long dateDiffer=(edate.getTime()-sdate.getTime())/1000;		
		return dateDiffer/60/60/24+1;
	}
	/**
	 * 判断时间差,以月为单位
	 * @param sdate
	 * @param edate
	 * @return
	 */
	public static long mouthDiffer(Date sdate,Date edate){
		long dateDiffer=(edate.getTime()-sdate.getTime())/1000;		
		return dateDiffer/60/60/24/30;
	}
	/**
	 * 判断时间差,以年为单位
	 * @param sdate
	 * @param edate
	 * @return
	 */
	public static long yearDiffer(Date sdate,Date edate){
		long dateDiffer=(edate.getTime()-sdate.getTime())/1000;		
		return dateDiffer/60/60/24/30/12;
	}
	
	/**
	 * 增加n个月
	 * @param sdate
	 * @return
	 */
	public static Date addMouth(Date sdate,int mouthNum){	
		Calendar cd = Calendar.getInstance();
		cd.setTime(sdate);
		cd.add(Calendar.MONTH, mouthNum);
		return cd.getTime();
	}
	/**
	 * 增加n天
	 * @param sdate
	 * @return
	 */
	public static Date addDayOfMouth(Date sdate,int dayNum){	
		Calendar cd = Calendar.getInstance();
		cd.setTime(sdate);
		cd.add(Calendar.DAY_OF_MONTH, dayNum);
		return cd.getTime();
	}
	/**
	 * 获取当月总天数
	 * @param sdate
	 * @return
	 */
	public static int getDaysOfDate(Date sdate){	
		Calendar cd = Calendar.getInstance();
		cd.setTime(sdate);		
		return cd.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 获取当月号数
	 * @param sdate
	 * @return
	 */
	public static int getDaysOfMouth(Date sdate){	
		Calendar cd = Calendar.getInstance();
		cd.setTime(sdate);		
		return cd.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 计算天数
	 * @param startdate
	 * @param enddate
	 * @return
	 */
	public static int dayBetween(Date startdate,Date enddate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			startdate = sdf.parse(sdf.format(startdate));
			enddate = sdf.parse(sdf.format(enddate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(startdate);    
		        long time1 = cal.getTimeInMillis();                 
		        cal.setTime(enddate);    
		        long time2 = cal.getTimeInMillis();         
		        long between_days=(time2-time1)/(1000*3600*24);  
		        return Integer.parseInt(String.valueOf(between_days/365)); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	//把yyyymmdd转成yyyy-MM-dd格式
		public static String formatDate(String str){
			  SimpleDateFormat sf1 = new SimpleDateFormat("yyyy.MM.dd");
			     SimpleDateFormat sf2 =new SimpleDateFormat("yyyy-MM-dd");
			     String sfstr = "";
			     try {
			      sfstr = sf2.format(sf1.parse(str));
			      System.out.println(sfstr);
			  } catch (ParseException e) {
			   e.printStackTrace();
			  }
			  return sfstr;
			 }

		/**
		 * 得到总页数
		 * @param totalCount
		 * @return
		 */
		public static int getTotalPages(int totalCount){
			 
			  return totalCount%10==0?totalCount/10:totalCount/10+1;
		}
		/**
		 * 添加n天
		 * @param str
		 * @param dayNum
		 * @return
		 */
		public static String addDays(String str,int dayNum){
			Calendar cd = Calendar.getInstance();
			cd.setTime(string2Date(str));
			cd.add(Calendar.DAY_OF_MONTH, dayNum);
			return date2String(cd.getTime());
		}
}
