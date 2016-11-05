package controllers;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


import entity.ReservationAll;
import entity.Table;
import entity.TableAll;

public class TableController {
	public static TableAll TableRecords;
	public static ArrayList<Table> reservedToday;
	public TableController(){
		TableRecords = new TableAll(5,5,10,10);
		reservedToday = new ArrayList<Table>();
	}
	
	public void run(){
		ReservationAll rL = ReservationController.allTheReservations;
		Date today = new GregorianCalendar().getTime();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String todayString = df.format(today);
		System.out.println("Syncing tables with reservations list");
		reservedToday = checkAndSetReservedToday(todayString, rL);
		for(int i=0;i<TableRecords.getTotalNumOfTables();i++){
			TableRecords.getAllTheTables().get(i).displayTable();
		}
		
	}
	
	/*public void syncTablesWithReservations(){
		Date today = new GregorianCalendar().getTime();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String todayString = df.format(today);
		System.out.println("Date today:" + todayString);
		ReservationAll rL = ReservationController.allTheReservations;
		ArrayList<Integer> reservedTablesAM = rL.reservedTablesOnDate(todayString, "AM");
		ArrayList<Integer> reservedTablesPM = rL.reservedTablesOnDate(todayString, "PM");
		for(int i=0;i<TableRecords.getTotalNumOfTables();i++){
			if(reservedTablesAM.contains(TableRecords.getAllTheTables().get(i).getTableNumber())){
				Table temp = TableRecords.getAllTheTables().get(i);
				temp.setIsReservedAM(true);
			}
			if(reservedTablesPM.contains(TableRecords.getAllTheTables().get(i).getTableNumber())){
				Table temp = TableRecords.getAllTheTables().get(i);
				temp.setIsReservedPM(true);
			}
		}
	}*/
	public void removeExpiredRes(ReservationAll rL){		//To remove reservations that have expired! eg 30min still not here
		Date today = new GregorianCalendar().getTime();
		ArrayList<String> reservedTime = new ArrayList<String>();
		for(int i=0;i<reservedToday.size();i++){
			 
		}
		
	}
	
	public ArrayList<Table> checkAndSetReservedToday(String todayString,ReservationAll rL ){
		ArrayList<Table> temp = new ArrayList<Table>();
		
		ArrayList<Integer> reservedTablesAM = rL.reservedTablesOnDate(todayString, "AM");
		ArrayList<Integer> reservedTablesPM = rL.reservedTablesOnDate(todayString, "PM");
		for(int i=0;i<TableRecords.getTotalNumOfTables();i++){
			if(reservedTablesAM.contains(TableRecords.getAllTheTables().get(i).getTableNumber())){
				Table temp1 = TableRecords.getAllTheTables().get(i);
				temp1.setIsReservedAM(true);
				temp.add(temp1);
			}
			if(reservedTablesPM.contains(TableRecords.getAllTheTables().get(i).getTableNumber())){
				Table temp1 = TableRecords.getAllTheTables().get(i);
				temp1.setIsReservedPM(true);
				temp.add(temp1);
			}
		}
		return temp;
	}
}
