package org.krishna.demo.model;

public class Employee {

	private Long id;
	private String firstName;
	private String lastName;
	private Integer zipcode;
	private Double salary;
	private String title;
	
	public Employee() {
		super();
	}
	
	public Employee(long id, String firstName, String lastName, int zipcode, double salary, String title) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.zipcode = zipcode;
		this.salary = salary;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
