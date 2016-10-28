package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import entity.Table;
import entity.Reservation;
import entity.Customer;

public class ReservationController {
	private enum RestaurantSubmenu {
		ViewTableAvailability, ViewReservations, AddReservation, RemoveReservation
	}

	private static final int[] TABLE_SIZES = { 
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
			4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
			8, 8, 8, 8, 8, 10, 10, 10, 10, 10 };

	private static final int RESERVATION_DURATION = 2;

	private static final int LUNCH_OPENING_HOUR = 11;

	private static final int LUNCH_CLOSING_HOUR = 15;

	private static final int DINNER_OPENING_HOUR = 18;

	private static final int DINNER_CLOSING_HOUR = 22;

	private static List<Table> tables;

	private static List<Reservation> reservations;

	private static Scanner sc;

	private static SimpleDateFormat dateFormatter = null;

	private static ReservationController manager = null;

	private ReservationController() {
		sc = new Scanner(System.in);

		tables = new ArrayList<Table>();
		reservations = new ArrayList<Reservation>();

		dateFormatter = new SimpleDateFormat("E, dd/MM/yyyy, HH:mm");

		setUpTables();
	}

	public static ReservationController getManager() {
		if (manager == null) {
			manager = new ReservationController();
		}
		return manager;
	}

	public int getPaxFromReservation(int customerID) {
		if ( reservations.isEmpty())
			return 0;

		for (Reservation reservation :  reservations) {
			if (reservation.getCustomerID() == customerID) {
				return reservation.getNumOfPeople();
			}
		}

		return 0;
	}
	public Table getTableByNumber(int tableNumber)
	{
		for(Table table : tables)
		{
			if(table.getTableNumber() == tableNumber)
				return table;
		}
		
		return null;
	}
	private void viewReservations()
	{
		checkReservations();
		
		if(reservations.isEmpty())
		{
			System.out.print("\nWell, there are no reservations"
					+ " made at the moment!");
			System.out.println(" Try adding a new reservation? :-)");
			
			return;
		}
		
		System.out.print("\n" + new String(new char[43]).replace("\0", "*"));
		System.out.print(" Reservations ");
		System.out.println(new String(new char[43]).replace("\0", "*"));
		
		int currReservationNo = 1;
		
		for(Reservation reservation : reservations)
		{
			System.out.println(currReservationNo++);
			reservation.displayReservation();
		}
		
		System.out.print("\n" + new String(new char[43]).replace("\0", "*"));
		System.out.print(" Reservations ");
		System.out.println(new String(new char[43]).replace("\0", "*"));
	}
	
	private void displayRestaurantManagementOptions()
	{
		System.out.println("\n" + new String(new char[50]).replace("\0", "="));
		System.out.print("|" + new String(new char[10]).replace("\0", " "));
		System.out.print("Restaurant Management Submenu");
		System.out.println(new String(new char[9]).replace("\0", " ") + "|");
		System.out.println(new String(new char[50]).replace("\0", "="));
		
		System.out.println("0. Return to main menu");
		System.out.println("1. View table availability");
		System.out.println("2. View reservations");
		System.out.println("3. Add a new reservation");
		System.out.println("4. Remove a existing reservation");
	}
	
	private void setUpTables()
	{
		int tableNumber = 1;
		for(int tableSize : TABLE_SIZES)
		{		
			Table newTable;
			newTable = new Table(tableNumber++, tableSize, false,
					false, 0);
			
			tables.add(newTable);
		}
	}
	public int getRestaurantManagementChoice()
	{
		displayRestaurantManagementOptions();
		
		int maxRestaurantChoices = RestaurantSubmenu.values().length;
		int restaurantChoice = -1;
		do
		{
			try
			{
				System.out.printf("%nPlease enter your choice (0-%d): ",
						maxRestaurantChoices);
				restaurantChoice = sc.nextInt();
				sc.nextLine();
			}
			catch(InputMismatchException ex)
			{
				System.out.println("Invalid input! Please try again..");
				sc.nextLine(); // Clear the garbage input
				continue;
			}
			catch(Exception ex)
			{
				System.out.println("Invalid input! Please try again..");
				sc.nextLine(); // Clear the garbage input
				continue;
			}
			
			if(restaurantChoice < 0 || restaurantChoice > maxRestaurantChoices)
				System.out.println("Invalid choice! Please try again..");
			
		} while (restaurantChoice < 0 || restaurantChoice > maxRestaurantChoices);
		
		if(restaurantChoice == 0)
			return restaurantChoice; // Go back to main menu
		else
		{
			switch(RestaurantSubmenu.values()[restaurantChoice - 1])
			{
			case ViewTableAvailability:
				viewTableAvailability();
				break;
			
			case ViewReservations:
				viewReservations();
				break;
				
			case AddReservation:
				addReservation();
				break;
				
			case RemoveReservation:
				removeReservation();
				break;
			}
		}
		
		return restaurantChoice;
	}
	
	
	
	
	private void viewTableAvailability()
	{
		checkReservations();
		
		System.out.printf("%n%-20s", "Table Number");
		System.out.printf("%-20s", "Number of Seats");
		System.out.printf("%-20s", "Table Status");
		System.out.printf("%-20s%n", "Customer ID");
		
		for(Table table : tables)
		{
			table.displayTable();
		}
	}
	
