package entity;

public class Table {
	private int tableNumber;
	private int tableSeats;
	private boolean isReservedAM;
	private boolean isReservedPM;
	private boolean isOccupied;
	
/**
 * Constructor for Table
 * @param tableNumber The number assigned to the table
 * @param seats The number of seats on this table
 * @param isReserved Whether the table has been reserved
 * @param isOccupied Whether the table is currently being occupied
 * @param customerID The customer assigned to this table as it is being occupied
 */
public Table (int tableNumber, int tableSeats, boolean isReservedAM, boolean isReservedPM, boolean isOccupied){
	this.tableNumber = tableNumber;
	this.tableSeats = tableSeats;
	this.isReservedAM = isReservedAM;
	this.isReservedPM = isReservedPM;
	this.isOccupied = isOccupied;
	
}
	
/**
 * Getter and Setter Methods	
 */

public int getTableNumber(){
	return tableNumber;
}

public void setTableNumber(int number){
	this.tableNumber = number;
}

public int getSeats(){
	return tableSeats;
}

public void setSeats (int number){
	this.tableSeats = number;
}

public boolean isReservedAM(){
	return isReservedAM;
}


public boolean isReservedPM(){
	return isReservedPM;
}


public boolean isOccupied(){
	return isOccupied;
}


public void displayTable(){
	System.out.println("Table Number: "+getTableNumber());
	System.out.println("Number of Seats: "+getSeats());
	System.out.println(isOccupied() ? "Occupied" : 
		(isReservedAM() ? "Reserved" : "Available"));
}


	
}
