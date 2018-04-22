package org.krishna.demo.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.krishna.demo.exceptions.EmployeeServiceException;
import org.krishna.demo.model.Employee;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeDaoTest {
	
	private static final String inputFile = "employees.json";
	private List<Employee> employeesMock;

	@InjectMocks
	private EmployeeDaoImpl employeeDao;
	
	@Before
	public  void setup() throws JsonParseException, JsonMappingException, IOException {
		MockitoAnnotations.initMocks(this);
		ObjectMapper jsonMapper = new ObjectMapper();
		InputStream ioStream = EmployeeDaoTest.class.getResourceAsStream("/" + inputFile);
		this.employeesMock = jsonMapper.readValue(ioStream, new TypeReference<List<Employee>>() {});
		this.employeeDao.setEmployees(employeesMock);
	}
	
	@Test
	public void test_getAllEmployees() throws JsonParseException, JsonMappingException, IOException {
		assertEquals(employeesMock, this.employeeDao.getAllEmployees());
	}

	@Test
	public void test_getEmployee() {
		assertEquals(this.employeesMock.get(0), this.employeeDao.getEmployee(1L));
	}
	
	@Test
	public void test_findEmployeess() {
		assertEquals(1, this.employeeDao.findEmployees("Robert").size());
	}
	
	@Test
	public void test_findEmployees_zeroResult() {
		assertEquals(0, this.employeeDao.findEmployees("NoResults").size());
	}
	
	@Test
	public void test_findEmployees_null() {
		assertEquals(0, this.employeeDao.findEmployees(null).size());
	}
	
	@Test
	public void test_addEmployees() {
		Employee emp = new Employee();
		emp.setFirstName("firstName");
		emp.setLastName("lastName");
		emp.setAddress1("address1");
		emp.setAddress2("address2");
		emp.setCity("city");
		emp.setState("state");
		emp.setTitle("title");
		emp.setSalary(120000.00);
		
		int size = this.employeesMock.size();
		Employee result = this.employeeDao.addEmployee(emp);
		assertNotNull(result.getId());
		assertEquals(size+1, this.employeeDao.getAllEmployees().size());
	}
	
	@Test(expected=EmployeeServiceException.class)
	public void test_addEmployees_null() throws Exception {
		this.employeeDao.addEmployee(null);
	}
	
	@Test
	public void test_updateEmployee() {
		Employee emp = this.employeesMock.get(0);
		emp.setFirstName("updated_firstname");
		Employee result = this.employeeDao.updateEmployee(emp.getId(), emp);
		
		assertEquals("updated_firstname", result.getFirstName());
	}
	
	@Test(expected=EmployeeServiceException.class)
	public void test_updateEmployee_id_null() throws Exception {
		this.employeeDao.updateEmployee(null, new Employee());
	}
	
	@Test(expected=EmployeeServiceException.class)
	public void test_updateEmployee_employee_null() throws Exception {
		this.employeeDao.updateEmployee(1L, null);
	}
	
	@Test
	public void test_DeleteEmployee() {
		Employee emp = this.employeesMock.get(0);
		int size = this.employeesMock.size();
		this.employeeDao.deleteEmployee(emp.getId());
		
		assertEquals(size-1, this.employeeDao.getAllEmployees().size());
	}
	
	@Test(expected=EmployeeServiceException.class)
	public void test_deleteEmployee_id_null() throws Exception {
		this.employeeDao.deleteEmployee(null);
	}
	
}
