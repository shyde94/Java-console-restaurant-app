package entity;

public class Table {
	private int tableNumber;
	private int seats;
	private boolean isReserved;
	private boolean isOccupied;
	private int customerID;
	
/**
 * Constructor for Table
 * @param tableNumber The number assigned to the table
 * @param seats The number of seats on this table
 * @param isReserved Whether the table has been reserved
 * @param isOccupied Whether the table is currently being occupied
 * @param customerID The customer assigned to this table as it is being occupied
 */
public Table (int tableNumber, int seats, boolean isReserved, boolean isOccupied, int customerID){
	this.tableNumber = tableNumber;
	this.seats = seats;
	this.isReserved = isReserved;
	this.isOccupied = isOccupied;
	this.customerID = customerID;
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
	return seats;
}

public void setSeats (int number){
	this.seats = number;
}

public boolean isReserved(){
	return isReserved;
}

public boolean isOccupied(){
	return isOccupied;
}

public int getCustomerID(){
	return customerID;
}

public void reserveTable(int customerID){
	//Check if reservation is empty at a particular time
	isReserved = true;
	this.customerID = customerID;
}

public void cancelTable(){
	customerID = 0;
	isReserved = false;
}

public void occupyTable(int customerID){
	isOccupied = true;
	isReserved = false;
	this.customerID = customerID;
}

public void flushTable(){
	isOccupied = false;
	isReserved = false;
	customerID = 0;
}

public void displayTable(){
	System.out.println("Table Number: "+getTableNumber());
	System.out.println("Number of Seats: "+getSeats());
	System.out.println(isOccupied() ? "Occupied" : 
		(isReserved() ? "Reserved" : "Available"));
}


	
}
