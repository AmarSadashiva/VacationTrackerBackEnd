package com.cerner.VacationTracker.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cerner.VacationTracker.Entity.Associate;
import com.cerner.VacationTracker.Entity.Vacation;
import com.cerner.VacationTracker.Service.VacationTrackerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(VacationTrackerController.class)
class VacationTrackerControllerTest {
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

		mockVacationDetails1.setAssociateID("AS078223");
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
		mockAssociateDetails.setManagerName("Akshay");
		mockAssociateDetails.setDateOfJoin("2020-01-13");
		mockAssociateDetails.setIsManager(true);
	}

	String inputInJson = "{\r\n" + "        \"serialNum\": \"1\",\r\n" + "        \"associateID\": \"AS078223\",\r\n"
			+ "        \"vacationType\": \"sick leave\",\r\n" + "        \"startDate\": \"11 mar 2020\",\r\n"
			+ "        \"endDate\": \"13 mar 2020\"\r\n" + "    }";

	@Autowired
	private MockMvc mvc;
	@InjectMocks
	private VacationTrackerController vacationTrackerController;
	@MockBean
	private VacationTrackerService vacationTrackerService;

	@Test
	void testGetassociateById() throws Exception {
		Mockito.when(vacationTrackerService.getAssociateById(Mockito.anyString()))
				.thenReturn(Optional.of(mockAssociateDetails));
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/VacationTracker/getProfileData/AS078223")
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		String expectedJson = this.mapToJson(mockAssociateDetails);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	void testGetassociateByIdException() throws Exception {
		String associate = null;
		assertEquals(vacationTrackerController.getassociateById(associate).getStatusCode(), HttpStatus.NO_CONTENT);
	}

	@Test
	void testCreateVacationDetails() throws Exception {
		Mockito.when(vacationTrackerService.createVacationDetails(Mockito.any(Vacation.class)))
				.thenReturn(mockVacationDetails);

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/VacationTracker/addVacationInfo")
				.accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	@Test
	void testCreateVacationDetailsException() throws Exception {
		assertEquals(vacationTrackerController.createVacationDetails(null).getStatusCode(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Test
	void testGetVacationHistoryByAssociateId() throws Exception {
		List<Vacation> vacationList = new ArrayList<>();
		vacationList.add(mockVacationDetails1);
		vacationList.add(mockVacationDetails2);
		vacationList.add(mockVacationDetails);

		Mockito.when(vacationTrackerService.getVacationHistoryByAssociateId(Mockito.anyString()))
				.thenReturn(vacationList);
		MvcResult result = mvc.perform(MockMvcRequestBuilders
				.get("/api/VacationTracker/getAssociatevacationHistory/AS078223").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		String expectedJson = this.mapToJson(vacationList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	void testGetVacationHistoryByAssociateIdException() throws Exception {
		assertEquals(vacationTrackerController.getVacationHistoryByAssociateId(null).getStatusCode(),
				HttpStatus.NO_CONTENT);
	}

	@Test
	void testGetVacationCountByAssociateId() throws Exception {
		int count1 = 3;
		Mockito.when(vacationTrackerService.getVacationCountByAssociateId((Mockito.anyString()))).thenReturn(count1);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/VacationTracker/getVacationCount/AS078223")
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		String expectedJson = this.mapToJson(count1);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
