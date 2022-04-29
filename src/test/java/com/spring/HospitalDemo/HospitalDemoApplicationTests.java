package com.spring.HospitalDemo;

import com.spring.HospitalDemo.DAO.PatientsRepository;
import com.spring.HospitalDemo.DAO.RoomsRepository;
import com.spring.HospitalDemo.controller.LoginController;
import com.spring.HospitalDemo.controller.PatientController;
import com.spring.HospitalDemo.entity.Patient;
import com.spring.HospitalDemo.entity.RoomsPatients;
import com.spring.HospitalDemo.services.PatientService;
import com.spring.HospitalDemo.services.RoomsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class HospitalDemoApplicationTests {

	@Autowired
	private RoomsService roomsService;

	@MockBean
	private RoomsRepository roomsRepository;

	@Autowired
	private PatientService patientService;

	@MockBean
	private PatientsRepository patientsRepository;

	@Autowired
	private WebApplicationContext wac;

	//Controller
	//Login Controller
	@Test
	void loginPage()
	{
		LoginController loginController = new LoginController();
		String actual = loginController.myLoginPage();
		String expected = "login.html";
		Assertions.assertEquals(expected,actual);
	}

	@Test
	void accessPage()
	{
		LoginController loginController = new LoginController();
		String actual = loginController.accessDenied();
		String expected = "access.html";
		Assertions.assertEquals(expected,actual);
	}

	@Test
	@WithMockUser(username = "SaiSrinivas" , authorities = {"ROLE_DOCTOR"})
	public void DoctorhomePage() throws Exception {
		LoginController loginController = new LoginController();
		String actual = loginController.patient();
		String expected = "redirect:/patients/list?username=SaiSrinivas";
		Assertions.assertEquals(expected,actual);
	}

	@Test
	@WithMockUser(username = "Kalyan" , authorities = {"ROLE_ADMIN"})
	public void RoomshomePage() throws Exception {
		LoginController loginController = new LoginController();
		String actual = loginController.patient();
		String expected = "redirect:/rooms/list";
		Assertions.assertEquals(expected,actual);
	}

	//Controller
	//Rooms Controller
	@Test
	public void roomListMVC() throws Exception {
		when(roomsRepository.findAll()).thenReturn(
				Stream.of(new RoomsPatients(1,101,1,6),
						new RoomsPatients(2,102,1,7)).collect(Collectors.toList()));

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		mockMvc.perform(get("/rooms/list")).andExpect(status().is(200));
	}

	@Test
	public void roomAllotMVC() throws Exception {
		when(patientService.findPatientforRooms()).thenReturn(
				Stream.of(new Patient(1,"Sai","Srinivas",22,986612281,
										"msai@gmail.com","Cramps","SaiSrinivas","active"),
								new Patient(2,"Chandu","Kalvakuri",25,951236478,
										"kchan@gmail.com","hangover","susmitha","active"))
						.collect(Collectors.toList())
		);

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		mockMvc.perform(get("/rooms/allot").queryParam("id","1")).andExpect(status().is(200));
	}

	//check
	@Test
	public void roomSelectMVC() throws Exception {

		int id =1;
		RoomsPatients roomsPatients = new RoomsPatients(1,101,1,null);
		when(roomsRepository.findById(id)).thenReturn(Optional.of(roomsPatients));

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		mockMvc.perform(get("/rooms/select").queryParam("patientId","6")
				.queryParam("id", String.valueOf(id))).andExpect(status().is(302));
	}



	//Controller
	//Patients Controller
	@Test
	public void patientsListMVC() throws Exception {
		String doc ="SaiSrinivas";
		when(patientsRepository.findByDoctorName(doc)).thenReturn(
				Stream.of(new Patient(1,"Sai","Srinivas",22,986612281,
										"msai@gmail.com","Cramps","SaiSrinivas","active"),
								new Patient(2,"Chandu","Kalvakuri",25,951236478,
										"kchan@gmail.com","hangover","SaiSrinivas","active"))
						.collect(Collectors.toList())
		);

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		mockMvc.perform(get("/patients/list").queryParam("username","SaiSrinivas"))
				.andExpect(status().is(200));
	}

	@Test
	@WithMockUser(username = "SaiSrinivas", authorities = { "DOCTOR"})
	public void patientsAddFormMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		mockMvc.perform(get("/patients/addform")).andExpect(status().isOk());
	}

	/*@Test
	public void patientSaveFormMVC() throws Exception {
		Patient patient = new Patient(6,"Sai","Srinivas",22,986612281,
				"msai@gmail.com","Cramps","SaiSrinivas","active");

		String username = "SaiSrinivas";
		patientService.addPatient(patient);

		PatientController patientController = new PatientController();
		String actual = patientController.addPatient(patient,username);

		verify(patientsRepository,times(1)).save(patient);
		String expected = "redirect:/patients/list?username=SaiSrinivas";
		Assertions.assertEquals(expected,actual);
	}*/

	@Test
	public void patientsViewDetMVC() throws Exception {
		int id =6;
		Patient patient = new Patient(6,"Sai","Srinivas",22,986612281,
				"msai@gmail.com","Cramps","SaiSrinivas","active");
		when(patientService.findPatient(id)).thenReturn(Optional.of(patient) );

		int patientId = 6;
		RoomsPatients roomsPatients = new RoomsPatients(1,101,1,6);
		when(roomsService.findRoomByPatId(patientId)).thenReturn( roomsPatients );

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		mockMvc.perform(get("/patients/viewdet").queryParam("patientId","6")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "SaiSrinivas", authorities = { "DOCTOR"})
	public void updateFormMVC() throws Exception {
		int id =6;
		Patient patient = new Patient(6,"Sai","Srinivas",22,986612281,
				"msai@gmail.com","Cramps","SaiSrinivas","active");
		when(patientService.findPatient(id)).thenReturn( Optional.of(patient) );

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		mockMvc.perform(get("/patients/updateform").queryParam("patientId","6")).andExpect(status().isOk());
	}

	//Service
	//Rooms Service
	@Test
	void allRoomsTest()
	{
		when(roomsRepository.findAll()).thenReturn(
				Stream.of(new RoomsPatients(1,101,1,6),
						new RoomsPatients(2,102,1,7)).collect(Collectors.toList())
		);
		Assertions.assertEquals(2,roomsService.findAllRoooms().size());
	}

	@Test
	void findRoomByPatIdTest()
	{
		int patientId = 6;
		RoomsPatients roomsPatients = new RoomsPatients(1,101,1,6);
		when(roomsRepository.findByPatientId(patientId)).thenReturn( roomsPatients );
		Assertions.assertEquals(roomsPatients,roomsService.findRoomByPatId(6));
	}

	@Test
	void findRoomByRoomIdTest()
	{
		int id =1;
		RoomsPatients roomsPatients = new RoomsPatients(1,101,1,6);
		when(roomsRepository.findById(id)).thenReturn(Optional.of(roomsPatients));
		Assertions.assertEquals(Optional.of(roomsPatients),roomsService.findRoomById(1));
	}

	@Test
	void updateRoomTest()
	{
		int id =1;
		int patientId=6;
		RoomsPatients roomsPatients = new RoomsPatients(1,101,1,6);
		roomsPatients.setBedNumber(2);
		when(roomsService.findRoomById(id)).thenReturn(Optional.of(roomsPatients));
		roomsService.updateRoom(Optional.of(roomsPatients));

		verify(roomsRepository,times(1)).save(roomsPatients);
	}

	//Service
	//Patients Service
	@Test
	void allPatientsTest()
	{
		when(patientsRepository.findAll()).thenReturn(
				Stream.of(new Patient(1,"Sai","Srinivas",22,986612281,
								"msai@gmail.com","Cramps","SaiSrinivas","active"),
						new Patient(2,"Chandu","Kalvakuri",25,951236478,
								"kchan@gmail.com","hangover","susmitha","active"))
						.collect(Collectors.toList())
		);
		Assertions.assertEquals(2,patientService.findAllPatients().size());
	}

	@Test
	void findPatientByIdTest()
	{
		int id =1;
		Patient patient = new Patient(1,"Sai","Srinivas",22,986612281,
				"msai@gmail.com","Cramps","SaiSrinivas","active");
		when(patientsRepository.findById(id)).thenReturn(Optional.of(patient));

		Assertions.assertEquals(Optional.of(patient),patientService.findPatient(id));
	}

	@Test
	void addPatientTest()
	{
		Patient patient = new Patient(1,"Sai","Srinivas",22,986612281,
				"msai@gmail.com","Cramps","SaiSrinivas","active");

		patientService.addPatient(patient);

		verify(patientsRepository,times(1)).save(patient);
	}

	@Test
	void findPatientsByDocTest()
	{
		String doc ="SaiSrinivas";
		when(patientsRepository.findByDoctorName(doc)).thenReturn(
				Stream.of(new Patient(1,"Sai","Srinivas",22,986612281,
										"msai@gmail.com","Cramps","SaiSrinivas","active"),
								new Patient(2,"Chandu","Kalvakuri",25,951236478,
										"kchan@gmail.com","hangover","SaiSrinivas","active"))
						.collect(Collectors.toList())
		);
		Assertions.assertEquals(2,patientService.findPatientByDoc(doc).size());
	}

	@Test
	void findPatientsForRoomsTest()
	{
		when(patientsRepository.findPatientForRooms()).thenReturn(
				Stream.of(new Patient(1,"Sai","Srinivas",22,986612281,
										"msai@gmail.com","Cramps","SaiSrinivas","active"),
								new Patient(2,"Chandu","Kalvakuri",25,951236478,
										"kchan@gmail.com","hangover","susmitha","active"))
						.collect(Collectors.toList())
		);
		Assertions.assertEquals(2,patientService.findPatientforRooms().size());
	}

	//Entity
	//RoomsPatients
	@Test
	void setIdInRoomsPatients() {
		RoomsPatients roomsPatients = new RoomsPatients();
		int expected = 1;
		roomsPatients.setId(expected);
		Assertions.assertEquals(expected,roomsPatients.getId());
	}

	@Test
	void setRoomId() {
		RoomsPatients roomsPatients = new RoomsPatients();
		int expected = 101;
		roomsPatients.setRoomId(expected);
		Assertions.assertEquals(expected,roomsPatients.getRoomId());
	}

	@Test
	void setBedNumber() {
		RoomsPatients roomsPatients = new RoomsPatients();
		int expected = 1;
		roomsPatients.setBedNumber(expected);
		Assertions.assertEquals(expected,roomsPatients.getBedNumber());
	}

	@Test
	void setPatientId() {
		RoomsPatients roomsPatients = new RoomsPatients();
		int expected = 1;
		roomsPatients.setPatientId(expected);
		Assertions.assertEquals(expected,roomsPatients.getPatientId());
	}

	@Test
	void testToString() {
		RoomsPatients roomsPatients = new RoomsPatients(1,101,1,6);
		String expected = "RoomsPatients{id=1, roomId=101, bedNumber=1, patientId=6}";
		Assertions.assertEquals(expected,roomsPatients.toString());
	}

	//Entity
	//Patient
	@Test
	void setId() {
		Patient patient = new Patient();
		int expected =1;
		patient.setId(expected);
		Assertions.assertEquals(expected,patient.getId());
	}

	@Test
	void setFirstName() {
		Patient patient = new Patient();
		String expected  ="Sai";
		patient.setFirstName(expected);
		Assertions.assertEquals(expected,patient.getFirstName());
	}

	@Test
	void setLastName() {
		Patient patient = new Patient();
		String expected  ="Srinivas";
		patient.setLastName(expected);
		Assertions.assertEquals(expected,patient.getLastName());
	}

	@Test
	void setEmail() {
		Patient patient = new Patient();
		String expected  ="Sai@gmail.com";
		patient.setEmail(expected);
		Assertions.assertEquals(expected,patient.getEmail());
	}

	@Test
	void setDescription() {
		Patient patient = new Patient();
		String expected  ="Sai is headache";
		patient.setDescription(expected);
		Assertions.assertEquals(expected,patient.getDescription());
	}

	@Test
	void setDoctorName() {
		Patient patient = new Patient();
		String expected  ="Dr. Sai";
		patient.setDoctorName(expected);
		Assertions.assertEquals(expected,patient.getDoctorName());
	}

	@Test
	void setAge() {
		Patient patient = new Patient();
		int expected =22;
		patient.setAge(expected);
		Assertions.assertEquals(expected,patient.getAge());
	}

	@Test
	void setPhoneNumber() {
		Patient patient = new Patient();
		int expected =924531678;
		patient.setPhoneNumber(expected);
		Assertions.assertEquals(expected,patient.getPhoneNumber());
	}

	@Test
	void setStatus() {
		Patient patient = new Patient();
		String expected  ="active";
		patient.setStatus(expected);
		Assertions.assertEquals(expected,patient.getStatus());
	}

	@Test
	void toStringTest(){
		String expected  =
				"Patient{" +
						"id=1"+
						", firstName='Sai"+ '\'' +
						", lastName='Srinivas"+ '\'' +
						", age=22"+
						", phoneNumber=924531678" +
						", email='Sai@gmail.com"+ '\'' +
						", description='Sai is headache"+ '\'' +
						", doctor_name='Dr. Sai"+ '\'' +
						", status='active"+ '\'' +
						'}';
		Patient patient = new Patient(1,"Sai","Srinivas",22,924531678,
				"Sai@gmail.com","Sai is headache","Dr. Sai","active");
		Assertions.assertEquals(expected,patient.toString());
	}

}
