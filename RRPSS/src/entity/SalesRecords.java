package entity;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import controllers.ReservationController;

public class SalesRecords {
	public static ArrayList<OrderSheetPerTable> allTheSalesRecords;
	
	public SalesRecords(){
		allTheSalesRecords = new ArrayList<OrderSheetPerTable>();
		
	}
        
        public ArrayList <OrderSheetPerTable> getSalesRecords(){
            return allTheSalesRecords;
        }
	
        public void setSalesRecords(ArrayList <OrderSheetPerTable> sales){
            allTheSalesRecords = sales;
        }
        
	public void printAllSalesRecords(){
		for(int i=0;i<allTheSalesRecords.size();i++){
			OrderSheetPerTable tempOrderSheet = allTheSalesRecords.get(i);
			tempOrderSheet.printOrderSheetDetails();
		}
	}
	
	public ArrayList<OrderSheetPerTable> QuerySalesRecordsOnDate(String dateToCheck, String format){
		ArrayList<OrderSheetPerTable> tempRecords = new ArrayList<OrderSheetPerTable>();
		for(int i=0;i<allTheSalesRecords.size();i++){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String dateInvoiceString = sdf.format(allTheSalesRecords.get(i).getInvoiceDateTime());
			if(dateToCheck.equals(dateInvoiceString)){
				tempRecords.add(allTheSalesRecords.get(i));
			}
		}
		if(tempRecords.isEmpty()){
			System.out.println("There were no records on this day or the format of the date/month entered is incorrect. Please ensure that correct date/month format is used.");
		}
		return tempRecords;
	}
	
	//Method can be used to check for month/date
	public double CalculateRevenueOnDate(String dateToCheck, String format){
		double revenue=0;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		ArrayList<Double>breakDown = new ArrayList<Double>();
		ArrayList<OrderSheetPerTable> tempRecords = QuerySalesRecordsOnDate(dateToCheck, format);
		if(!(tempRecords.isEmpty())){
			for(int i=0;i<tempRecords.size();i++){
				tempRecords.get(i).printOrderSheetDetails();
				revenue += tempRecords.get(i).getTotalBill();
			}
			System.out.println("Time period: " + dateToCheck);
			System.out.println("######################## Revenue: "+ revenue);
		}
		return revenue;
		
	}

}


