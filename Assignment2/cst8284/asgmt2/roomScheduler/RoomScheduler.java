package cst8284.asgmt2.roomScheduler;
/*  Course Name: CST8284
	Author: Pham Thanh Hue
	Class name: RoomScheduler.java
	Date: March 02, 2020
*/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import cst8284.asgmt2.room.ComputerLab;
import cst8284.asgmt2.room.Room;


public class RoomScheduler {
	private static Scanner scan = new Scanner (System.in);
	private ArrayList<RoomBooking> roomBookings;
	private Room room;
	
	private static final int	DISPLAY_ROOM_INFORMATION = 1,
    							ENTER_ROOM_BOOKING = 2,
    							DELETE_BOOKING = 3,
    							CHANGE_BOOKING = 4,
    							DISPLAY_BOOKING = 5,
    							DISPLAY_DAY_BOOKINGS = 6,
    							SAVE_BOOKINGS_TO_FILE = 7,
    							LOAD_BOOKINGS_FROM_FILE = 8,
    							EXIT = 0;
	
	public RoomScheduler() {
        this.roomBookings = new ArrayList<RoomBooking>();
        ComputerLab computerLab = new ComputerLab();
        computerLab.setRoomNumber("B119");
        setRoom(computerLab);
	}
	
	public void launch() {
		int choice = 0;
		do {
			choice=displayMenu();
			executeMenuItem(choice);
		} while (choice!=EXIT);
	}
	
	private int displayMenu() {
		System.out.println("Enter a selection from the following menu:");
		System.out.println(
						DISPLAY_ROOM_INFORMATION + ". Display room information\n" 
						+ ENTER_ROOM_BOOKING + ". Enter a room booking  \n"
		                + DELETE_BOOKING + ". Remove a room booking  \n"
		                + CHANGE_BOOKING + ". Change a room booking\n"
		                + DISPLAY_BOOKING + ". Display a booking \n"
		                + DISPLAY_DAY_BOOKINGS + ". Display room bookings for the whole day \n"
		                + SAVE_BOOKINGS_TO_FILE + ". Backup current bookings to file \n"
		                + LOAD_BOOKINGS_FROM_FILE + ". Load current bookings from file \n"
		                + EXIT + ". Exit program\n");
		int choice = scan.nextInt();
		scan.nextLine();  
		System.out.println(); 
		return choice;
	}
	
	private void executeMenuItem(int choice) {
		switch (choice) {
			case DISPLAY_ROOM_INFORMATION:
				displayRoomInfo();
				break;
			case ENTER_ROOM_BOOKING:
                saveRoomBooking(makeBookingFromUserInput());
                break;
			case DISPLAY_BOOKING:
				displayBooking(makeCalendarFromUserInput(null, true));
				break;
			case SAVE_BOOKINGS_TO_FILE:
                saveBookingsToFile();
                break;
			case LOAD_BOOKINGS_FROM_FILE:
                roomBookings = loadBookingsFromFile();
                break;
            case CHANGE_BOOKING:
//                scan.nextLine();
                Calendar calChange = makeCalendarFromUserInput(null, true);
                changeBooking(calChange);
                break;
            case DELETE_BOOKING:
//                scan.nextLine();
                Calendar calDel = makeCalendarFromUserInput(null, true);
                deleteBooking(calDel);
                break;
            case DISPLAY_DAY_BOOKINGS:
//                scan.nextLine();
                Calendar cal1 = makeCalendarFromUserInput(null, false);
                displayDayBookings(cal1);
                break;
			case EXIT:
				System.out.println("Exiting Room Booking Application");
				break;
			default: System.out.println("Invalid choice: try again. (Select " + EXIT + " to exit.)\n");
		}
		System.out.println();  // add blank line after each output
	}

    private void displayRoomInfo() {
        System.out.println(getRoom().toString());
    }
    
    private boolean changeBooking(Calendar cal) {
        RoomBooking roomBooking = findBooking(cal);
        
        if (roomBooking != null) {
        	System.out.println(roomBooking.toString());
            Calendar startTime = roomBooking.getTimeBlock().getStartTime(), endTime = roomBooking.getTimeBlock().getEndTime();
            String time = getResponseTo("Enter New Start Time: ");
            int hour = processTimeString(time);
            startTime.set(Calendar.HOUR_OF_DAY, hour);
            time = getResponseTo("Enter New End Time: ");
            hour = processTimeString(time);
            endTime.set(Calendar.HOUR_OF_DAY, hour);
            TimeBlock timeBlock = new TimeBlock(startTime, endTime);
            roomBooking.setTimeBlock(timeBlock);
            System.out.println("Booking has been changed to: \n");
            System.out.print(roomBooking.toString());
        }
        return false;
    }
    
