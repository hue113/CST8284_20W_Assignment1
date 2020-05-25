package cst8284.asgmt2.roomScheduler;
/*  Course Name: CST8284
	Author: Pham Thanh Hue
	Class name: RoomBooking.java
	Date: March 02, 2020
*/

public class RoomBooking {
	private ContactInfo contactInfo;
	private Activity activity;
	private TimeBlock timeBlock;
	
	public static final long serialVersionUID = 1L;
	
	public RoomBooking(TimeBlock timeBlock, ContactInfo contactInfo, Activity activity) {
		setTimeBlock(timeBlock);
		setContactInfo(contactInfo);
		setActivity(activity);
	}
	
	public RoomBooking() {
	}
	
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	public TimeBlock getTimeBlock() {
		return timeBlock;
	}
	public void setTimeBlock(TimeBlock timeBlock) {
		this.timeBlock = timeBlock;
	}
	
	public String toString() {
		return "------------------\n" + 
				getTimeBlock().toString() + 
				getContactInfo().toString() + 
				getActivity().toString() + 
				"------------------\n";
	}
	
}
