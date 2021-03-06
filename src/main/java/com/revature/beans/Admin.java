package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

/**
 * Admin class that represents the admins. All admins have an id and a username.
 * 
 * @author Adonis Cabreja
 *
 */

@Component
@Entity
@Table(name="admins")
public class Admin implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="admin_id")
	private int adminId;
	
	@NotBlank
	@Column(name="user_name")
	@Size(min=3,max=12, message="Must be between 3 and 12 characters")
	@Pattern(regexp="^\\w+\\.?\\w+$", message="Must use valid characters")
	private String userName;
	
	public Admin() {
		super();
	}
	
	public Admin(int adminId, String userName) {
		super();
		this.adminId = adminId;
		this.userName = userName;
	}

	
	/** 
	 * @return int
	 * value is admin ID
	 */
	public int getAdminId() {
		return adminId;
	}

	
	/** 
	 * @param adminId
	 * admin ID setter
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	
	/** 
	 * @return String
	 * value is username
	 */
	public String getUserName() {
		return userName;
	}

	
	/** 
	 * @param userName
	 * username setter 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	/** 
	 * @return int
	 * value is hashcode result 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adminId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		Admin other = (Admin) obj;
		if (adminId != other.adminId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} 
		else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	
	/** 
	 * @return String
	 *  
	 */
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", userName=" + userName + "]";
	}
	
}
