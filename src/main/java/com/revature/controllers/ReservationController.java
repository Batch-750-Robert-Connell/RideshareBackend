package com.revature.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.services.CarService;
import com.revature.services.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.revature.beans.*;

@RestController
@CrossOrigin
@Api(tags = { "Reservation" })
@RequestMapping("/reservations")
public class ReservationController {

	@Autowired
	private ReservationService rr;

	@Autowired
	private CarService cs;

	
	/** 
	 * @param id
	 * @return List<Reservation>
	 */
	@ApiOperation(value = "Returns reservation by driver id", tags = { "Reservation" })
	@GetMapping("/driver")
	public List<Reservation> getReservationByDriverId(@RequestParam("id") int id) {
		return rr.getReservationByDriverId(id);
	}

	
	/** 
	 * @param @RequestParam("id"
	 * @return Reservation
	 */
	@ApiOperation(value = "Returns reservation by driver id and travel date", tags = { "Reservation" })
	@GetMapping("/travel")
	public Reservation getReservationByDriverIdAndTravelDate(@RequestParam("id") int id,
			@RequestParam("token") String travelDate) {
		return rr.getReservationByDriverIdAndTravelDate(id, travelDate);
	}

	
	/** 
	 * @param id
	 * @return List<Reservation>
	 */
	@ApiOperation(value = "Returns reservation by rider id", tags = { "Reservation" })
	@GetMapping("/rider")
	public List<Reservation> getReservationByRiderId(@RequestParam("id") int id) {

		return rr.getReservationByRiderId(id);
	}

	
	/** 
	 * @param reservation
	 * @return ResponseEntity<Reservation>
	 */
	@ApiOperation(value = "Adds a new reservation", tags = { "Reservation" })
	@PostMapping
	public ResponseEntity<Reservation> addReservation(@Valid @RequestBody Reservation reservation) {
		return new ResponseEntity<>(rr.addReservation(reservation), HttpStatus.CREATED);
	}

	
	/** 
	 * @param id
	 * @return int
	 */
	@ApiOperation(value = "Returns car seats occupied by driver id", tags = { "Reservation" })
	@GetMapping("/occupied")
	public int getOccupiedSeatsByDriverId(@RequestParam("id") int id) {
		return rr.getCarSeatsOccupied(id);
	}

	
	/** 
	 * @param id
	 * @return int
	 */
	@ApiOperation(value = "Returns car seats occupied by driver id", tags = { "Reservation" })
	@GetMapping("/seats")
	public int getAvailableSeatsByDriverId(@RequestParam("id") int id) {
		int available = cs.getCarByUserId(id).getSeats() - rr.getCarSeatsOccupied(id);
		return available;
	}

}
