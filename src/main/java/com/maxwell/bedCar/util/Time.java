package com.maxwell.bedCar.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Time {

	final static int ONE_HOUR_SECONDS = 3600;
	final static int ONE_MINUTE_SECONDS = 60;
	final static Double PRICE_PER_HOUR = 2.00;

	int seconds;
	int minutes;
	int hours;

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Time(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	
	/**
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public static Time difference(Time start, Time stop) {
		Time diff = new Time(0, 0, 0);

		if (stop.seconds > start.seconds) {
			--start.minutes;
			start.seconds += 60;
		}

		diff.seconds = start.seconds - stop.seconds;
		if (stop.minutes > start.minutes) {
			--start.hours;
			start.minutes += 60;
		}

		diff.minutes = start.minutes - stop.minutes;
		diff.hours = start.hours - stop.hours;

		return (diff);
	}

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
		String inHourSplit[] = inHour.split(":");
		String outHourSplit[] = outHour.split(":");

		Time startHour = new Time(Integer.parseInt(inHourSplit[0]), Integer.parseInt(inHourSplit[1]),
				Integer.parseInt(inHourSplit[2]));
		Time finishHour = new Time(Integer.parseInt(outHourSplit[0]), Integer.parseInt(outHourSplit[1]),
				Integer.parseInt(outHourSplit[2]));
		Time diff = new Time(0, 0, 0);

		diff = Time.difference(startHour, finishHour);

		System.out.println(diff.getHours() + diff.getMinutes() + diff.getSeconds());

		String totalHours = diff.getHours() + ":" + diff.getMinutes() + ":" + diff.getSeconds();

		return totalHours.replace("-", "");
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

		System.out.println("hours h*3600 = " + hour);
		System.out.println("minutes m*60 = " + minute);
		System.out.println("hours + minutes = " + hourPlusMinutes);
		System.out.println("price 2/3600 = " + price);

		String totalValue = "€" + Double.toString(hourPlusMinutes * price);

		System.out.println("Total " + totalValue);
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