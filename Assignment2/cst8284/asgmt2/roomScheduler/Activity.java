package cst8284.asgmt2.roomScheduler;
/*  Course Name: CST8284
	Author: Pham Thanh Hue
	Class name: Activity.java
	Date: March 02, 2020
*/

import java.io.Serializable;

public class Activity implements Serializable{
	private String description, category;
	
	public Activity (String category, String description) {
		this.setCategory(category);
		this.setDescription(description);
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	/****************************************************************************
	* Title: CST8284 Assignment1 code
	* Author: Dave Houtman
	* Date: Feb 2020
	* Code version: 1.0
	* Availability: Algonquin 20W_CST8284_310 Object Oriented Programming (Java)
	*****************************************************************************/
	public String toString() {
		return  "Event: " + getCategory() + "\n" + 
			((getDescription()!="")?"Description: " + getDescription():"") + "\n";
	}
	
	
	
	
	
	
}
