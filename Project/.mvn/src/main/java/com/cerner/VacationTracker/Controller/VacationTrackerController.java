package com.cerner.VacationTracker.Controller;

import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.cerner.VacationTracker.Entity.Associate;
import com.cerner.VacationTracker.Entity.Vacation;
import com.cerner.VacationTracker.Service.VacationTrackerService;

/**
 * 
 * @author AS078223 VacationTrackerController is a controller class to handle
 *         requests from client
 */

@RestController
@RequestMapping(value = "/api/VacationTracker")
public class VacationTrackerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(VacationTrackerController.class);
	@Autowired
	private VacationTrackerService vacationTrackerService;

	/**
	 * Invokes getAssociateById method present in vacationTrackerService class
	 * 
	 * @param associate_id (Unique id which identifies an associate)
	 * @return Associate (Associate details which includes all the fields mentioned
	 *         in Associate enity class
	 * @throws Exception
	 */
	@GetMapping(value = "/getProfileData/{associate_id}")
	public ResponseEntity<Associate> getassociateById(@PathVariable("associate_id") String associate_id)
			throws Exception {
		Associate associateResult = null;
		try {
			associateResult = vacationTrackerService.getAssociateById(associate_id).get();
			if (associateResult == null) {
				throw new Exception("Associate doesnot exist");
			}
		} catch (Exception ex) {
			LOGGER.warn("Associate doesnot exist---method:ResponseEntity<Associate> getassociateById(@PathVariable(\"associate_id\") String associate_id)");
			return new ResponseEntity<Associate>(associateResult, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Associate>(associateResult, HttpStatus.OK);
	}

	/**
	 * Invokes createVacationDetails method present in vacationTrackerService class
	 * 
	 * @param vacationDetails (vacation details which includes every field mentioned
	 *                        in Vacation Entity class)
	 * @return Vacation (returns the Associate vacation details which has been added
	 *         to the database)
	 * @throws Exception
	 */
	@PostMapping(value = "/addVacationInfo")
	public ResponseEntity<Vacation> createVacationDetails(@RequestBody Vacation vacationDetails) throws Exception {
		Vacation vacationResult = null;
		try {
			vacationResult = vacationTrackerService.createVacationDetails(vacationDetails);
			if (vacationResult == null) {
				throw new Exception("Vacation details cannot be inserted");
			}
		} catch (Exception ex) {
			LOGGER.warn("Vacation details cannot be inserted---method:ResponseEntity<Vacation> createVacationDetails(@RequestBody Vacation vacationDetails)");
			return new ResponseEntity<Vacation>(vacationResult, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Vacation>(vacationResult, HttpStatus.CREATED);
	}

	/**
	 * Invokes UpdateAcceptedVacationRequests method present in
	 * vacationTrackerService class
	 * 
	 * @param vacationDetails (vacation details of the associate whose request has
	 *                        been accepted by manager
	 * @return Vacation (updated vacation details)
	 * @throws Exception
	 */
	@PutMapping(value = "/acceptRequest")
	public ResponseEntity<Vacation> updateAcceptedVacationRequests(@RequestBody Vacation vacationDetails)
			throws Exception {
		Vacation vacationResult = null;
		try {
			if (vacationDetails != null) {
				vacationDetails.setLeaveStatus("accepted");
				vacationResult = vacationTrackerService.updateAcceptedVacationRequests(vacationDetails);
				if (vacationResult == null) {
					throw new Exception("Vacation details cannot be updated");
				}
			}
		} catch (Exception ex) {
			LOGGER.warn("Vacation details cannot be updated---ResponseEntity<Vacation> UpdateAcceptedVacationRequests(@RequestBody Vacation vacationDetails)");
			return new ResponseEntity<Vacation>(vacationResult, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Vacation>(vacationResult, HttpStatus.CREATED);
	}

	/**
	 * Invokes UpdateAcceptedVacationRequests method present in
	 * vacationTrackerService class
	 * 
	 * @param vacationDetails (vacation details of the associate whose request has
	 *                        been accepted by manager
	 * @return Vacation (updated vacation details)
	 * @throws Exception
	 */
	@PutMapping(value = "/declineRequest")
	public ResponseEntity<Vacation> updateDeclinedVacationRequests(@RequestBody Vacation vacationDetails)
			throws Exception {
		Vacation vacationResult = null;
		try {
			if (vacationDetails != null) {
				vacationDetails.setLeaveStatus("declined");
				vacationResult = vacationTrackerService.updateDeclinedVacationRequests(vacationDetails);
				if (vacationResult == null) {
					throw new Exception("Vacation details cannot be updated");
				}
			}
		} catch (Exception ex) {
			LOGGER.warn("Vacation details cannot be updated---method:ResponseEntity<Vacation> UpdateDeclinedVacationRequests(@RequestBody Vacation vacationDetails)");
			return new ResponseEntity<Vacation>(vacationResult, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Vacation>(vacationResult, HttpStatus.CREATED);
	}

	/**
	 * Invokes getVacationHistoryByAssociateId method present in
	 * vacationTrackerService class
	 * 
	 * @param associate_id (Unique id which identifies an associate)
	 * @return List<Vacation> (list of vacations taken by an associate in which each
	 *         item in list consists of all the fields mentioned in Vacation entity
	 *         class)
	 * @throws Exception
	 */
	@GetMapping(value = "/getAssociatevacationHistory/{associate_id}")
	public ResponseEntity<List<Vacation>> getVacationHistoryByAssociateId(@PathVariable("associate_id") String associate_id) throws Exception {
		List<Vacation> vacationHistory = null;
		try {
			vacationHistory = vacationTrackerService.getVacationHistoryByAssociateId(associate_id);
			if (vacationHistory == null) {
				throw new Exception("No vacation History exists");
			}
		} catch (Exception ex) {
			LOGGER.warn("No vacation History exists---method:ResponseEntity<List<Vacation>> getVacationHistoryByAssociateId(@PathVariable(\"associate_id\") String associate_id)");
			return new ResponseEntity<List<Vacation>>(vacationHistory, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Vacation>>(vacationHistory, HttpStatus.OK);
	}

	/**
	 * Invokes getVacationDetailsForReview method present in vacationTrackerService class
	 * 
	 * @param manager_id (unique id which identifies the manager)
	 * @return List<Vacation> (List of vacation details of the associates under the
	 *         manager
	 * @throws Exception
	 */
	@GetMapping(value = "/getVacationDetailsForReview/{manager_id}")
	public ResponseEntity<List<Vacation>> getVacationDetailsForReview(@PathVariable("manager_id") String manager_id)
			throws Exception {
		List<Vacation> vacationDetails = null;
		try {
			vacationDetails = vacationTrackerService.getVacationDetailsForReview(manager_id);
			if (vacationDetails == null) {
				throw new Exception("No vacation details to review");
			}
		} catch (Exception ex) {
			LOGGER.warn("No vacation details to review exists---method:ResponseEntity<List<Vacation>> getVacationDetailsForReview(@PathVariable(\"manager_id\") String manager_id)");
			return new ResponseEntity<List<Vacation>>(vacationDetails, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Vacation>>(vacationDetails, HttpStatus.OK);
	}
	
	/**
	 * Invokes getVacationHistory method present in vacationTrackerService class
	 * 
	 * @param associate_id (unique id which identifies the associate)
	 * @return List<Vacation> (list of vacation history of the associate)
	 * @throws Exception
	 */
	@GetMapping(value = "/getVacationHistory/{associate_id}")
	public ResponseEntity<List<Vacation>> getVacationHistory(@PathVariable("associate_id") String associate_id)
			throws Exception {
		List<Vacation> vacationDetails = null;
		try {
			vacationDetails = vacationTrackerService.getVacationHistory(associate_id);
			if (vacationDetails == null) {
				throw new Exception("Vacation History doesnt exist");
			}
		} catch (Exception ex) {
			LOGGER.warn("No vacation details to review exists---method:ResponseEntity<List<Vacation>> getVacationHistory(@PathVariable(\"associate_id\") String associate_id)");
			return new ResponseEntity<List<Vacation>>(vacationDetails, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Vacation>>(vacationDetails, HttpStatus.OK);
	}

	/**
	 * Invokes getVacationCountByAssociateId method present in
	 * vacationTrackerService class
	 * 
	 * @param associate_id (Unique id which identifies an associate)
	 * @return Integer (vacation count)
	 * @throws Exception
	 */
	@GetMapping(value = "/getVacationCount/{associate_id}")
	public ResponseEntity<Integer> getVacationCountByAssociateId(@PathVariable("associate_id") String associate_id)
			throws Exception {
		Integer vacationCount = vacationTrackerService.getVacationCountByAssociateId(associate_id);
		return new ResponseEntity<Integer>(vacationCount, HttpStatus.OK);
	}
}
