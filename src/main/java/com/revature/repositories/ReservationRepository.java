package com.revature.repositories;

import org.springframework.stereotype.Repository;
import com.revature.beans.Reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

    @Query("select r from Reservation r where r.driver.userId = ?1")
    public List<Reservation> getReservationByDriverId(int id);

    @Query("select r from Reservation r where r.driver.userId = ?1 and r.travelDate = ?2")
    public Reservation getReservationByDriverIdAndTravelDate(int id, String travelDate);

    @Query("select r from Reservation r where r.rider.userId = ?1")
    public List<Reservation> getReservationByRiderId(int id);
    
    @Query(value="SELECT COUNT(*) FROM RESERVATION WHERE (DRIVER_ID = ?1 AND STATUS<3)", nativeQuery = true)
	public int getCarSeatsOccupied(int userId);
    
}