	private void addReservation()
	{
		Customer customer = Customer.selectCustomer();
		checkReservations();
		
		System.out.println("\nRestaurant's opening hours: "
    					+ "Lunch Time: 1100 - 1500, "
				+ "Dinner Time: 1800 - 2200, Reservation Duration: 2 Hours");
		System.out.println("NOTE: Reservations will be cancelled 5 minutes"
				+ " after reservation date/time if you do not show up!");
		
        try
        {
        	SimpleDateFormat sdf = new SimpleDateFormat();
        	sdf.applyPattern("dd/MM/yyyy HH:mm");
        	sdf.setLenient(false);
        	
        	System.out.print("\nEnter reservation date (dd/mm/yyyy): ");
        	String reservationDateStr = sc.next();

        	System.out.print("Enter reservation time,"
        			+ " in 24-hour format (hh:mm): ");
        	String reservationTimeStr = sc.next();

        	Date reservationDateTime = sdf.parse(reservationDateStr 
        			+ " " + reservationTimeStr);
        	
        	Calendar startDateTime = GregorianCalendar.getInstance();
        	startDateTime.setTime(reservationDateTime);
        	
        	Calendar endDateTime = (Calendar) startDateTime.clone();
        	endDateTime.add(Calendar.HOUR_OF_DAY, RESERVATION_DURATION);
        	
        	Calendar restOpeningTimeL = (Calendar) startDateTime.clone();
        	restOpeningTimeL.set(Calendar.HOUR_OF_DAY, 
        			(LUNCH_OPENING_HOUR - 1) );
        	restOpeningTimeL.set(Calendar.MINUTE, 59);
        	
        	Calendar restClosingTimeL = (Calendar) restOpeningTimeL.clone();
        	restClosingTimeL.set(Calendar.HOUR_OF_DAY, LUNCH_CLOSING_HOUR);
        	restClosingTimeL.set(Calendar.MINUTE, 1);
        	
        	Calendar restOpeningTimeD = (Calendar) startDateTime.clone();
        	restOpeningTimeD.set(Calendar.HOUR_OF_DAY, 
        			(DINNER_OPENING_HOUR - 1) );
        	restOpeningTimeD.set(Calendar.MINUTE, 59);
        	
        	Calendar restClosingTimeD = (Calendar) restOpeningTimeD.clone();
        	restClosingTimeD.set(Calendar.HOUR_OF_DAY, DINNER_CLOSING_HOUR);
        	restClosingTimeD.set(Calendar.MINUTE, 1);
        	
        	
        	
        	
        	// Only allow reservations from 1100 - 1500
        	if( !(startDateTime.after(restOpeningTimeL)) ||
        		!(endDateTime.before(restClosingTimeL))||
        		!(endDateTime.after(restClosingTimeD))||
        		!(endDateTime.before(restClosingTimeD)))
        	{
        		System.out.print("\nInvalid reservation date/time! ");
    			System.out.println("Failed to add new reservation,"
    					+ " please try again..");
    			System.out.println("NOTE: The restaurant is only open"
    					+ " from 1100 - 1500 and 1800 - 2200!");
    			
    			return;
        	}
        	
        	Calendar currentInstant = GregorianCalendar.getInstance();
        	Date currentDateTime = currentInstant.getTime();
        	currentInstant.setTime(currentDateTime);
        	
        	if(startDateTime.before(currentInstant))
        	{
        		System.out.print("\nInvalid reservation date/time! ");
    			System.out.println("Failed to add new reservation,"
    					+ " please try again..");
    			System.out.println("NOTE: Reservation can only"
    					+ " be made in advance!");
    			
    			return;
        	}
                        	
        	System.out.print("Enter number of people (1-10): ");
        	int numOfPeople = sc.nextInt();
        	
        	if(numOfPeople < 1 || numOfPeople > 10)
            {
            	System.out.println("Invalid number of people, failed to"
            			+ " add new reservation!");
            	return;
            }
        	
        	// Attempt to allocate a available table
        	int availableTableNumber = 0;
        	int tableNumber = 1;
        	
    		for(int tableSize : TABLE_SIZES)
    		{
    			if(tableSize >= numOfPeople)
    			{
    				boolean isReserved = false;
    				
    				for(Reservation reservation : reservations)
    	    		{
    					Calendar resStartDateTime =
    							reservation.getStartDateTime();
    					
    					Calendar resEndDateTime = 
    							(Calendar) resStartDateTime.clone();
    					resEndDateTime.add(Calendar.HOUR_OF_DAY, 
    							reservation.getDuration());
    					
    					// Check for existing reservation for this table number
    	    			if(reservation.getReservedTableNo() == tableNumber)
    	    			{
    	    				/* Assumption: Edges overlapping is not allowed
        					 * 
        					 * Therefore, overlapping occurs when
        					 * (StartA <= EndB) and (EndA >= StartB)
        					 * 
        					 * Let StartA be startDateTime, EndA be endDateTime
        					 * Let StartB be resStartDateTime,
        					 * 	   EndB   be resEndDateTime
        					 */
    	    				
    	    				if(startDateTime.before(resEndDateTime) &&
    	    				 endDateTime.after(resStartDateTime))
	    	    			{
	    	    				isReserved = true;
	    	    				break;
	    	    			}
    	    			}
    	    		} // End reservations for loop
    				
    				if(!isReserved)
    				{
    					availableTableNumber = tableNumber;
    					break;
    				}
    			}
    			
    			tableNumber++;
    		} // End tables for loop
    		
    		if(availableTableNumber == 0)
    		{
    			System.out.printf("\nSorry, there are no tables available"
    					+ " at the selected date/time that can accommodate"
    					+ " %d people!%n", numOfPeople);
    		}
    		else
    		{	
    			Reservation newReservation;
    			newReservation = new Reservation(availableTableNumber,
    					customer.getCustomerID(), customer.getName(),
    					customer.getContactNumber(), numOfPeople,
    					startDateTime, RESERVATION_DURATION);
    			
    			reservations.add(newReservation);
    			
    			Collections.sort(reservations);
    			
    			System.out.printf("\nSuccessfully allocated Table"
    					+ " '%d' to '%s'!%n", availableTableNumber, 
    					customer.getName());
    			System.out.printf("Reservation Date/Time: %s,"
    					+ " Reservation Duration: %d Hours%n",
    					dateFormatter.format(startDateTime.getTime()),
    					RESERVATION_DURATION);
    		}
        }
        catch(ParseException ex)
        {
        	System.out.print("\nInvalid reservation date/time! ");
			System.out.println("Failed to add new reservation,"
					+ " please try again..");
			System.out.println("NOTE: Reservation date should"
        			+ " be in dd/mm/yyyy, e.g. 25/12/2014 and"
        			+ "\n      reservation time should be in"
        			+ " hh:mm (24-hour format), e.g. 19:30!");
			
			return;
        }
        catch(InputMismatchException ex)
        {
        	System.out.print("\nInvalid input! ");
			System.out.println("Failed to add new reservation,"
					+ " please try again..");
			return;
        }
        catch(Exception ex)
        {
        	System.out.print("\nInvalid input! ");
			System.out.println("Failed to add new reservation,"
					+ " please try again..");
			return;
        }
	}
	
	
	private void removeReservation()
	{
		checkReservations();
		
		if(reservations.isEmpty())
		{
			System.out.print("\nWell, there are no reservations"
					+ " made at the moment!");
			System.out.println(" What is there to remove? :-)");
			
			return;
		}

		try
		{
			int numOfReservations = 0;

			System.out.println();
			System.out.printf("Table Number");
			System.out.printf("Customer Name");
			System.out.printf("Reservation Date/Time");
			System.out.printf("Reservation Duration");

			// Display reservations
			for(Reservation reservation : reservations)
			{
				System.out.printf("%-5s", "(" + (++numOfReservations) + ")");
				reservation.displayReservationSummary();
			}

			System.out.printf("%nPlease select a reservation to remove "
					+ "(0 to cancel): ");

			int reservationIndex = sc.nextInt();
			sc.nextLine();

			// User chooses not to remove any reservation
			if(reservationIndex == 0)
			{
				System.out.println("\nNo reservation removed! :-)");
				return;
			}

			// Valid reservationIndex from 1 to numOfReservations
			if (reservationIndex < 1 || reservationIndex > numOfReservations)
			{
				System.out.print("\nInvalid input! ");
				System.out.println("Failed to remove reservation,"
						+ " please try again..");
				return;
			}

			Reservation removedReservation = 
					reservations.get(reservationIndex - 1);

			if(removedReservation != null)
			{
				Table table = getTableByNumber(
						removedReservation.getReservedTableNo());
				
				if(table.isReserved() && (table.getCustomerID() == 
						removedReservation.getCustomerID()) )
				{
					table.flushTable();
				}
				
				System.out.printf("%nSuccessfully removed reservation made by"
						+ " \"%s\"!%n", removedReservation.getCustomerName());
			}
			
			reservations.remove(removedReservation);
		}
		catch(InputMismatchException ex)
		{
			System.out.print("\nInvalid input! ");
			System.out.println("Failed to remove reservation,"
					+ " please try again..");

			sc.nextLine(); // Clear the garbage input
			return;
		}
		catch(Exception ex)
		{
			System.out.println("Failed to remove reservation,"
					+ " please try again..");

			sc.nextLine(); // Clear the garbage input
			return;
		}
	}
	
	
	private void checkReservations()
	{
		if(reservations.isEmpty())
			return;
		
		Calendar currentInstant = GregorianCalendar.getInstance();
		Date currentDateTime = currentInstant.getTime();
		currentInstant.setTime(currentDateTime);

		Iterator<Reservation> resIter = reservations.iterator();
		Reservation reservation = null;
		
		while(resIter.hasNext()) {
			
			reservation = resIter.next();
			
			Calendar restStartDateTime = reservation.getStartDateTime();
			Calendar restClone = (Calendar) restStartDateTime.clone();
			restClone.add(Calendar.MINUTE, 5);

			// If currentInstant is after restStartDateTime + 5 mins
			if(restClone.before(currentInstant))
			{
				Table table = 
						getTableByNumber(reservation.getReservedTableNo());
				
				if( (!table.isOccupied()) && table.isReserved()
						&& (table.getCustomerID() == 
						reservation.getCustomerID()) )
				{
					table.flushTable();
				}
				
				// Remove expired reservation
				resIter.remove();
			}
			else
			{
				// If reservationTime <= currentInstant & not expired
				// Set table as reserved
				restStartDateTime = reservation.getStartDateTime();
				if(restStartDateTime.before(currentInstant))
				{
					Table table = 
							getTableByNumber(reservation.getReservedTableNo());
					
					if(!table.isReserved())
						table.reserveTable(reservation.getCustomerID());
					
					if(table.isOccupied()) {
						// Remove expired reservation
						resIter.remove();
					}
				}
			}
		}
	}
	
	
	public Table getReservedTable(int customerID)
	{
		checkReservations();
		
		for(Table table : tables)
		{
			if(table.isReserved())
			{
				if(table.getCustomerID() == customerID)
				{
					table.reserveTable(customerID);
					return table;
				}
			}
		}
		
		return null;
	}
	
	public Table getAvailableTable(int customerID, int numOfPeople) {
		checkReservations();

		for (Table table :  tables) {
			if (!table.isOccupied()) {
				if (!table.isReserved()) {
					if (table.getSeats() >= numOfPeople) {
						table.reserveTable(customerID);
						return table;
					}
				}
			}
		}

		System.out.println("\nNo available tables!");
		return null;
	}
}
