package cst8284.asgmt1.roomScheduler;

/* Course Name: CST8284 
 * Student Name: Pham Thanh Hue 
 * Class Name: 310
 * Date: 10 Feb 2020
 */

public class ContactInfo {

    private String firstName, lastName, phoneNumber, organization = "Algonquin College";

    public ContactInfo(String firstName, String lastName, String phoneNumber,String organiztion) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setOrganization(organiztion);
    }

    public ContactInfo(String firstName, String lastName, String phoneNumber) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhoneNumber(phoneNumber);
    
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOrganization() {
        return organization;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String toString() {
        return "Contact Information: " + getFirstName() + " " + getLastName() + "\nPhone: " + getPhoneNumber() + "\n" + getOrganization();
    }
}
