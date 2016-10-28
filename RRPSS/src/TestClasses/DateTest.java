package TestClasses;
	import java.util.Calendar;
	import java.util.Scanner;
	import java.util.Date;
	import java.util.GregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

	public class DateTest
	{
		
		public static void main (String [] args) throws ParseException{
				Scanner sc = new Scanner (System.in);

	            SimpleDateFormat sdf = new SimpleDateFormat();
	        	sdf.applyPattern("dd/MM/yyyy HH:mm");
	        	
	        	System.out.print("\nEnter reservation date (dd/mm/yyyy): ");
	        	String reservationDateStr = sc.next();

	        	System.out.print("Enter reservation time,"
	        			+ " in 24-hour format (hh:mm): ");
	        	String reservationTimeStr = sc.next();

				Date reservationDateTime = sdf.parse(reservationDateStr 
							+ " " + reservationTimeStr);
				
				System.out.println("Currently saved as this format: "+reservationDateTime);
				
				Calendar startDateTime = GregorianCalendar.getInstance();
				startDateTime.setTime(reservationDateTime);
				
				System.out.println("Gregorian Calendar used to edit specific fields of the date "+startDateTime);

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println(date);
				System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
				
				
		}
	}
