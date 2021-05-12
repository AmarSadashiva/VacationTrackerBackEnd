package com.cerner.VacationTracker.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cerner.VacationTracker.Entity.Vacation;

/**
 * VacationRepository provides a CRUD interface for Vacation entity
 * 
 * @author AS078223
 */
@Repository
public interface VacationRepository extends CrudRepository<Vacation, String> {

	/**
	 * Implements the nativeQuery mentioned below
	 * 
	 * @param associate_id (Unique id which identifies an associate)
	 * @return List<Vacation> (list of vacations taken by an associate in which each
	 *         item in list consists of all the fields mentioned in Vacation entity
	 *         class)
	 */
	@Query(value = "SELECT * FROM vacationtracker.vacation_info WHERE associate_id = ?1", nativeQuery = true)
	List<Vacation> getVacationHistoryByAssociateId(String associate_id);

	/**
	 * Implements the nativeQuery mentioned below
	 * 
	 * @param associate_id (Unique id which identifies an associate)
	 * @return int (Vacation count of an associate)
	 */
	@Query(value = "SELECT COUNT(*) FROM vacationtracker.vacation_info WHERE associate_id  = ?1", nativeQuery = true)
	int getVacationCountByAssociateId(String associate_id);

	/**
	 * Implements the nativeQuery mentioned below
	 * @param manager_id (Unique id which identifies an associate)
	 * @return List<Vacation> (List of all the vacation requests made to the manager for reviewing)
	 */
	@Query(value = "SELECT * FROM vacationtracker.vacation_info WHERE associate_id IN (SELECT associate_id from vacationtracker.associate where manager_id = ?1) and leave_status = 'in review'", nativeQuery = true)
	List<Vacation> getVacationDetailsForReview(String manager_id);
	
	/**
	 * Implements the nativeQuery mentioned below
	 * @param associate_id (Unique id which identifies an associate)
	 * @return List<Vacation> (List of all the vacation history of the associate)
	 */
	@Query(value = "SELECT * FROM vacationtracker.vacation_info WHERE associate_id  = ?1", nativeQuery = true)
	List<Vacation> getVacationHistory(String associate_id);
}
