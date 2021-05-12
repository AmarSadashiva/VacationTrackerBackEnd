package com.cerner.VacationTracker.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cerner.VacationTracker.Dao.AssociateRepository;
import com.cerner.VacationTracker.Dao.VacationRepository;
import com.cerner.VacationTracker.Entity.Associate;
import com.cerner.VacationTracker.Entity.Vacation;

/**
 * VacationTrackerService is a Service class which provides/receives data
 * to/from DAO class
 * 
 * @author AS078223
 *
 */
@Service
public class VacationTrackerService {
	@Autowired
	private AssociateRepository associateRepository;
	@Autowired
	private VacationRepository vacationRepository;

	/**
	 * Invokes findById method defined in CRUDRepository
	 * 
	 * @param associate_id (Unique id which identifies an associate)
	 * @return Associate (returns associate details which includes all the fields
	 *         mentioned in Associate entity class
	 */
	public Optional<Associate> getAssociateById(String associate_id) {
		return associateRepository.findById(associate_id);
	}

	/**
	 * Invokes save method defined in CRUDRepository
	 * 
	 * @param vacationDetails (vacation details which includes every field mentioned
	 *                        in Vacation Entity class)
	 * @return Vacation (returns the Associate vacation details which has been added
	 *         to the database)
	 */
	public Vacation createVacationDetails(Vacation vacationDetails) {

		return vacationRepository.save(vacationDetails);
	}

	/**
	 * Invokes getVacationHistoryByAssociateId method defined in VacationRepository
	 * class
	 * 
	 * @param associate_id (Unique id which identifies an associate)
	 * @return List<Vacation> (list of vacations taken by an associate in which each
	 *         item in list consists of all the fields mentioned in Vacation entity
	 *         class)
	 */
	public List<Vacation> getVacationHistoryByAssociateId(String associate_id) {
		return vacationRepository.getVacationHistoryByAssociateId(associate_id);
	}

	/**
	 * Invokes getVacationCountByAssociateId method defined in VacationRepository
	 * class
	 * 
	 * @param associate_id (Unique id which identifies an associate)
	 * @return int (vacation count of an associate)
	 */
	public int getVacationCountByAssociateId(String associate_id) {
		return vacationRepository.getVacationCountByAssociateId(associate_id);
	}

	/**
	 * Invokes getVacationDetailsForReview method defined in VacationRepository class
	 * @param manager_id (unique id which identifies the manager)
	 * @return List<Vacation> (list of vacation requests made to the manager)
	 */
	public List<Vacation> getVacationDetailsForReview(String manager_id) {
		return vacationRepository.getVacationDetailsForReview(manager_id);
	}
	
	/**
	 * Invokes getVacationHistory method defined in VacationRepository class
	 * @param associate_id (unique id which identifies  the  associate)
	 * @return List<Vacation> (list of vacation history of the associate)
	 */
	public List<Vacation> getVacationHistory(String associate_id) {
		return vacationRepository.getVacationHistory(associate_id);
	}

	/**
	 * Invokes save method defined in VacationRepository class
	 * @param vacationDetails (vacation details of the associate which are to be updated)
	 * @return Vacation (updated vacation details)
	 */
	public Vacation updateAcceptedVacationRequests(Vacation vacationDetails) {
		return vacationRepository.save(vacationDetails);
	}

	/**
	 * Invokes save method defined in VacationRepository class
	 * @param vacationDetails (vacation details of the associate which are to be updated)
	 * @return Vacation (updated vacation details)
	 */
	public Vacation updateDeclinedVacationRequests(Vacation vacationDetails) {
		return vacationRepository.save(vacationDetails);
	}
}
