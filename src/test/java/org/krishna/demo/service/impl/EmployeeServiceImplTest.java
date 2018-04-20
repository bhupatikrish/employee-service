package org.krishna.demo.service.impl;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.krishna.demo.dao.EmployeeDao;
import org.krishna.demo.model.Employee;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {
	
	@Mock
	private EmployeeDao employeeDaoMock;
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	@Before
	public  void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_getAllEmployees() {
		when(employeeDaoMock.getAllEmployees()).thenReturn(new ArrayList<Employee>());
		assertEquals(new ArrayList<Employee>(), this.employeeService.getAllEmployees()) ;
	}
	
	@Test
	public void test_getEmployee() {
		Employee emp = new Employee();
		when(employeeDaoMock.getEmployee(1L)).thenReturn(emp);
		assertEquals(emp, this.employeeService.getEmployee(1L));
	}

	@Test
	public void test_getEmployee_null() {
		when(employeeDaoMock.getEmployee(null)).thenReturn(null);
		assertEquals(null, this.employeeService.getEmployee(null));
	}
}
