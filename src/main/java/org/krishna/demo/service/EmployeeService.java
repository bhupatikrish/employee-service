package org.krishna.demo.service;

import java.util.List;

import org.krishna.demo.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();
	public Employee getEmployee(Long id);
	public List<Employee> findEmployees(String search);
	public Employee addEmployee(Employee employee);
	public Employee updateEmployee(Long id, Employee employee);
	public void deleteEmployee(Long id);
}
