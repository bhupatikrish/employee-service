package org.krishna.demo.controllers;

import java.util.List;

import org.krishna.demo.model.Employee;
import org.krishna.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public List<Employee> getAllEmployees() {
		return this.employeeService.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable Long id) {
		return this.employeeService.getEmployee(id);
	}
	
	@GetMapping("/search")
	public List<Employee> findEmployee(@RequestParam("q") String search) {
		return this.employeeService.findEmployees(search);
	}
	
	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee) {
		return this.employeeService.addEmployee(employee);
	}
	
	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		return this.employeeService.updateEmployee(id, employee);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		this.employeeService.deleteEmployee(id);
	}
}
