package com.cerner.VacationTracker.Dao;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.cerner.VacationTracker.Entity.Vacation;
import com.cerner.VacationTracker.Service.VacationTrackerService;

 

@RunWith(SpringRunner.class)
@SpringBootTest
class VacationRepositoryTest {
    Vacation mockVacationDetails = new Vacation();
    Vacation mockVacationDetails1 = new Vacation();
    Vacation mockVacationDetails2 = new Vacation(); 
    
    @BeforeEach
    void setUp() throws Exception {
//        mockVacationDetails.setSerial_num("5");
        mockVacationDetails.setAssociateID("AS078223");
        mockVacationDetails.setVacationType("Personal leave");
        mockVacationDetails.setStartDate("21 mar 2020");
        mockVacationDetails.setEndDate("24 mar 2020");
        
//        mockVacationDetails1.setSerial_num("1");
        mockVacationDetails1.setAssociateID("AS078223");
        mockVacationDetails1.setVacationType("sick leave");
        mockVacationDetails1.setStartDate("11 mar 2020");
        mockVacationDetails1.setEndDate("13 mar 2020");
        
//        mockVacationDetails2.setSerial_num("4");
        mockVacationDetails2.setAssociateID("AS078223");
        mockVacationDetails2.setVacationType("work from home");
        mockVacationDetails2.setStartDate("12 mar 2020");
        mockVacationDetails2.setEndDate("15 mar 2020");
        
    }
    
    @MockBean
    private VacationTrackerService vacationTrackerService;

    @Autowired
    private VacationRepository vacationRepository;
 

    
     
    
    @Test
    void testGetVacationHistoryByAssociateId() {
        List<Vacation> vacationList1 = new ArrayList<>();
        vacationList1 =  vacationTrackerService.getVacationHistoryByAssociateId("AS078223");
        System.out.println(vacationList1);
        assertThat(vacationList1).isNotNull();
        
        
    }

 

    @Test
    void testGetVacationCountByAssociateId() {
        int expCount = 0;
        int actualCount = vacationTrackerService.getVacationCountByAssociateId("AS078223");
        
        assertThat(actualCount).isEqualTo(expCount);
    }

 

//    @Test
//    void testSave() {
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(VacationTrackerApplication.class);
//        VacationTrackerService vacationTrackerService = applicationContext.getBean("vacationTrackerService",VacationTrackerService.class);
//        
//        Vacation mockvacationDetails = new Vacation();
//        mockvacationDetails.setSerial_num("5");
//        mockvacationDetails.setAssociateID("AS078223");
//        mockvacationDetails.setVacationType("personal leave");
//        mockvacationDetails.setStartDate("21 mar 2020");
//        mockvacationDetails.setEndDate("24 mar 2020");
//        
//        vacationTrackerService.createVacationDetails(mockvacationDetails);
//        Optional <Associate> associate = vacationTrackerService.getAssociateById("AS078223");
//        assertEquals(mockvacationDetails.getAssociateID(),associate.get().getAssociateID());
//        
//        
//    
//    }

 

}
 