package cst8284.asgmt1.roomScheduler;

/* Course Name: CST8284 
 * Student Name: Pham Thanh Hue 
 * Class Name: 310
 * Date: 10 Feb 2020
 */

import java.util.Calendar;

public class TimeBlock {

    private Calendar startTime, endTime;

    public TimeBlock() {
    	startTime.set(Calendar.HOUR_OF_DAY, 8);
    	endTime.set(Calendar.HOUR_OF_DAY, 23);
    }

    public TimeBlock(Calendar start, Calendar end) {
        this.setStartTime(start);
        this.setEndTime(end);
    }

    public boolean overlaps(TimeBlock newBlock) {			// use this method for findBooking()
        // short cut to check if ... return true;
//      	if (newBlock.startTime.get(Calendar.ERA) == startTime.get(Calendar.ERA)
//                && newBlock.startTime.get(Calendar.YEAR) == startTime.get(Calendar.YEAR)
//                && newBlock.startTime.get(Calendar.DAY_OF_MONTH) == startTime.get(Calendar.DAY_OF_MONTH)
//                && newBlock.startTime.get(Calendar.HOUR_OF_DAY) == startTime.get(Calendar.HOUR_OF_DAY)
//                && newBlock.startTime.get(Calendar.MONTH) == startTime.get(Calendar.MONTH))
//      	{ return true;
//      	} else {return false;}
//    }	
    	return (newBlock.startTime.get(Calendar.ERA) == startTime.get(Calendar.ERA)
                && newBlock.startTime.get(Calendar.YEAR) == startTime.get(Calendar.YEAR)
                && newBlock.startTime.get(Calendar.DAY_OF_MONTH) == startTime.get(Calendar.DAY_OF_MONTH)
                && newBlock.startTime.get(Calendar.HOUR_OF_DAY) == startTime.get(Calendar.HOUR_OF_DAY)
                && newBlock.startTime.get(Calendar.MONTH) == startTime.get(Calendar.MONTH));
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public int duration() {
        return getEndTime().get(Calendar.HOUR_OF_DAY)-getStartTime().get(Calendar.HOUR_OF_DAY);
    }

    public String toString() {     
        return getStartTime().get(Calendar.HOUR_OF_DAY)+ ":00 - " + getEndTime().get(Calendar.HOUR_OF_DAY) +":00 \n";
    }
}

