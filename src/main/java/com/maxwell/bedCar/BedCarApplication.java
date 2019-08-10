package com.maxwell.bedCar;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.maxwell.bedCar.util.DateAndTime;

@SpringBootApplication
public class BedCarApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BedCarApplication.class, args);
		
		String dateStart = "11:16:08";
		String dateStop = "22:10:49";

		String total = DateAndTime.calculateHours(dateStart, dateStop);
		
		String pay = DateAndTime.calculateTotalPayment(total);
		
		System.out.println(total);
		System.out.println(pay);
		
	}
}