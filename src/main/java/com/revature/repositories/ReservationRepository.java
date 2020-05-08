package com.revature.repositories;

import org.springframework.stereotype.Repository;
import com.revature.beans.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

    @Query("select r from Reservation r where r.driver.userId = ?1")
    public Reservation getReservationByDriverId(int id);

    @Query("select r from Reservation r where r.driver.userId = ?1 and r.travelDate = ?2")
    public Reservation getReservationByDriverIdAndTravelDate(int id, String travelDate);

    @Query("select r from Reservation r where r.rider.userId = ?1")
    public Reservation getReservationByRiderId(int id);

}
