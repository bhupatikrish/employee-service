package org.krishna.demo.service.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang.StringUtils;
import org.krishna.demo.model.Employee;
import org.krishna.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static List<Employee> employees = new LinkedList<Employee>(Arrays.asList(
			new Employee(1L, "FristName1", "LastName1", 27500, 122000.00, "Solution Architect"),
			new Employee(2L, "FristName2", "LastName2", 24512, 95000.00, "Senior Software Engineer")
				));
	private static Long count = 2L;

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
				StringUtils.containsIgnoreCase(employee.getLastName(), search);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		employee.setId(++count);
		employees.add(employee);
		return employee;
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
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
		int index = IntStream.range(0, employees.size())
                .filter(i -> id == employees.get(i).getId())
                .findFirst().orElse(-1);
		
		if(index > -1) {
			employees.remove(index);
		}
	}

}
