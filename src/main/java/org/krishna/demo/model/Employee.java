package org.krishna.demo.model;

public class Employee {

	private Long id;
	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private Integer zipcode;
	private Double salary;
	private String title;
	private String avatar;
	private String image;
	
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

	public void setId(Long id) {
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

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
