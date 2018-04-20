package org.krishna.demo.controllers;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.krishna.demo.exceptions.EmployeeNotFoundException;
import org.krishna.demo.exceptions.EmployeeServiceException;
import org.krishna.demo.exceptions.EmployeeServiceValidationError;
import org.krishna.demo.model.Employee;
import org.krishna.demo.model.EmployeeResponse;
import org.krishna.demo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author krishna
 * EmployeeController is an api to get, add, delete, find and update employees. 
 */
@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	/*
	 * Employee service
	 */
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * This method will return all the employees fund in the system.
	 * 
	 * @return all the employees found in the system
	 * @return empty responseData if no users are found.
	 */
	@GetMapping
	public EmployeeResponse getAllEmployees() {
		LOGGER.debug("getAllEmployees start");
		EmployeeResponse response = new EmployeeResponse();
		response.getMetadata().setSuccessOutcome();
		
		LOGGER.debug("getAllEmployees from service");
		response.setResponseData(this.employeeService.getAllEmployees());
		LOGGER.debug("getAllEmployees done");
		
		return response;
	}
	
	/**
	 * This method will return the employee for the provied id.
	 * 
	 * @param id: Employee id
	 * @return employee if found else returns just the metadata
	 * 
	 */
	@GetMapping("/{id}")
	public EmployeeResponse getEmployee(@PathVariable String id) {
		LOGGER.debug("getEmployee id:", id);
		EmployeeResponse response = new EmployeeResponse();
		if(!StringUtils.isNumeric(id)) {
			LOGGER.debug("getEmployee validation failed");
			throw new EmployeeServiceValidationError("Id Not valid");
		}
		
		response.getMetadata().setSuccessOutcome();
		
		LOGGER.debug("getEmployee from service");
		response.setResponseData(this.employeeService.getEmployee(Long.parseLong(id)));
		LOGGER.debug("getEmployee done");
		
		return response;
	}
	
	/**
	 * This method returns the list of users found for the provided search string.
	 * It searches by comparing the provided query string to certain attributes of employee.
	 * 
	 * @param search: query string. Example: part of user first name. "krish"
	 * @return List of employees found else return null in responseData.
	 */
	@GetMapping("/search")
	public EmployeeResponse findEmployee(@RequestParam("q") String search) {
		LOGGER.debug("findEmployee search:", search);
		EmployeeResponse response = new EmployeeResponse();
		if(StringUtils.isEmpty(search)) {
			LOGGER.debug("findEmployee validation failed");
			throw new EmployeeServiceValidationError("Search string not valid");
		}
		response.getMetadata().setSuccessOutcome();
		
		LOGGER.debug("findEmployee from service");
		response.setResponseData(this.employeeService.findEmployees(search));
		LOGGER.debug("findEmployee done");
		
		return response;
	}
	
	/**
	 * Adds the employee to the system.
	 * @param employee: employee to be added to the system
	 * @return created employee
	 */
	@PostMapping
	public EmployeeResponse addEmployee(@Valid @RequestBody Employee employee) {
		LOGGER.debug("addEmployee start:", employee.toString());
		EmployeeResponse response = new EmployeeResponse();
		response.getMetadata().setSuccessOutcome();
		
		LOGGER.debug("addEmployee using service");
		response.setResponseData(this.employeeService.addEmployee(employee));
		LOGGER.debug("addEmployee done");
		
		return response;
	}
	
	/**
	 * Updates the employee.
	 * 
	 * @param id: Employee id that need to be updated
	 * @param employee: Employee data to be updated
	 * @return updated employee
	 */
	@PutMapping("/{id}")
	public EmployeeResponse updateEmployee(@PathVariable String id, @Valid @RequestBody Employee employee) {
		LOGGER.debug("updateEmployee id:", id);
		EmployeeResponse response = new EmployeeResponse();
		if(!StringUtils.isNumeric(id)) {
			LOGGER.debug("updateEmployee validation failed");
			throw new EmployeeServiceValidationError("Id not valid");
		}
		response.getMetadata().setSuccessOutcome();
		
		LOGGER.debug("updateEmployee using service");
		response.setResponseData(this.employeeService.updateEmployee(Long.parseLong(id), employee));
		LOGGER.debug("updateEmployee done");
		
		return response;
	}
	
	/**
	 * Deletes the employee for the provided id
	 * @param id: Employee id
	 * @return none in responseData
	 */
	@DeleteMapping("/{id}")
	public EmployeeResponse deleteEmployee(@PathVariable String id) {
		LOGGER.debug("deleteEmployee id:", id);
		EmployeeResponse response = new EmployeeResponse();
		if(!StringUtils.isNumeric(id)) {
			LOGGER.debug("deleteEmployee validation failed");
			throw new EmployeeServiceValidationError("Id not valid");
		}
		response.getMetadata().setSuccessOutcome();
		
		LOGGER.debug("deleteEmployee using service");
		this.employeeService.deleteEmployee(Long.parseLong(id));
		LOGGER.debug("deleteEmployee done");
		
		return response;
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<EmployeeResponse> notFoundExceptionHandler(Exception ex, WebRequest request) {
		EmployeeResponse response = new EmployeeResponse();
		response.getMetadata().setNotFoundOutcome();
		return new ResponseEntity<EmployeeResponse>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmployeeServiceValidationError.class)
	public ResponseEntity<EmployeeResponse> validationExceptionHandler(Exception ex, WebRequest request) {
		EmployeeResponse response = new EmployeeResponse();
		response.getMetadata().setValidationErrorOutcome();
		response.getMetadata().setMessage(ex.getMessage());;
		return new ResponseEntity<EmployeeResponse>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmployeeServiceException.class)
	public ResponseEntity<EmployeeResponse> exceptionHandler(Exception ex, WebRequest request) {
		EmployeeResponse response = new EmployeeResponse();
		response.getMetadata().setErrorOutcome();
		response.getMetadata().setMessage(ex.getMessage());;
		return new ResponseEntity<EmployeeResponse>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
