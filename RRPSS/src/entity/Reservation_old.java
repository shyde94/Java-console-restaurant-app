package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reservation_old implements Serializable, Comparable<Reservation_old>
{
	/**
	 * Generated serial version ID for serializable classes
	 */
	private static final long serialVersionUID = -1362303644760942385L;

	private int reservedTableNo;
	
	private int customerID;
	
	private String customerName;
	
	private int customerPhone;

	private int numOfPeople;

	private Calendar startDateTime;

	private int duration;

	private static final SimpleDateFormat dateFormatter = 
			new SimpleDateFormat("E, dd/MM/yyyy, HH:mm");
	

	public Reservation_old(int reservedTableNo, int customerID,
			String customerName, int customerPhone, int numOfPeople,
			Calendar startDateTime, int duration) {
		
		this.reservedTableNo = reservedTableNo;
		
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.numOfPeople = numOfPeople;
		this.startDateTime = startDateTime;
		this.duration = duration;
	}
	
	public int getReservedTableNo() {
		return reservedTableNo;
	}
	
	public void setReservedTableNo(int newReservedTableNo) {
		this.reservedTableNo = newReservedTableNo;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public int getCustomerPhone() {
		return customerPhone;
	}
	
	public int getNumOfPeople() {
		return numOfPeople;
	}
	
	public void setNumOfPeople(int number) {
		numOfPeople = number;
	}
	
	public Calendar getStartDateTime() {
		return startDateTime;
	}
	
	public void setStartDateTime(Calendar dateTime) {
		startDateTime = dateTime;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int time) {
		duration = time;
	}
	
	public void displayReservation()
	{
		System.out.println("Table Number: " + getReservedTableNo());
		
		System.out.println("Customer ID: " + getCustomerID());
		System.out.println("Name: " + getCustomerName());
		System.out.println("Contact: " + getCustomerPhone());	
		System.out.println("Number of People: " + getNumOfPeople());
		System.out.println("Reservation Date/Time: " + 
				dateFormatter.format(startDateTime.getTime()));
		System.out.println("Duration: " + getDuration() + 
				(getDuration() > 1 ? " Hours" : " Hour"));
	}

	public void displayReservationSummary()
	{
		// "Table Number"
		System.out.println(getReservedTableNo());
		
		// "Customer Name"
		System.out.println(getCustomerName());
		
		// "Reservation Date/Time"
		System.out.println(dateFormatter.format(startDateTime.getTime()));
		
		// "Reservation Duration"
		System.out.println(getDuration() + 
				(getDuration() > 1 ? " Hours" : " Hour"));
	}

	@Override
	public int compareTo(Reservation_old res)
	{
		return this.getStartDateTime().compareTo(res.getStartDateTime());
	}
}