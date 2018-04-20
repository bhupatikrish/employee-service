package org.krishna.demo.controllers;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.krishna.demo.model.Employee;
import org.krishna.demo.model.EmployeeResponse;
import org.krishna.demo.service.EmployeeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Mock
	private EmployeeService employeeServiceMock;
	@InjectMocks
	private EmployeeController employeeController;
	
	private MockMvc mockMvc;
	
	private final static String uri = "/api/employees";
	private final static String mediaType = "application/json;charset=UTF-8";
	
	@Before
	public  void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}
	
	@Test
	public void test_getAllEmployees() throws Exception {
		List<Employee> employeesMock = new ArrayList<Employee>();
		EmployeeResponse responseMock = new EmployeeResponse();
		responseMock.getMetadata().setSuccessOutcome();
		responseMock.setResponseData(employeesMock);
		
		when(employeeServiceMock.getAllEmployees()).thenReturn(employeesMock);
		assertEquals(responseMock.getMetadata().getStatus(), this.employeeController.getAllEmployees().getMetadata().getStatus());

		this.mockMvc.perform(get(EmployeeControllerTest.uri))
			//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(EmployeeControllerTest.mediaType))
            .andExpect(jsonPath("$.metadata.message").value("Success"));
	}
	
	
	@Test
	public void test_getEmployee() throws Exception {
		Employee emp = new Employee();
		emp.setId(1L);
		EmployeeResponse responseMock = new EmployeeResponse();
		responseMock.getMetadata().setSuccessOutcome();
		responseMock.setResponseData(emp);
		
		when(employeeServiceMock.getEmployee(1L)).thenReturn(emp);
		assertEquals(responseMock.getMetadata().getStatus(), this.employeeController.getEmployee("1").getMetadata().getStatus());
		
		this.mockMvc.perform(get(EmployeeControllerTest.uri+ "/1"))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(EmployeeControllerTest.mediaType))
		.andExpect(jsonPath("$.metadata.message").value("Success"))
		.andExpect(jsonPath("$.responseData.id").value(1));
	}
	
	@Test
	public void test_getEmployee_invalidId() throws Exception {
		
		this.mockMvc.perform(get(EmployeeControllerTest.uri+ "/abc"))
		//.andDo(print())
		.andExpect(status().is4xxClientError())
		.andExpect(jsonPath("$.metadata.message").value("Id Not valid"));
	}

	@Test
	public void test_findEmployee() throws Exception {
		Employee emp = new Employee();
		emp.setFirstName("John");
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(emp);
		
		EmployeeResponse responseMock = new EmployeeResponse();
		responseMock.getMetadata().setSuccessOutcome();
		responseMock.setResponseData(empList);
		
		when(employeeServiceMock.findEmployees(Mockito.anyString())).thenReturn(empList);
		List<Employee> response = (List<Employee>) this.employeeController.findEmployee("John").getResponseData();
		assertEquals(responseMock.getMetadata().getStatus(), this.employeeController.findEmployee("John").getMetadata().getStatus());
		assertEquals("John", response.get(0).getFirstName());
		
		this.mockMvc.perform(get(EmployeeControllerTest.uri+ "/search?q=Robert"))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(EmployeeControllerTest.mediaType))
		.andExpect(jsonPath("$.metadata.message").value("Success"))
		.andExpect(jsonPath("$.responseData[0].firstName").value("John"));
	}
	
	@Test
	public void test_findEmployee_noSearh() throws Exception {
		
		this.mockMvc.perform(get(EmployeeControllerTest.uri+ "/search"))
		//.andDo(print())
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void test_addEmployee() throws Exception {
		Employee emp = new Employee();
		emp.setFirstName("firstName");
		emp.setLastName("lastName");
		emp.setAddress1("address1");
		emp.setCity("city");
		emp.setSalary(121212.00);
		emp.setState("state");
		emp.setTitle("title");
		emp.setZipcode(12323);
		
		EmployeeResponse responseMock = new EmployeeResponse();
		responseMock.getMetadata().setSuccessOutcome();
		responseMock.setResponseData(emp);
		
		when(employeeServiceMock.addEmployee(Mockito.any())).thenReturn(emp);
		Employee response = (Employee) this.employeeController.addEmployee(emp).getResponseData();
		assertEquals(responseMock.getMetadata().getStatus(), this.employeeController.addEmployee(emp).getMetadata().getStatus());
		assertEquals("firstName", response.getFirstName());
		
		ObjectMapper jsonMapper = new ObjectMapper();
		
		this.mockMvc.perform(post(EmployeeControllerTest.uri)
				.content(jsonMapper.writeValueAsString(emp))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(EmployeeControllerTest.mediaType))
		.andExpect(jsonPath("$.metadata.message").value("Success"))
		.andExpect(jsonPath("$.responseData.firstName").value("firstName"));
	}
	
	@Test
	public void test_addEmployee_invalid() throws Exception {
		Employee emp = new Employee();

		
		ObjectMapper jsonMapper = new ObjectMapper();
		
		this.mockMvc.perform(post(EmployeeControllerTest.uri)
				.content(jsonMapper.writeValueAsString(emp))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void test_updateEmployee() throws Exception {
		Employee emp = new Employee();
		emp.setId(1L);
		emp.setFirstName("firstName");
		emp.setLastName("lastName");
		emp.setAddress1("address1");
		emp.setCity("city");
		emp.setSalary(121212.00);
		emp.setState("state");
		emp.setTitle("title");
		emp.setZipcode(12323);
		
		EmployeeResponse responseMock = new EmployeeResponse();
		responseMock.getMetadata().setSuccessOutcome();
		responseMock.setResponseData(emp);
		
		when(employeeServiceMock.updateEmployee(Mockito.any(), Mockito.any())).thenReturn(emp);
		Employee response = (Employee) this.employeeController.updateEmployee("1", emp).getResponseData();
		assertEquals(responseMock.getMetadata().getStatus(), this.employeeController.addEmployee(emp).getMetadata().getStatus());
		assertEquals("firstName", response.getFirstName());
		
		ObjectMapper jsonMapper = new ObjectMapper();
		
		this.mockMvc.perform(put(EmployeeControllerTest.uri + "/1")
				.content(jsonMapper.writeValueAsString(emp))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(EmployeeControllerTest.mediaType))
		.andExpect(jsonPath("$.metadata.message").value("Success"))
		.andExpect(jsonPath("$.responseData.firstName").value("firstName"));
	}
	
	@Test
	public void test_updateEmployee_invalid() throws Exception {
		Employee emp = new Employee();
		ObjectMapper jsonMapper = new ObjectMapper();
		
		this.mockMvc.perform(put(EmployeeControllerTest.uri + "/1")
				.content(jsonMapper.writeValueAsString(emp))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void test_delete() throws Exception {
		this.mockMvc.perform(delete(EmployeeControllerTest.uri + "/1"))
		.andDo(print())
		.andExpect(status().isOk());
	}
}