    private boolean saveBookingsToFile() {
        try {

            FileOutputStream f = new FileOutputStream(new File("CurrentRoomBookings.book.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            for (int i = 0; i < getRoomBookings().size(); i++) {
                o.writeObject(getRoomBookings().get(i).getActivity());
                o.writeObject(getRoomBookings().get(i).getTimeBlock());
                o.writeObject(getRoomBookings().get(i).getContactInfo());
            }
            System.out.println("Current room bookings backed up to file");
            o.close();
            f.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(RoomScheduler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    private ArrayList<RoomBooking> loadBookingsFromFile() {
        FileInputStream fi;
        ArrayList<RoomBooking> array = new ArrayList<>();
        try {
            fi = new FileInputStream(new File("CurrentRoomBookings.book.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            int index = 0;
            TimeBlock time = null;
            Activity act = null;
            ContactInfo contact = null;
            while (fi.available() > 0) {
                if (index == 0) {
                    act = (Activity) oi.readObject();
                    index++;
                } else if (index == 1) {
                    time = (TimeBlock) oi.readObject();
                    index++;
                } else if (index == 2) {
                    index = 0;
                    contact = (ContactInfo) oi.readObject();
                    array.add(new RoomBooking(time, contact, act));
                }
            }
             System.out.println("Current room bookings loaded from file");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RoomScheduler.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(RoomScheduler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RoomScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    
	/****************************************************************************
	* Title: CST8284 Assignment1 code
	* Author: Dave Houtman
	* Date: Feb 2020
	* Code version: 1.0
	* Availability: Algonquin 20W_CST8284_310 Object Oriented Programming (Java)
	*****************************************************************************/
	private boolean saveRoomBooking(RoomBooking roomBooking) {
		TimeBlock tb = roomBooking.getTimeBlock();  // Check this TimeBlock to see if already booked
		Calendar cal = (Calendar)tb.getStartTime().clone(); // use its Calendar
		int hour = cal.get(Calendar.HOUR_OF_DAY);	//Get first hour of block
		
		for (; hour < tb.getEndTime().get(Calendar.HOUR_OF_DAY); hour++){ //Loop through each hour in TimeBlock
			cal.set(Calendar.HOUR_OF_DAY, hour); // set next hour
		    if (findBooking(cal)!=null) {  	// TimeBlock already booked at that hour, can't add appointment
		    	System.out.println("Cannot save booking; that time is already booked");
				return false;
		    }	
		}  					// else time slot still available; continue loop to next hour
//		getRoomBookings()[getBookingIndex()] = roomBooking;  
//	    setBookingIndex(getBookingIndex()+1);
		roomBookings.add(roomBooking);
		System.out.println("Booking time and date saved.");  
		return true;
	}
	
    private boolean deleteBooking(Calendar cal) {
        // RoomBooking room = null; 
        RoomBooking room = findBooking(cal);
        if (room != null) {
            System.out.println(room.toString());
            System.out.println("Press 'Y' to confirm deletion, any other key to abort: ");
            String userInput = scan.nextLine();
            if (userInput.equals("Y") || userInput.equals("y")) {
                roomBookings.remove(room);
                System.out.println("Booking deleted");
                return true;
            }
        }
        return false;
    }
	
	/****************************************************************************
	* Title: CST8284 Assignment1 code
	* Author: Dave Houtman
	* Date: Feb 2020
	* Code version: 1.0
	* Availability: Algonquin 20W_CST8284_310 Object Oriented Programming (Java)
	*****************************************************************************/
	private RoomBooking displayBooking (Calendar cal) {
		RoomBooking booking = findBooking(cal);
		int hr = cal.get(Calendar.HOUR_OF_DAY);
		System.out.print((booking!=null) ?
		   booking.toString(): 
  	       "No booking scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n"
		);
		return booking;
	}
	

	/****************************************************************************
	* Title: CST8284 Assignment1 code
	* Author: Dave Houtman
	* Date: Feb 2020
	* Code version: 1.0
	* Availability: Algonquin 20W_CST8284_310 Object Oriented Programming (Java)
	*****************************************************************************/
	private void displayDayBookings (Calendar cal) {
		for (int hrCtr = 8; hrCtr < 24; hrCtr++) {
			cal.set(Calendar.HOUR_OF_DAY, hrCtr);
			RoomBooking rb = displayBooking(cal);	
			if (rb !=null) hrCtr = rb.getTimeBlock().getEndTime().get(Calendar.HOUR_OF_DAY) - 1;
		}
	}
	
	private static String getResponseTo (String s) {
		System.out.print(s);
		return (scan.nextLine());
	}
	
	
	/****************************************************************************
	* Title: CST8284 Assignment1 code
	* Author: Dave Houtman
	* Date: Feb 2020
	* Code version: 1.0
	* Availability: Algonquin 20W_CST8284_310 Object Oriented Programming (Java)
	*****************************************************************************/
	private RoomBooking makeBookingFromUserInput() {
		
		String[] fullName = getResponseTo("Enter Client Name (as FirstName LastName): ").split(" ");
		String phoneNumber = getResponseTo("Phone Number (e.g. 613-555-1212): ");
		String organization = getResponseTo("Organization (optional): ");
		String category = getResponseTo("Enter event category: ");
		String description = getResponseTo("Enter detailed description of event: ");
		
		Calendar startCal = makeCalendarFromUserInput(null, true);
		Calendar endCal = makeCalendarFromUserInput(startCal, true);
		
//        Calendar startTime = null, endTime = null;
//        startTime = makeCalendarFromUserInput(startTime, true);		//requestHour true to prompt endTime
//        endTime = Calendar.getInstance();
//        endTime.set(startTime.get(Calendar.YEAR), startTime.get(Calendar.MONTH), startTime.get(Calendar.DAY_OF_MONTH));		// set same day as startTime
//        endTime = makeCalendarFromUserInput(endTime, true);
//	
		ContactInfo contactInfo = new ContactInfo(fullName[0], fullName[1], phoneNumber, organization);
		Activity activity = new Activity(category, description);
		TimeBlock timeBlock = new TimeBlock(startCal, endCal);
		RoomBooking roomBooking = new RoomBooking(timeBlock, contactInfo, activity);
		return roomBooking;
		//return (new RoomBooking(contactInfo, activity, timeBlock));   //shortcut
	}
	
	
	/****************************************************************************
	* Title: CST8284 Assignment1 code
	* Author: Dave Houtman
	* Date: Feb 2020
	* Code version: 1.0
	* Availability: Algonquin 20W_CST8284_310 Object Oriented Programming (Java)
	*****************************************************************************/
	private Calendar makeCalendarFromUserInput(Calendar initCal, boolean requestHour) {
    	Calendar cal = Calendar.getInstance(); cal.clear();
    	String date = "";
    	int hour = 0;	
    	boolean needCal = (initCal==null);
    	
   		if (needCal) date = getResponseTo("Event Date (entered as DDMMYYYY): ");
   		int day = needCal ? Integer.parseInt(date.substring(0,2)) : initCal.get(Calendar.DAY_OF_MONTH);
   		int month = needCal ? Integer.parseInt(date.substring(2,4))-1 : initCal.get(Calendar.MONTH);
   		int year = needCal ? Integer.parseInt(date.substring(4,8)) : initCal.get(Calendar.YEAR);
     		
		if (requestHour) {				
		   String time = getResponseTo((needCal?"Start":"End") +" Time: ");
		   hour = processTimeString(time);
		}
		
		cal.set(year, month, day, hour, 0);
		return (cal);
	}
	
	
	/****************************************************************************
	* Title: CST8284 Assignment1 code
	* Author: Dave Houtman
	* Date: Feb 2020
	* Code version: 1.0
	* Availability: Algonquin 20W_CST8284_310 Object Oriented Programming (Java)
	*****************************************************************************/
	private static int processTimeString (String t) {
		int hour = 0;
		t = t.trim();
		
		if (t.contains ("pm") || (t.contains("p.m."))) hour = Integer.parseInt(t.split(" ")[0]) + 12;
		if (t.contains("am") || t.contains("a.m.")) hour = Integer.parseInt(t.split(" ")[0]);
		if (t.contains(":")) hour = Integer.parseInt(t.split(":")[0]);
		
		return hour;
	}
	
	
	/****************************************************************************
	* Title: CST8284 Assignment1 code
	* Author: Dave Houtman
	* Date: Feb 2020
	* Code version: 1.0
	* Availability: Algonquin 20W_CST8284_310 Object Oriented Programming (Java)
	*****************************************************************************/
	private RoomBooking findBooking(Calendar cal) {
		Calendar oneHourLater = Calendar.getInstance();
    	oneHourLater.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)+1);
    	TimeBlock findTB = new TimeBlock(cal, oneHourLater);
    	for (int i = 0; i < getRoomBookings().size(); i++) 
    		if (getRoomBookings().get(i).getTimeBlock().overlaps(findTB)) 
    			return getRoomBookings().get(i);		
    	return null;
	}
	
	
	private ArrayList<RoomBooking> getRoomBookings() {
		return roomBookings;
	}
	
    private void setRoom(Room room) {
        this.room = room;
    }

    private Room getRoom() {
        return room;
    }
	
//	private int getBookingIndex() {
//		return lastBookingIndex;
//	}
//	
//	private void setBookingIndex(int bookingIndex) {
//		this.lastBookingIndex = bookingIndex;
//	}

}
