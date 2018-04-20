package org.krishna.demo.dao.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.krishna.demo.model.Employee;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
	
	private String inputFile = "employees.json";
	private List<Employee> employeesMock;
	
	@Mock
	private ObjectMapper jsonMapper;
	
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
}
