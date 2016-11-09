package controllers;

import java.util.Date;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import entity.Reservation;
import entity.ReservationAll;
import entity.Table;
import entity.TableAll;

/**
 * TableController Class controls the tables  in the restaurant
 * 
 * 9/11/2016
 *
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */
public class TableController {	//This class is just to settle which tables are being reserved, and which to be unreserved.
/**
 * TableRecords public attribute contain the table records in table all
 */	
    public static TableAll TableRecords;
	private ReservationAll rL;
	
        /**
         * TableController Constructor defines the number of tables in the 
         * restaurant
         */
	public TableController(){
		TableRecords = new TableAll(5,5,10,10);
		rL = ReservationController.allTheReservations;
	}
	

	/**
         * getReservationAll method retrieves all the reservations
         * @return the reservation list
         */
	public ReservationAll getReservationAll(){
		return rL;
	}
	
        /**
         * setR1 method assigns the reservation all to reservation list
         * @param rA the reservation all list
         */
	public void setRl(ReservationAll rA){
		rL = rA;
	}
	
        
        /**
         * run Method for the Table Controller, syncs tables with reservation
         */
	public void run(){
		Date today = new GregorianCalendar().getTime();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String todayString = df.format(today);
		
		
		System.out.println("Syncing tables with reservations list");
		TableRecords.syncTables(todayString, this.rL);
		try{
			//System.out.println("Testing removal of expired reservations");
			TableRecords.expireReservations(rL, todayString, 30);
			try {
				ReservationController.saveReservations();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				
			}
		}catch(ParseException e){
			System.out.println("OH SHIT.");
		}
		//TableRecords.showTableStatuses();
			//System.out.println("Please Select option: (Enter -1 to go back)");
	}
	
        /**
         * getAvailableTables retrieves all the tables that are available at a 
         * given moment
         */
	public void getAvailableTables(){
		System.out.println("These are the tables available at the moment:");
		ArrayList<Table> tempTableList = TableRecords.getAvailableTables();
		if(tempTableList.isEmpty()){
			System.out.println("All the tables are currently occupied or reserved during this timeslot. Please wait");
		}else{
			for(int i=0;i<tempTableList.size();i++){
				tempTableList.get(i).displayTable();
			}
		}
	}
	
        /**
         * showAllTableStatuses will display all the tables and their 
         * respective statuses
         */
	public void showAllTableStatuses(){
		System.out.println("Curent status of all tables: ");
		TableRecords.showTableStatuses();
	}
}
