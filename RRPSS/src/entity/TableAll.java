package entity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import controllers.ReservationController;

public class TableAll {
	//Private variables
	private ArrayList<Table> allTheTables;
	private static int numTenSeats;
	private static int numEightSeats;
	private static int numFourSeats;
	private static int numTwoSeats;
	private int totalNumOfTables;
	private enum SeatsPerTable {
	    A(10), B(8), C(4), D(2);
		private int value;
		private SeatsPerTable(int value) {
				this.value = value;
		}
		public int getValue() {
	        return value;
	    }
	}
	
	private ArrayList<Reservation> reservationsTodayAM;
	private ArrayList<Reservation> reservationsTodayPM;
	
	
	//Constructor
	public TableAll(int a, int b, int c ,int d){
		allTheTables = new ArrayList<Table>();
		numTenSeats = a;
		numEightSeats = b;
		numFourSeats = c;
		numTwoSeats = d;
		totalNumOfTables = a+b+c+d;
		setUpTables();
		
		reservationsTodayAM = new ArrayList<Reservation>();
		reservationsTodayPM = new ArrayList<Reservation>();
	}
	
	
	public ArrayList<Table> getAllTheTables() {
		return allTheTables;
	}
	public void setAllTheTables(ArrayList<Table> allTheTables) {
		this.allTheTables = allTheTables;
	}
	public static int getNumTenSeats() {
		return numTenSeats;
	}
	public static void setNumTenSeats(int numTenSeats) {
		TableAll.numTenSeats = numTenSeats;
	}
	public static int getNumEightSeats() {
		return numEightSeats;
	}
	public static void setNumEightSeats(int numEightSeats) {
		TableAll.numEightSeats = numEightSeats;
	}
	public static int getNumFourSeats() {
		return numFourSeats;
	}
	public static void setNumFourSeats(int numFourSeats) {
		TableAll.numFourSeats = numFourSeats;
	}
	public static int getNumTwoSeats() {
		return numTwoSeats;
	}
	public static void setNumTwoSeats(int numTwoSeats) {
		TableAll.numTwoSeats = numTwoSeats;
	}
	public int getTotalNumOfTables(){
		return totalNumOfTables;
	}
	
	public void AddTable(Table temp){
		allTheTables.add(temp);
	}
	
	public ArrayList<Reservation> getReservationsTodayAM(){
		return reservationsTodayAM;
	}
	
	public ArrayList<Reservation> getReservationsTodayPM(){
		return reservationsTodayPM;
	}
	
	public void setUpTables(){	//This function inserts tables into ArrayList allTheTables
		int count = 1;
		for(int i=0;i<numTwoSeats;i++){
			Table table = new Table(count, SeatsPerTable.D.getValue(), false, false, false);
			AddTable(table);
			count++;
		}
		for(int i=0;i<numFourSeats;i++){
			Table table = new Table(count, SeatsPerTable.C.getValue(), false, false, false);
			AddTable(table);
			count++;
		}
		for(int i=0;i<numEightSeats;i++){
			Table table = new Table(count, SeatsPerTable.B.getValue(), false, false, false);
			AddTable(table);
			count++;
		}
		for(int i=0;i<numTenSeats;i++){
			Table table  = new Table(count, SeatsPerTable.A.getValue(), false, false, false);
			AddTable(table);
			count++;
		}
		
		
		
	}
	
	public void showAvailableTables(){
		for(int i=0;i<allTheTables.size();i++){
			if(!(allTheTables.get(i).isOccupied())){
				allTheTables.get(i).displayTable();
			}
		}
	}
	
	public void showTableStatuses(){
		for(int i=0;i<allTheTables.size();i++){
			allTheTables.get(i).displayTable();
		}
	}
	
