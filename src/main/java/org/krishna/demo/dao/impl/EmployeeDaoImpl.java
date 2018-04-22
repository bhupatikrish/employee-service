package org.krishna.demo.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.krishna.demo.dao.EmployeeDao;
import org.krishna.demo.exceptions.EmployeeServiceException;
import org.krishna.demo.model.Employee;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeDaoImpl implements EmployeeDao {
	private String inputFile = "employees.json";
	
	private static List<Employee> employees; 
	private static Long count = 7L;
	
	@PostConstruct
	public void init() throws IOException {
		ObjectMapper jsonMapper = new ObjectMapper();
		InputStream ioStream = EmployeeDaoImpl.class.getResourceAsStream("/" + inputFile);
		EmployeeDaoImpl.employees = jsonMapper.readValue(ioStream, new TypeReference<List<Employee>>() {});
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employees;
	}

	@Override
	public Employee getEmployee(Long id) {
		return employees.stream()
				.filter(emp -> id == emp.getId())
				.findAny()
				.orElse(null);
	}

	@Override
	public List<Employee> findEmployees(String search) {
		return employees.stream()
				.filter(emp -> this.searchFilter(search, emp))
				.collect(Collectors.toList());
	}
	
	private boolean searchFilter(String search, Employee employee) {
		return StringUtils.containsIgnoreCase(employee.getFirstName(), search) ||
				StringUtils.containsIgnoreCase(employee.getLastName(), search) ||
				StringUtils.containsIgnoreCase(employee.getTitle(), search) ||
				StringUtils.containsIgnoreCase(employee.getAddress1(), search) ||
				StringUtils.containsIgnoreCase(employee.getCity(), search) ||
				StringUtils.containsIgnoreCase(employee.getState(), search) ||
				(NumberUtils.isNumber(search) && employee.getSalary().compareTo(Double.parseDouble(search)) > 0);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		if(null == employee)
			throw new EmployeeServiceException("Employee cannot be null");
		employee.setId(++count);
		employees.add(employee);
		return employee;
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		if(null == id)
			throw new EmployeeServiceException("Id cannot be empty or null");
		if(null == employee)
			throw new EmployeeServiceException("Id cannot be null");
		
		int index = IntStream.range(0, employees.size())
                .filter(i -> id == employees.get(i).getId())
                .findFirst().orElse(-1);
		
		if(index == -1) {
			return null;
		}
		else {
			employee.setId(id);
			employees.set(index, employee);
			return employee;
		}
	}

	@Override
	public void deleteEmployee(Long id) {
		if(null == id)
			throw new EmployeeServiceException("Id cannot be null");
		
		int index = IntStream.range(0, employees.size())
                .filter(i -> id == employees.get(i).getId())
                .findFirst().orElse(-1);
		
		if(index > -1) {
			employees.remove(index);
		}
	}

	/*
	 * Only used for unit tests since there is no straight way to mock @PostConstruct
	 */
	protected void setEmployees(List<Employee> employees) {
		EmployeeDaoImpl.employees = employees;
	}

}
