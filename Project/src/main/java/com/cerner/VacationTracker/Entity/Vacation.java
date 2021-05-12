package com.cerner.VacationTracker.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Vacation entity class is a POJO class
 * It represents objects in the vacationtracker database   
 * @author AS078223
 *
 */
@Entity
@Table(name = "vacation_info")
public class Vacation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sl_no")
	private int serial_num;
	
	@Column(name = "associate_id")
	private String associateID;
	
	@Column(name = "vacation_type")
	private String vacationType;
	
	@Column(name = "start_date")
	private String startDate;
	
	@Column(name = "end_date")
	private String endDate;
	
	@Column(name = "description")
	private String description;
	
	

	
//	public Vacation(String serial_num, String associateID, String vacationType, String startDate, String endDate) {
//		this.serial_num = serial_num;
//		this.associateID = associateID;
//		this.vacationType = vacationType;
//		this.startDate = startDate;
//		this.endDate = endDate;
	//}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSerial_num() {
		return serial_num;
	}

	public void setSerial_num(int serial_num) {
		this.serial_num = serial_num;
	}

	public String getAssociateID() {
		return associateID;
	}

	public void setAssociateID(String associateID) {
		this.associateID = associateID;
	}

	public String getVacationType() {
		return vacationType;
	}

	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
