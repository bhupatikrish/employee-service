package org.krishna.demo.service.impl;

import java.util.List;

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
		Employee emp = this.employeeDao.getEmployee(id);
		if(null == emp)
			throw new EmployeeNotFoundException();
		return emp;
	}

	@Override
	public List<Employee> findEmployees(String search) {
		List<Employee> employees = this.employeeDao.findEmployees(search);
		if(null == employees || employees.isEmpty())
			throw new EmployeeNotFoundException();
		return employees;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		if(null == employee)
			throw new EmployeeServiceException();
		return this.employeeDao.addEmployee(employee);
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		return this.employeeDao.updateEmployee(id, employee);
	}

	@Override
	public void deleteEmployee(Long id) {
		this.employeeDao.deleteEmployee(id);
	}

}
