package cst8284.asgmt2.room;
/*  Course Name: CST8284
	Author: Pham Thanh Hue
	Class name: Boardroom.java
	Date: March 02, 2020
*/

public final class Boardroom extends Room {
    private int seats;
    
    public Boardroom(){
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
    
    protected int getSeats(){
        return 16;
    }
    
    protected String getRoomType(){
        return "board room";
    }
    
    protected String getDetails(){
        return "conference call enabled";
    }
}
