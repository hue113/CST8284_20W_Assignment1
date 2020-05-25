package cst8284.asgmt1.roomScheduler;

/* Course Name: CST8284 
 * Student Name: Pham Thanh Hue 
 * Class Name: 310
 * Date: 10 Feb 2020
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class RoomScheduler {

    private Scanner scan = new Scanner(System.in);
    private RoomBooking[] roomBookings;
    private int lastBookingIndex;

    private final int ENTER_ROOM_BOOKING = 1;
    private final int DISPLAY_BOOKING = 2;
    private final int DISPLAY_DAY_BOOKINGS = 3;
    private final int EXIT = 0;

    public RoomScheduler() {
        this.roomBookings = new RoomBooking[999];
    }

    public void launch() {
        int choice;
        do {
            choice = displayMenu();		// displayMenu return an integer from userChoice input 
            executeMenuItem(choice);
        } while (choice != 0);
    }

    
    private int displayMenu() {
        System.out.println("\nEnter a selection from the following menu:");
        System.out.println(
        		ENTER_ROOM_BOOKING + ".  Enter a room booking  \n"+
                DISPLAY_BOOKING + ".  Display a booking  \n"+
                DISPLAY_DAY_BOOKINGS + ".  Display room bookings for the whole day\n"
                + EXIT + ".  Exit program\n");
// no need to declare a local variable: int userChoice = scan.nextInt(); -- then return(userChoice); 
// can combine like this:
        return (scan.nextInt());
    }

    private void executeMenuItem(int choice) {
        switch (choice) {
            case ENTER_ROOM_BOOKING:
                saveRoomBooking(makeBookingFromUserInput());
                break;
            case DISPLAY_BOOKING:
                scan.nextLine();
                Calendar cal = makeCalendarFromUserInput(null, true);
                RoomBooking roomBooking = displayBooking(cal);
                if(roomBooking!=null)
                System.out.print(roomBooking.toString());
                else{
                   System.out.print("No booking scheduled between " + (cal.get(Calendar.HOUR_OF_DAY)) + ":00 and " + (cal.get(Calendar.HOUR_OF_DAY) + 1) + ":00 \n");
                }
                break;
            case DISPLAY_DAY_BOOKINGS:
                scan.nextLine();
                Calendar cal1 = makeCalendarFromUserInput(null, false);
                displayDayBookings(cal1);
            case EXIT:
                System.out.println("Exiting Room Booking Application");
        }
    }

    private boolean saveRoomBooking(RoomBooking booking) {
//        if (booking == null) {
//            return false;
//        }
        if (findBooking(booking.getTimeblock().getStartTime()) != null) {	// not null = already booked
            System.out.println("Sorry! Room booking for this time is not available");
            return false;			//false = not save
        }
//	    roomBookings[lastBookingIndex] = booking;
        roomBookings[getBookingIndex()] = booking;
        setBookingIndex(++lastBookingIndex);
        System.out.println("Booking time and date saved.");
        return true;
    }

    private RoomBooking displayBooking(Calendar cal) {
        for (int i = 0; i < getBookingIndex(); i++) {
            Calendar startTime = getRoomBookings()[i].getTimeblock().getStartTime();		// RoomBooking array --> TimeBlock inside RoomBooking --> getStartTime inside TimeBlock (a calendar) 
            Calendar endTime = getRoomBookings()[i].getTimeblock().getEndTime();

            if (startTime.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)
                    && startTime.get(Calendar.MONTH) == cal.get(Calendar.MONTH)
                    && startTime.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
                    && startTime.get(Calendar.HOUR_OF_DAY) <= cal.get(Calendar.HOUR_OF_DAY)
                    && endTime.get(Calendar.HOUR_OF_DAY) > cal.get(Calendar.HOUR_OF_DAY)) {
                return getRoomBookings()[i];
            }
        }
        return null;
    }

    private void displayDayBookings(Calendar cal) {

        ArrayList<RoomBooking> listBookingOfDay = new ArrayList<>();
        for (int i = 0; i < getBookingIndex(); i++) {
            Calendar startTime = getRoomBookings()[i].getTimeblock().getStartTime();
            Calendar endTime = getRoomBookings()[i].getTimeblock().getEndTime();

            if (startTime.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)
                    && startTime.get(Calendar.MONTH) == cal.get(Calendar.MONTH)
                    && startTime.get(Calendar.YEAR) == cal.get(Calendar.YEAR)) {
            	listBookingOfDay.add(getRoomBookings()[i]);}
        }
        
        for (int i = 0; i < listBookingOfDay.size(); i++) {
            Calendar startTime = listBookingOfDay.get(i).getTimeblock().getStartTime();
            for (int j = i + 1; j < listBookingOfDay.size() - 1; j++) {
                Calendar startTime1 = listBookingOfDay.get(j).getTimeblock().getStartTime();
                if (startTime1.get(Calendar.HOUR_OF_DAY) < startTime.get(Calendar.HOUR_OF_DAY)) {
                    RoomBooking room = listBookingOfDay.get(i);
                    listBookingOfDay.set(i, listBookingOfDay.get(j));
                    listBookingOfDay.set(j, room);
                }
            }
        }
        int time = 8;
        do {
            boolean check = false;
            for (int i = 0; i < listBookingOfDay.size(); i++) {
                Calendar startTime = listBookingOfDay.get(i).getTimeblock().getStartTime();
                Calendar endTime = listBookingOfDay.get(i).getTimeblock().getEndTime();
                if (startTime.get(Calendar.HOUR_OF_DAY) <= time
                        && endTime.get(Calendar.HOUR_OF_DAY) > time) {
                    System.out.print(listBookingOfDay.get(i).toString());
                    time += endTime.get(Calendar.HOUR_OF_DAY) - startTime.get(Calendar.HOUR_OF_DAY);
                    check = true;
                    break;
                }
            }
            if (check == false) {
                System.out.print("No booking scheduled between " + (time) + ":00 and " + (time + 1) + ":00 \n");
                time++;
            }

        } while (time < 23);
    }

    private String getResponseTo(String s) {
        System.out.print(s);
        return scan.nextLine();
    }

    private RoomBooking makeBookingFromUserInput() {
        scan.nextLine();
        String name, phoneNumber, Organization, category, description, date;
        name = getResponseTo("Enter Client Name (as FirstName LastName): ");
        phoneNumber = getResponseTo("Phone Number (e.g. 613-555-1212): ");
        Organization = getResponseTo("Organization (optional): ");
        category = getResponseTo("Enter event category: ");
        description = getResponseTo("Enter detailed description of event: ");
        
        Calendar startTime = null, endTime = null;
        startTime = makeCalendarFromUserInput(startTime, true);		//requestHour true to prompt endTime
        endTime = Calendar.getInstance();
        endTime.set(startTime.get(Calendar.YEAR), startTime.get(Calendar.MONTH), startTime.get(Calendar.DAY_OF_MONTH));		// set same day as startTime
        endTime = makeCalendarFromUserInput(endTime, true);
        
        Activity activity = new Activity(category, description);
        ContactInfo contactInfo = new ContactInfo(name, name, phoneNumber, Organization);
        TimeBlock timeBlock = new TimeBlock(startTime, endTime);
        RoomBooking roomBooking = new RoomBooking(timeBlock, contactInfo, activity);
        return roomBooking;
    }

    private Calendar makeCalendarFromUserInput(Calendar cal, boolean requestHour) {
        String time = "";
        if (cal == null) {
            String date = getResponseTo("Event Date (entered as DDMMYYYY): ");
            cal = Calendar.getInstance();		// Create a new Calendar object
            cal.set(Integer.parseInt(date.substring(4, 8)), Integer.parseInt(date.substring(2, 4)), Integer.parseInt(date.substring(0, 2)));
            if(requestHour==true)
            time = getResponseTo("Start Time: ");
        } else {
            if (requestHour) {
                time = getResponseTo("End Time: ");
                processTimeString(time);
            }
        }
        
        if (requestHour) {
            int hour = processTimeString(time);
            cal.set(Calendar.HOUR_OF_DAY, hour);
//             if (time.contains(":00")) {
//                cal.set(Calendar.HOUR_OF_DAY, hour);
//            } else {
//                if (time.contains("am") || time.contains("a.m.")) {
//                    cal.set(Calendar.HOUR_OF_DAY, hour);
//                    // cal.set(Calendar.AM, hour);
//                } else {
//                    cal.set(Calendar.HOUR_OF_DAY, hour + 12);
//                    //  cal.set(Calendar.PM, hour);
//                }
//            }
        }
        return cal;
    }

    private int processTimeString(String t) {
        if (t.contains(":00")) {			//24 hour format
            return (Integer.parseInt(t.substring(0, t.indexOf(":"))));
        } else {							// am pm format
            if(t.contains("am")||t.contains("a.m.")) {
            	return (Integer.parseInt(t.substring(0, t.indexOf(" "))));
        	} else {
        		return (Integer.parseInt(t.substring(0, t.indexOf(" "))) +12);
        	}
        }
    }

    private RoomBooking findBooking(Calendar cal) {
        TimeBlock timeblock = new TimeBlock(cal, null);
        for (int i = 0; i < getBookingIndex(); i++) {
            if (getRoomBookings()[i].getTimeblock().overlaps(timeblock)) {
                return getRoomBookings()[i];
            }
        }
        return null;
    }

    private RoomBooking[] getRoomBookings() {
        return roomBookings;
    }

    private void setBookingIndex(int bookingIndex) {
        this.lastBookingIndex = bookingIndex;
//        lastBookingIndex++;
    }

    private int getBookingIndex() {
        return lastBookingIndex;
    }
}