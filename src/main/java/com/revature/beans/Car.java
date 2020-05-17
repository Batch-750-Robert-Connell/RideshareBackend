package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;

/**
 * Car class that represents a user's car. All cars have an id, color, seats, make, model, year
 * and the corresponding user.
 * 
 * @author Adonis Cabreja
 *
 */

@Component
@Entity
@Table(name="cars")
public class Car implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="car_id")
	private int carId;
	
	private String color;
	
	@Min(value=1, message = "Car cannot have less than one seat available")
	@Max(value=6, message = "Car cannot have more than six seats available")
	@Positive
	private int seats;
	
	@NotBlank(message = "Fill in all required fields.")
	private String make;
	
	@NotBlank(message ="Fill in all required fields.")
	private String model;
	
	@Positive
	@Min(value = 1980, message="Cars must from at least year 1980")
	@Column(name="car_year")
	private int year;
	
	@OneToOne
	@JoinColumn(name="user_id", unique=true)
	private User user;
	
	public Car() {
		super();
	}

	public Car(int carId, String color, int seats, String make, String model, int year, User user) {
		super();
		this.carId = carId;
		this.color = color;
		this.seats = seats;
		this.make = make;
		this.model = model;
		this.year = year;
		this.user = user;
	}

	
	/** 
	 * @return int
	 * value is car ID
	 */
	public int getCarId() {
		return carId;
	}

	
	/** 
	 * @param carId
	 * car ID setter 
	 */
	public void setCarId(int carId) {
		this.carId = carId;
	}

	
	/** 
	 * @return String
	 * value is car color 
	 */
	public String getColor() {
		return color;
	}

	
	/** 
	 * @param color
	 * car color setter 
	 */
	public void setColor(String color) {
		this.color = color;
	}

	
	/** 
	 * @return int
	 * value is number of seats
	 */
	public int getSeats() {
		return seats;
	}

	
	/** 
	 * @param seats
	 * car seat setter 
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}

	
	/** 
	 * @return String
	 * value is car make 
	 */
	public String getMake() {
		return make;
	}

	
	/** 
	 * @param make
	 * car make setter 
	 */
	public void setMake(String make) {
		this.make = make;
	}

	
	/** 
	 * @return String
	 * value is car model
	 */
	public String getModel() {
		return model;
	}

	
	/** 
	 * @param model
	 * car model setter
	 */
	public void setModel(String model) {
		this.model = model;
	}

	
	/** 
	 * @return int
	 * value is car year
	 */
	public int getYear() {
		return year;
	}

	
	/** 
	 * @param year
	 * car year setter
	 */
	public void setYear(int year) {
		this.year = year;
	}

	
	/** 
	 * @return User
	 * value is user oject 
	 */
	public User getUser() {
		return user;
	}

	
	/** 
	 * @param user
	 * user setter 
	 */
	public void setUser(User user) {
		this.user = user;
	}

	
	/** 
	 * @return int
	 * value is hashcode result 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carId;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + seats;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + year;
		return result;
	}

	
	/** 
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (carId != other.carId)
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} 
		else if (!color.equals(other.color))
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} 
		else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} 
		else if (!model.equals(other.model))
			return false;
		if (seats != other.seats)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} 
		else if (!user.equals(other.user))
			return false;
		return year == other.year;
	}

	
	/** 
	 * @return String
	 */
	@Override
	public String toString() {
		return "Car [carId=" + carId + ", color=" + color + ", seats=" + seats + ", make=" + make + ", model=" + model
				+ ", year=" + year + ", user=" + user + "]";
	}
	
}

