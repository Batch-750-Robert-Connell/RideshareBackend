package com.revature.services;

import java.util.List;

import com.revature.beans.Reservation;

public interface ReservationService {
	
	public List<Reservation> getReservationByDriverId(int userId);
	
    public Reservation getReservationByDriverIdAndTravelDate(int driverId, String travelDate);

    public List<Reservation> getReservationByRiderId(int riderId);
    
    public Reservation addReservation(Reservation reservation);
    
    public Reservation getReservationById(int reservationId);
    
    public Reservation updateReservation(Reservation reservation);

}
