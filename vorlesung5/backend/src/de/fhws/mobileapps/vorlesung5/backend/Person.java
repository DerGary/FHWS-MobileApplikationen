package de.fhws.mobileapps.vorlesung5.backend;

public class Person {

	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private String url;

	
	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.url = "http://lorempixel.com/300/300/people/";
	}

	public long getId() {
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
	
	public String toString()
	{
		return Long.toString(this.id) + ";" + this.firstName + ";" + this.lastName + ";" + this.url;
	}
	

}
