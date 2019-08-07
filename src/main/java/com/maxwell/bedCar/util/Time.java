package com.maxwell.bedCar.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

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

	public static String getAtualDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

}