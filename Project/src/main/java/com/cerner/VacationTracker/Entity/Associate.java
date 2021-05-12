package com.cerner.VacationTracker.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Associate entity class is a POJO class
 * It represents objects in the vacationtracker database   
 * @author AS078223
 *
 */
@Entity
@Table(name = "associate")
public class Associate {
	@Id
	@Column(name = "associate_id")
	private String associateID;
	
	@Column(name = "associate_name")
	private String associateName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "manager_id")
	private String managerID;
	
	@Column(name = "manager_name")
	private String managerName;

	public String getAssociateID() {
		return associateID;
	}

	public void setAssociateID(String associateID) {
		this.associateID = associateID;
	}

	public String getAssociateName() {
		return associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getManagerID() {
		return managerID;
	}

	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

}
