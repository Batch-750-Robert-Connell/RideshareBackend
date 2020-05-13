package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.stereotype.Component;


/**
 * Reservation class creates the resevation table to keep track of all 
 * matches between drivers and riders
 * 
 * @author TheoGherman
 *
 */

@Component
@Entity
@Table(name="reservation")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="reservation_id")
    private int reservationId;

    @NotBlank(message="Travel date is a required field.")
    @Column(name="travel_date")
    private String travelDate;

    @ManyToOne
    @JoinColumn(name="driver_id", referencedColumnName = "user_id", nullable=false)
    private User driver;

    @ManyToOne
    @JoinColumn(name="rider_id", referencedColumnName = "user_id", nullable=false)
    private User rider;
    
	@Min(value=1, message = "Reservation status cannot be less than 1")
	@Max(value=3, message = "Reservation status cannot be greater than 3")
	@Column(name="status")
	private int status;

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public User getDriver() {
		return driver;
	}

	public void setDriver(User driver) {
		this.driver = driver;
	}

	public User getRider() {
		return rider;
	}

	public void setRider(User rider) {
		this.rider = rider;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", travelDate=" + travelDate + ", driver=" + driver
				+ ", rider=" + rider + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + reservationId;
		result = prime * result + ((rider == null) ? 0 : rider.hashCode());
		result = prime * result + status;
		result = prime * result + ((travelDate == null) ? 0 : travelDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (driver == null) {
			if (other.driver != null)
				return false;
		} else if (!driver.equals(other.driver))
			return false;
		if (reservationId != other.reservationId)
			return false;
		if (rider == null) {
			if (other.rider != null)
				return false;
		} else if (!rider.equals(other.rider))
			return false;
		if (status != other.status)
			return false;
		if (travelDate == null) {
			if (other.travelDate != null)
				return false;
		} else if (!travelDate.equals(other.travelDate))
			return false;
		return true;
	}

	public Reservation(int reservationId, @NotBlank(message = "Travel date is a required field.") String travelDate,
			User driver, User rider,
			@Min(value = 1, message = "Reservation status cannot be less than 1") @Max(value = 3, message = "Reservation status cannot be greater than 3") int status) {
		super();
		this.reservationId = reservationId;
		this.travelDate = travelDate;
		this.driver = driver;
		this.rider = rider;
		this.status = status;
	}

	public Reservation() {
		super();
	}

	
	
}
