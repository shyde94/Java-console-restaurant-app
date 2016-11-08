package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Reservation Class contains all the information related to a reservation
 * 9/11/2016
 * 
 * @author Shide 
 * @author Eeyern 
 * @author Grace 
 * @author Xi Tong 
 */
public class Reservation  implements Serializable{
	private int tableNumber;
	private int numberOfPeople;
	private String hpNumber;
	private String customerName;
	//private Date start;
	private String dateReserved;
	private String timeOfArrival;
	private String slot;
	
	
	//Constructors 
/**
 * Reservation Constructor 
 * 
 * @param tableNumber
 * @param numberOfPeople
 * @param hpNumber
 * @param customerName
 * @param dateReserved
 * @param timeOfArrival
 * @param slot 
 */
	public Reservation(int tableNumber, int numberOfPeople, String hpNumber, String customerName, String dateReserved, String timeOfArrival, String slot){
		this.setTableNumber(tableNumber);
		this.setNumberOfPeople(numberOfPeople);
		this.setHpNumber(hpNumber);
		this.setCustomerName(customerName);
		this.setDateReserved(dateReserved);
		this.setTimeOfArrival(timeOfArrival);
		this.setSlot(slot);
	}


	public String getSlot() {
		return slot;
	}





	public void setSlot(String slot) {
		this.slot = slot;
	}



	public int getTableNumber() {
		return tableNumber;
	}


	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}


	public int getNumberOfPeople() {
		return numberOfPeople;
	}


	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}


	public String getHpNumber() {
		return hpNumber;
	}


	public void setHpNumber(String hpNumber) {
		this.hpNumber = hpNumber;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setDateReserved(String dateReserved) {
		this.dateReserved = dateReserved;
		
	}
	
	public String getDateReserved() {
		return dateReserved;
	}
	
	public String getTimeOfArrival(){
		return this.timeOfArrival;
	}

	
	public void setTimeOfArrival(String timeOfArrival){
		this.timeOfArrival = timeOfArrival;
	}
	
	public void printReservation(){
		System.out.println( "date reserved: " + dateReserved + ", time slot: "+ slot +", Time of arrival: " + timeOfArrival+", Table: " + tableNumber + ", Number of people: " + numberOfPeople + ", Customer name: " + customerName );
	}
	
	
	
	
	
	
}
