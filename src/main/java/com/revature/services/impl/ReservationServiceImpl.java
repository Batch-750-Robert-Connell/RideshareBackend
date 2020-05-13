package com.revature.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Reservation;
import com.revature.repositories.ReservationRepository;
import com.revature.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository rr;
	
	
	@Override
	public Reservation getReservationByDriverId(int userId) {
		return rr.getReservationByDriverId(userId);
	}

	@Override
	public Reservation getReservationByDriverIdAndTravelDate(int driverId, String travelDate) {
		return rr.getReservationByDriverIdAndTravelDate(driverId, travelDate);
	}

	@Override
	public Reservation getReservationByRiderId(int riderId) {
		return rr.getReservationByRiderId(riderId);
	}

	@Override
	public Reservation addReservation(Reservation reservation) {
		return rr.save(reservation);
	}
	
	public Reservation getReservationById(int reservationId){
		return rr.getOne(reservationId);
	}
	
	public Reservation updateReservation(Reservation reservation){
		return rr.save(reservation);
	}
	
	

}