	public void syncTables(String todayString, ReservationAll rL){
		ArrayList<Integer> reservedTablesAM = rL.reservedTablesOnDate(todayString, "AM");
		ArrayList<Integer> reservedTablesPM = rL.reservedTablesOnDate(todayString, "PM");
		for(int i=0;i<totalNumOfTables;i++){
			if(reservedTablesAM.contains(allTheTables.get(i).getTableNumber())){
				Table temp1 = allTheTables.get(i);
				temp1.setIsReservedAM(true);
			}
			if(reservedTablesPM.contains(allTheTables.get(i).getTableNumber())){
				Table temp1 = allTheTables.get(i);
				temp1.setIsReservedPM(true);
			}
		}
	}
	
	public ArrayList<Table> getReservedTables(){
		ArrayList<Table> tablesReservedToday = new ArrayList<Table>();
		Table temp = null;
		for(int i=0;i<totalNumOfTables;i++){
			if(allTheTables.get(i).isReservedAM() || allTheTables.get(i).isReservedPM()){
				temp = allTheTables.get(i);
				tablesReservedToday.add(temp);
			}
		}
		return tablesReservedToday;
	}
	
	public ArrayList<Table> getAvailableTables(){
		ArrayList <Table> tablesAvailable = new ArrayList<Table>();
		Table temp = null;
		for(int i=0;i<totalNumOfTables;i++){
			if(allTheTables.get(i).isOccupied()){
				temp = allTheTables.get(i);
				tablesAvailable.add(temp);
			}
		}
		return tablesAvailable;
	}
	
	//Problem here...only removes reservations on today. Not on days before today...hmmmm
	public void expireReservations(ReservationAll rL, String todayString, int time_limit) throws ParseException{
		Date today = new GregorianCalendar().getTime();
		String restaurantShift = ReservationController.checkSlot(today);
		//System.out.println("Restaurant shift: " + restaurantShift);
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
				//System.out.println("timeTemp:" + timeTemp);
				//System.out.println("dateTemp:" + dateTemp);
				Calendar dateObjectTemp = ReservationController.stringToCalender("dd-MM-yyyy HHmm", dateTemp, timeTemp);
				timeOfArrival.add(dateObjectTemp); //Order of calendar objects in timeOfArrival is the same as reservation objects in reservationsTodayAM
			}	
			for(int i=0;i<timeOfArrival.size();i++){
				//if (timeOfArrivalAM + 30min) is still before now, that means their reservation should expire. 
				Calendar temp = timeOfArrival.get(i);
				temp.add(Calendar.MINUTE, time_limit);
				if(temp.getTime().before(today)){
					int tableNumber = holdingArrayList.get(i).getTableNumber();
					//System.out.println("TableNumber:" + tableNumber);
					Table tempTable = allTheTables.get(tableNumber-1);
					//if(!(tempTable.isOccupied())){
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
					//}	
				}
			}
			reservationsTodayAM = rL.reservationsMadeOnDate(todayString, "AM");//Refresh reservationsTodayAM.
			reservationsTodayPM = rL.reservationsMadeOnDate(todayString, "PM");
		}
		
	}
	
	public void printReservationsArrayList(ArrayList<Reservation> r){
		for(int i=0;i<r.size();i++){
			r.get(i).printReservation();
		}
	}
	
	public Table getTable(int tableNumber){
		Table temp = allTheTables.get(tableNumber-1);
		return temp;
	}
	
	public boolean checkOrderSheetInit(int tableNumber){
		Table temp = getTable(tableNumber);
		if(temp.getOrderSheet()==null){
			return false;
		}else{
			return true;
		}
	}
	public void initialiseOrderSheetForTable(int tableNumber, int staffId, Date today){
		if(!(checkOrderSheetInit(tableNumber))){
			Table temp = getTable(tableNumber);
			temp.initOrderSheet(tableNumber, staffId, today);
		}
	}
	
	public ArrayList<OrderSheetPerTable> consolidateOrderSheets(){
		ArrayList<OrderSheetPerTable> allTheOrderSheets = new ArrayList<OrderSheetPerTable>();
		for(int i=0;i<allTheTables.size();i++){
			Table tempTable = allTheTables.get(i);
			if(tempTable.getOrderSheet()!=null){
				allTheOrderSheets.add(tempTable.getOrderSheet());
			}
		}
		
		return allTheOrderSheets;
	}
	
	

	
}
