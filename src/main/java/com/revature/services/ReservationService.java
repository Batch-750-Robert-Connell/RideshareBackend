package com.revature.services;

import com.revature.beans.Reservation;

public interface ReservationService {
	
	public Reservation getReservationByDriverId(int userId);
	
    public Reservation getReservationByDriverIdAndTravelDate(int driverId, String travelDate);

    public Reservation getReservationByRiderId(int riderId);
    
    public Reservation addReservation(Reservation reservation);

}
