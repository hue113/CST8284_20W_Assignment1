package cst8284.asgmt1.roomScheduler;

/* Course Name: CST8284 
 * Student Name: Pham Thanh Hue 
 * Class Name: 310
 * Date: 10 Feb 2020
 */

public class RoomBooking {

    private ContactInfo contactInfo;
    private Activity activity;
    private TimeBlock timeblock;

    public RoomBooking(TimeBlock timeblock, ContactInfo contactInfo, Activity activity) {
        setContactInfo(contactInfo);
        setActivity(activity);
        setTimeBlock(timeblock);
    }

    public void setContactInfo(ContactInfo contactInfo) {
    	this.contactInfo = contactInfo;
    }
    
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setActivity(Activity activity) {
    	this.activity = activity;
    }
    
    public Activity getActivity() {
        return activity;
    }

    public void setTimeBlock (TimeBlock timeblock) {
    	this.timeblock = timeblock;
    }
    
    public TimeBlock getTimeblock() {
        return timeblock;
    }

    public String toString() {
        return "------------------\n"+ timeblock.toString() + activity.toString() + contactInfo.toString()+"\n------------------\n";

    }
}