package controllers;

import java.util.Date;
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

public class TableController {
	public static TableAll TableRecords;
	private ArrayList<Table> reservedToday; //does this need to be public static??
	private ReservationAll rL;
	private ArrayList<Reservation> reservationsTodayAM;
	private ArrayList<Reservation> reservationsTodayPM;
	
	public TableController(){
		TableRecords = new TableAll(5,5,10,10);
		reservedToday = new ArrayList<Table>();
		rL = ReservationController.allTheReservations;
		reservationsTodayAM = new ArrayList<Reservation>();
		reservationsTodayPM = new ArrayList<Reservation>();
	}
	
	public ArrayList<Table> getReservedToday(){
		return reservedToday;
	}
	public ArrayList<Reservation> getReservationsTodayAM(){
		return reservationsTodayAM;
	}
	public ArrayList<Reservation> getReservationsTodayPM(){
		return reservationsTodayPM;
	}
	public ReservationAll getReservationAll(){
		return rL;
	}
	
	
	public void run(){
		Date today = new GregorianCalendar().getTime();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String todayString = df.format(today);
		
		System.out.println("Syncing tables with reservations list");
		syncTablesWithReservations(todayString, this.rL);
		try{
			System.out.println("Testing removal of expired reservations");
			removeExpiredRes(rL, todayString, 30);
		}catch(ParseException e){
			System.out.println("OH SHIT.");
		}
		System.out.println("Please Select option: (Enter -1 to go back)");
		
		
		
		for(int i=0;i<TableRecords.getTotalNumOfTables();i++){
			TableRecords.getAllTheTables().get(i).displayTable();
		}
		
	}
	
	public void syncTablesWithReservations(String todayString, ReservationAll rL){
		reservedToday = checkAndSetReservedToday(todayString, rL);
	}
	public void removeExpiredRes(ReservationAll rL, String todayString, int time_limit) throws ParseException{		//To remove reservations that have expired! eg 30min still not here
		/*1) Find Reservations today, extract time slot for each reservation.  
		 *2) Find Pair each reservation with the table number. 
		 *3) If 30mins passed and isOccupied = false, remove reservation, set isReserved(timeslot) to false*/
		Date today = new GregorianCalendar().getTime();
		String restaurantShift = ReservationController.checkSlot(today);
		System.out.println("Restaurant shift: " + restaurantShift);
		reservationsTodayAM = rL.reservationsMadeOnDate(todayString, "AM");
		reservationsTodayPM = rL.reservationsMadeOnDate(todayString, "PM");
		ArrayList<Reservation> holdingArrayList = null;
		ArrayList<Calendar> timeOfArrival = new ArrayList<Calendar>();
		int cont=0;
		if(restaurantShift == "AM"){
			holdingArrayList = reservationsTodayAM;	
			cont = 1;
		}else if(restaurantShift=="PM"){
			holdingArrayList = reservationsTodayPM;
			cont = 1;
		}
		else{
			System.out.println("Restaurant is closed at the moment. Removal of expired reservations will resume once the restaurant re-opens.");
		}
		if(cont==1){
			for(int i=0;i<holdingArrayList.size();i++){
				String timeTemp = holdingArrayList.get(i).getTimeOfArrival();
				String dateTemp = holdingArrayList.get(i).getDateReserved();
				System.out.println("timeTemp:" + timeTemp);
				System.out.println("dateTemp:" + dateTemp);
				Calendar dateObjectTemp = ReservationController.stringToCalender("dd-MM-yyyy HHmm", dateTemp, timeTemp);
				timeOfArrival.add(dateObjectTemp); //Order of calendar objects in timeOfArrival is the same as reservation objects in reservationsTodayAM
			}	
			for(int i=0;i<timeOfArrival.size();i++){
				//if (timeOfArrivalAM + 30min) is still before now, that means their reservation should expire. 
				Calendar temp = timeOfArrival.get(i);
				temp.add(Calendar.MINUTE, time_limit);
				if(temp.getTime().before(today)){
					int tableNumber = holdingArrayList.get(i).getTableNumber();
					System.out.println("TableNumber:" + tableNumber);
					Table tempTable = TableRecords.getAllTheTables().get(tableNumber-1);
					if(!(tempTable.isOccupied())){
						//1.remove this reservation from reservation list
						//2.set isReservedAM/PM for that table to false.
						if(restaurantShift == "AM"){
							tempTable.setIsReservedAM(false);
						}
						else if(restaurantShift=="PM"){
							tempTable.setIsReservedPM(false);
						}
						Reservation todelete = holdingArrayList.get(i);
						rL.deleteReservation(todelete);	
					}	
				}
			}
			reservationsTodayAM = rL.reservationsMadeOnDate(todayString, "AM");//Refresh reservationsTodayAM.
			reservationsTodayPM = rL.reservationsMadeOnDate(todayString, "PM");
		}
		
		
		
	}
	//Set tables that are reserved accordingly, returns table number of those tables which are reserved.
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
