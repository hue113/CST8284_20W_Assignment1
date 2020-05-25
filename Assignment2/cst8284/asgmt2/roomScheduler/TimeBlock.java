package cst8284.asgmt2.roomScheduler;
/*  Course Name: CST8284
	Author: Pham Thanh Hue
	Class name: TimeBlock.java
	Date: March 02, 2020
*/

import java.io.Serializable;
import java.util.Calendar;

public class TimeBlock implements Serializable{
	private Calendar startTime, endTime;

	public TimeBlock() {
    	this(
    			new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 8).build(),
				new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 24).build());
	}
	
	public TimeBlock(Calendar startTime, Calendar endTime) {
		setStartTime(startTime);
		setEndTime(endTime);
	}
	
	public boolean overlaps(TimeBlock newBlock) {
		if (newBlock.startTime.get(Calendar.YEAR) == startTime.get(Calendar.YEAR)
			&& newBlock.startTime.get(Calendar.DAY_OF_MONTH) == startTime.get(Calendar.DAY_OF_MONTH)
            && newBlock.startTime.get(Calendar.MONTH) == startTime.get(Calendar.MONTH));{
                return ((newBlock.getStartTime().get(Calendar.HOUR_OF_DAY) < getEndTime().get(Calendar.HOUR_OF_DAY))
                		&& (newBlock.getEndTime().get(Calendar.HOUR_OF_DAY) > getStartTime().get(Calendar.HOUR_OF_DAY))) ;
		}
	}
	
	public int duration() {
		return getEndTime().get(Calendar.HOUR_OF_DAY) - getStartTime().get(Calendar.HOUR_OF_DAY);
	}
	
	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	
	public String toString() {
		return getStartTime().get(Calendar.HOUR_OF_DAY) +":00 - "+ getEndTime().get(Calendar.HOUR_OF_DAY) +":00\n";
	}
	
	
}
