package org.krishna.demo.service.impl;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.krishna.demo.dao.impl.EmployeeDaoImpl;
import org.krishna.demo.exceptions.EmployeeNotFoundException;
import org.krishna.demo.exceptions.EmployeeServiceException;
import org.krishna.demo.model.Employee;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {
	
	@Mock
	private EmployeeDaoImpl employeeDaoMock;
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	@Before
	public  void setup() throws IOException {
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
		emp.setId(1L);
		when(employeeDaoMock.getEmployee(1L)).thenReturn(emp);
		assertEquals(emp.getId(), this.employeeService.getEmployee(1L).getId());
	}

	@Test(expected=EmployeeServiceException.class)
	public void test_getEmployee_null() {
		employeeService.getEmployee(null);
	}
	
	@Test 
	public void test_findEmployees() {
		List<Employee> empList= new ArrayList<Employee>();
		empList.add(new Employee());
		
		when(employeeDaoMock.findEmployees("search")).thenReturn(empList);
		assertEquals(empList.size(), this.employeeService.findEmployees("search").size()) ;
	}
	
	@Test(expected=EmployeeNotFoundException.class) 
	public void test_findEmployees_exception() {

		when(employeeDaoMock.findEmployees("search")).thenReturn(new ArrayList<Employee>());
		assertEquals(new ArrayList<Employee>(), this.employeeService.findEmployees("search")) ;
	}

	@Test(expected=EmployeeServiceException.class)
	public void test_findEmployees_null() {
		employeeService.findEmployees(null);
	}
	
	@Test 
	public void test_addEmployee() {		
		Employee emp = new Employee();
		emp.setId(1L);
		
		when(employeeDaoMock.addEmployee(Mockito.any(Employee.class))).thenReturn(emp);
		assertEquals(1, this.employeeService.addEmployee(new Employee()).getId().intValue()) ;
	}
	
	@Test(expected=EmployeeServiceException.class) 
	public void test_faddEmployee_exception() {
		this.employeeService.addEmployee(null);
	}
	
	@Test 
	public void test_updateEmployee() {		
		Employee emp = new Employee();
		emp.setFirstName("firstName");;
		
		when(employeeDaoMock.updateEmployee(Mockito.anyLong(), Mockito.any(Employee.class))).thenReturn(emp);
		assertEquals("firstName", this.employeeService.updateEmployee(1L, new Employee()).getFirstName()) ;
	}
	
	@Test(expected=EmployeeServiceException.class) 
	public void test_updateEmployee_id_null() {
		this.employeeService.updateEmployee(null, new Employee());
	}
	
	@Test(expected=EmployeeServiceException.class) 
	public void test_updateEmployee_employee_null() {
		this.employeeService.updateEmployee(1L, null);
	}
	
	@Test(expected=EmployeeServiceException.class) 
	public void test_deleteEmployee_id_null() {
		this.employeeService.deleteEmployee(null);
	}
}
