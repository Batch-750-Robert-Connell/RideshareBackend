package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

/**
 * Batch class that represents a user's batch. All batches have a batch number and a location.
 * 
 * @author Adonis Cabreja
 *
 */

@Component
@Entity
@Table(name="batches")
public class Batch implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull(message="Must define batch number")
	@Column(name="batch_number")
	private int batchNumber;
	
	@NotBlank(message="Must define batch location")
	@Column(name="batch_location")
	private String batchLocation;
	
	public Batch() {
		super();
	}

	public Batch(int batchNumber, @NotBlank String batchLocation) {
		super();
		this.batchNumber = batchNumber;
		this.batchLocation = batchLocation;
	}

	
	/** 
	 * @return int
	 * value is batch number 
	 */
	public int getBatchNumber() {
		return batchNumber;
	}

	
	/** 
	 * @param batchNumber
	 * batch number setter 
	 */
	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	
	/** 
	 * @return String
	 * value is batch location
	 */
	public String getBatchLocation() {
		return batchLocation;
	}

	
	/** 
	 * @param batchLocation
	 * batch location setter 
	 */
	public void setBatchLocation(String batchLocation) {
		this.batchLocation = batchLocation;
	}

	
	/** 
	 * @return int
	 * value is hashcode result
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchLocation == null) ? 0 : batchLocation.hashCode());
		result = prime * result + batchNumber;
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
		Batch other = (Batch) obj;
		if (batchLocation == null) {
			if (other.batchLocation != null)
				return false;
		} 
		else if (!batchLocation.equals(other.batchLocation))
			return false;
		return batchNumber == other.batchNumber;
	}

	
	/** 
	 * @return String
	 */
	@Override
	public String toString() {
		return "Batch [batchNumber=" + batchNumber + ", batchLocation=" + batchLocation + "]";
	}
	
}
