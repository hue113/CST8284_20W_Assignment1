package cst8284.asgmt2.room;
/*  Course Name: CST8284
	Author: Pham Thanh Hue
	Class name: ComputerLab.java
	Date: March 02, 2020
*/

public final class ComputerLab extends Room{
    private static final int DEFAULT_SEATS = 30;
    private int seats;

    public ComputerLab() {  
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
    
	@Override
	protected String getRoomType() {
		return "computer lab";
	}

	@Override
	protected int getSeats() {
		return DEFAULT_SEATS;
	}
	
	@Override
	protected String getDetails() {
		return "contains outlets for 30 laptops";
	}

}
