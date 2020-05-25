package cst8284.asgmt2.room;
/*  Course Name: CST8284
	Author: Pham Thanh Hue
	Class name: Classroom.java
	Date: March 02, 2020
*/

public final class Classroom extends Room {
    private static final int DEFAULT_SEATS = 120;
    private int seats;
    
    public Classroom(){ 
    }
    
    public void setSeats(int seats) {
        this.seats = seats;
    }
    
	@Override
	protected int getSeats() {
		return DEFAULT_SEATS;
	}
	
	@Override
	protected String getRoomType() {
		return "class room";
	}

	@Override
	protected String getDetails() {
		return "contains overhead projector";
	}

}
