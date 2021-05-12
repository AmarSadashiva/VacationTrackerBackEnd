package com.cerner.VacationTracker.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cerner.VacationTracker.Dao.AssociateRepository;
import com.cerner.VacationTracker.Dao.VacationRepository;
import com.cerner.VacationTracker.Entity.Associate;
import com.cerner.VacationTracker.Entity.Vacation;

@RunWith(SpringRunner.class)
@SpringBootTest
class VacationTrackerServiceTest {
	Vacation mockVacationDetails = new Vacation();
	Vacation mockVacationDetails1 = new Vacation();
	Vacation mockVacationDetails2 = new Vacation();
	Associate mockAssociateDetails = new Associate();

	@BeforeEach
	void setUp() throws Exception {
		mockVacationDetails.setAssociateID("AS078223");
		mockVacationDetails.setVacationType("Personal leave");
		mockVacationDetails.setStartDate("21 mar 2020");
		mockVacationDetails.setEndDate("24 mar 2020");

		mockVacationDetails1.setVacationType("sick leave");
		mockVacationDetails1.setStartDate("11 mar 2020");
		mockVacationDetails1.setEndDate("13 mar 2020");

		mockVacationDetails2.setAssociateID("AS078223");
		mockVacationDetails2.setVacationType("work from home");
		mockVacationDetails2.setStartDate("12 mar 2020");
		mockVacationDetails2.setEndDate("15 mar 2020");

		mockAssociateDetails.setAssociateID("AS078223");
		mockAssociateDetails.setAssociateName("Amar S");
		mockAssociateDetails.setPassword("as078223");
		mockAssociateDetails.setDesignation("Software Intern");
		mockAssociateDetails.setManagerID("NA078088");
		mockAssociateDetails.setManagerName("Nischitha");
	}

	@Autowired
	private VacationTrackerService vacationTrackerService;

	@MockBean
	private VacationRepository vacationRepository;

	@MockBean
	AssociateRepository associateRepository;;

	@Test
	void testGetAssociateById() {
		Mockito.when(associateRepository.findById("AS078223")).thenReturn(Optional.of(mockAssociateDetails));
		assertTrue(vacationTrackerService.getAssociateById("AS078223").isPresent());
	}

	@Test
	void testCreateVacationDetails() {
		Mockito.when(vacationRepository.save(mockVacationDetails)).thenReturn(mockVacationDetails);
		try {
			assertTrue(vacationTrackerService.createVacationDetails(mockVacationDetails).equals(mockVacationDetails));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	void testGetVacationHistoryByAssociateId() {
		List<Vacation> vacationList = new ArrayList<>();
		vacationList.add(mockVacationDetails1);
		vacationList.add(mockVacationDetails2);
		vacationList.add(mockVacationDetails);
		Mockito.when(vacationRepository.getVacationHistoryByAssociateId("AS078223")).thenReturn(vacationList);
		assertTrue(vacationTrackerService.getVacationHistoryByAssociateId("AS078223").equals(vacationList));
	}

	@Test
	void testGetVacationCountByAssociateId() {
		int count = 3;
		Mockito.when(vacationRepository.getVacationCountByAssociateId("AS078223")).thenReturn(count);
		assertEquals(vacationTrackerService.getVacationCountByAssociateId("AS078223"), 3);
	}

}
