package com.maxwell.bedCar.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.bedCar.entity.CheckInOutEntity;
import com.maxwell.bedCar.model.CheckInOutModel;
import com.maxwell.bedCar.service.impl.CheckInOutServiceImpl;
import com.maxwell.bedCar.service.impl.MapValidationErrorService;
import com.maxwell.bedCar.util.Time;

@RestController
@CrossOrigin("*")
public class CheckInOutController {

	@Autowired
	private CheckInOutServiceImpl service;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	final static int ONE_HOUR_SECONDS = 3600;
	final static int ONE_MINUTE_SECONDS = 60;
	final static Double PRICE_PER_HOUR = 2.00;

	/**
	 * Check in the single car
	 * 
	 * @param entity
	 * @param result
	 * @return
	 */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/v1/checkInOut/checksIn")
	public ResponseEntity<?> checkIn(@Valid @RequestBody CheckInOutEntity entity, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		entity.setCheckInHour(hourNow());

		CheckInOutModel model = service.register(entity);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.CREATED);
	}

	/**
	 * Check in the single car
	 * 
	 * @param entity
	 * @param result
	 * @return
	 */
	@PostMapping(path = "/api/v1/checkInOut/checksOut")
	public ResponseEntity<?> checkOut(@Valid @RequestBody CheckInOutEntity entity, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		//entity.setCheckOutHour(hourNow());
		
		String totalToPay = calculate(entity.getCheckInHour(), entity.getCheckOutHour());
		
		CheckInOutModel model = service.register(entity);
		
		model.setValue(totalToPay);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.CREATED);
	}

	/**
	 * Check in the single car
	 * 
	 * @param entity
	 * @param result
	 * @return
	 */
	@PostMapping(path = "/api/v1/checkInOut/payments")
	public ResponseEntity<?> registerPayment(@Valid @RequestBody CheckInOutEntity entity, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		CheckInOutModel model = service.register(entity);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.CREATED);
	}

	@PutMapping(path = "/api/v1/checkInOut/checksInOut")
	public ResponseEntity<?> update(@Valid @RequestBody CheckInOutEntity entity) {
		CheckInOutModel model = service.update(entity);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.CREATED);
	}

	@GetMapping(path = "/api/v1/checkInOut/checksInOut")
	public ResponseEntity<?> findAll() {
		List<CheckInOutModel> list = service.findAll();

		return new ResponseEntity<List<CheckInOutModel>>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/api/v1/checkInOut/checksInOut/{id}")
	public ResponseEntity<?> findById(@Valid @PathVariable("id") Long id) {
		CheckInOutModel model = service.findById(id);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.OK);
	}

	@GetMapping(path = "/api/v1/checkInOut/checksInOut/{carPlate}")
	public ResponseEntity<?> findByCarPlate(@Valid @PathVariable("carPlate") String carPlate) {
		CheckInOutModel model = service.findByCarPlate(carPlate);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.OK);
	}

	public String calculate(String checkIn, String checkOut) {
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

		String totalValue = "€" + Double.toString(hourPlusMinutes * price);

		System.out.println("Total " + totalValue);
		return totalValue;
	}

}
