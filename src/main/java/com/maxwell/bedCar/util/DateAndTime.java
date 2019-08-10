package com.maxwell.bedCar.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

public class DateAndTime {

	final static int ONE_HOUR_SECONDS = 3600;
	final static int ONE_MINUTE_SECONDS = 60;
	final static Double PRICE_PER_HOUR = 2.00;

	/**
	 * 
	 * @param checkIn
	 * @param checkOut
	 * @return
	 */
	public static String calculate(String checkIn, String checkOut) {
		String totalHours = calculateHours(checkIn, checkOut);

		String totalPayment = calculateTotalPayment(totalHours);
		return totalPayment.replace("-", "");
	}

	/**
	 * 
	 * @param inHour
	 * @param outHour
	 * @return
	 */
	public static String calculateHours(String inHour, String outHour) {

		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		StringBuilder totalHours = new StringBuilder();

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(inHour);
			d2 = format.parse(outHour);

			DateTime dt1 = new DateTime(d1);
			DateTime dt2 = new DateTime(d2);

			totalHours.append(Hours.hoursBetween(dt1, dt2).getHours() % 24).append(":");
			totalHours.append(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60).append(":");
			totalHours.append(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalHours.toString();
	}

	/**
	 * ((h*3600)+(m*60))*(priceperhour/3600)
	 * 
	 * @param totalHours
	 */
	public static String calculateTotalPayment(String totalHours) {
		int h = Integer.parseInt(totalHours.split(":")[0]);
		int m = Integer.parseInt(totalHours.split(":")[1]);

		double hour = h * ONE_HOUR_SECONDS;
		double minute = m * ONE_MINUTE_SECONDS;
		double hourPlusMinutes = hour + minute;
		Double price = (double) PRICE_PER_HOUR / ONE_HOUR_SECONDS;

		String totalValue = "€" + String.format("%.2f", hourPlusMinutes * price);

		return totalValue;
	}

	/**
	 * 
	 * @return
	 */
	public static String getCurrentHour() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	/**
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * 
	 * @param subscriptionDate
	 * @param days
	 * @return
	 */
	public static String getDueDate(String subscriptionDate, Integer days) {
		try {
			final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			final SimpleDateFormat dueDateFormat = new SimpleDateFormat("dd");
			final Date date = format.parse(subscriptionDate);
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_YEAR, days);
			return dueDateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

}