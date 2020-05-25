package cst8284.asgmt2.roomScheduler;
/*  Course Name: CST8284
	Author: Pham Thanh Hue
	Class name: ContactInfo.java
	Date: March 02, 2020
*/

import java.io.Serializable;

public class ContactInfo implements Serializable{
	private String firstName, lastName, phoneNumber, organization;
	
	public ContactInfo (String firstName, String lastName, String phoneNumber, String organization) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPhoneNumber(phoneNumber);
		this.setOrganization(organization);
	}
	
	public ContactInfo (String firstName, String lastName, String phoneNumber) {
		this(firstName, lastName, phoneNumber, "Algonquin College");
		
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	/****************************************************************************
	* Title: CST8284 Assignment1 code
	* Author: Dave Houtman
	* Date: Feb 2020
	* Code version: 1.0
	* Availability: Algonquin 20W_CST8284_310 Object Oriented Programming (Java)
	*****************************************************************************/
	public String toString() {
		return "Contact Information: " +
				((getFirstName()!="")?(getFirstName() + " " + getLastName()) : "") + 
				"\n" + "Phone: " + getPhoneNumber() +  
				((getOrganization().equals(""))?"":("\n" +getOrganization() + "\n"));
		}
	
	
}
