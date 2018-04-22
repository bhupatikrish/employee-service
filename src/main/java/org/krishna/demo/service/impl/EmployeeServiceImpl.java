package org.krishna.demo.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.krishna.demo.dao.EmployeeDao;
import org.krishna.demo.exceptions.EmployeeNotFoundException;
import org.krishna.demo.exceptions.EmployeeServiceException;
import org.krishna.demo.model.Employee;
import org.krishna.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Employee> getAllEmployees() {
		return this.employeeDao.getAllEmployees();
	}

	@Override
	public Employee getEmployee(Long id) {
		if(null == id)
			throw new EmployeeServiceException("Id cannot be null.");
		
		Employee emp = this.employeeDao.getEmployee(id);
		if(null == emp)
			throw new EmployeeNotFoundException();
		return emp;
	}

	@Override
	public List<Employee> findEmployees(String search) {
		if(StringUtils.isEmpty(search))
			throw new EmployeeServiceException("Search cannot be empty.");
		
		List<Employee> employees = this.employeeDao.findEmployees(search);
		if(null == employees || employees.isEmpty())
			throw new EmployeeNotFoundException();
		return employees;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		if(null == employee)
			throw new EmployeeServiceException("Employee cannot be empty");
		return this.employeeDao.addEmployee(employee);
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		if(null == id)
			throw new EmployeeServiceException("Id cannot be null.");
		if(null == employee)
			throw new EmployeeServiceException("Employee cannot be empty");
		
		return this.employeeDao.updateEmployee(id, employee);
	}

	@Override
	public void deleteEmployee(Long id) {
		if(null == id)
			throw new EmployeeServiceException("Id cannot be null.");
		
		this.employeeDao.deleteEmployee(id);
	}

}
