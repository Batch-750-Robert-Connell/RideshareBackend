package com.revature.controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.errors.ApiException;
import com.revature.Driver;
import com.revature.beans.Batch;
import com.revature.beans.User;
import com.revature.services.BatchService;
import com.revature.services.DistanceService;
import com.revature.services.ReservationService;
import com.revature.services.UserService;
import com.revature.services.impl.ReservationServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.revature.beans.*;
import com.revature.repositories.*;
@RestController
@CrossOrigin
@Api(tags= {"Reservation"})
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService rr;

    @ApiOperation(value="Returns reservation by driver id", tags= {"Reservation"})
    @GetMapping("/driver")
    public Reservation getReservationByDriverId(@RequestParam("id") int id) {
        return rr.getReservationByDriverId(id);
    }

    @ApiOperation(value="Returns reservation by driver id and travel date", tags= {"Reservation"})
    @GetMapping("/travel")
    public Reservation getReservationByDriverIdAndTravelDate(@RequestParam("id") int id, @RequestParam("travelDate") String travelDate) {
        return rr.getReservationByDriverIdAndTravelDate(id, travelDate);
    }

    
	@ApiOperation(value="Returns reservation by rider id", tags= {"Reservation"})
	@GetMapping("/rider")
	public Reservation getReservationByRiderId(@RequestParam("id")int id) {
		
		return rr.getReservationByRiderId(id);
    }
    
    @ApiOperation(value="Adds a new reservation", tags= {"Reservation"})
	@PostMapping
    public ResponseEntity<Reservation> addReservation(@Valid @RequestBody Reservation reservation) {
        return new ResponseEntity<>(rr.addReservation(reservation), HttpStatus.CREATED);
    }
	
}
