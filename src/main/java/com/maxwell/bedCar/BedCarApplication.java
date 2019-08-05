package com.maxwell.bedCar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.maxwell.bedCar.util.Time;

@SpringBootApplication
public class BedCarApplication {

	final static int ONE_HOUR_SECONDS = 3600;
	final static int ONE_MINUTE_SECONDS = 60;
	final static Double PRICE_PER_HOUR = 2.00;

	public String calculateHours(String inHour, String outHour) {
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

		return totalHours;
	}

	/**
	 * 
	 * @return
	 */
	public String hourNow() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	/**
	 * ((h*3600)+(m*60))*(priceperhour/3600)
	 * 
	 * @param totalHours
	 */
	public String calculateTotalPayment(String totalHours) {
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

		String totalValue = "€" + Double.toString(hourPlusMinutes * price).replace("-", "");

		System.out.println("Total " + totalValue);
		return totalValue;
	}

	public static void main(String[] args) {
		SpringApplication.run(BedCarApplication.class, args);

	}

}
