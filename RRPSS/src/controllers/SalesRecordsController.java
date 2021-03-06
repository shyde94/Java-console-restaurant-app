package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.OrderSheetPerTable;
import entity.SalesRecords;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * SalesRecordsController
 * 
 * @since 9/11/2016
 *
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */
public class SalesRecordsController {

    private SalesRecords recordsOfSales;
    
    private final static String SPACING = "**********************************************";
    
    private final static String SPACING2 = "##############################################";

    /**
     * SalesRecordsController constructor will create a new sales records 
     * class
     */
    public SalesRecordsController() {
        recordsOfSales = new SalesRecords();
    }

    /**
     * getRecordsOfSales will retrieve all the invoices
     * @return the invoices
     */
    public SalesRecords getRecordsOfSales() {
        return recordsOfSales;
    }

    /**
     * setRecordsOfSales will assign all the invoices
     * @param s all the invoices
     */
    public void setRecordsOfSales(SalesRecords s) {
        recordsOfSales = s;
    }

    /**
     * run method will start the SalesRecordsController
     * @throws InputMismatchException input and output checking when writing
     * or reading
     */
    public void run() throws InputMismatchException {
        boolean x = true;
        Scanner input = new Scanner(System.in);
        while (x) {
            boolean y = true;
            this.displayMenuOptions();
            int choice = input.nextInt();
            System.out.println(SPACING2);
            switch (choice) {
                case (1):
                    do {
                        try {
                            double revenue = this.getRevenueToday();
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        }
                    } while (y);
                    break;
                case (2):
                    do {
                        try {
                            double revenue = this.getRevenueOnDate(1);
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        }
                    } while (y);
                    break;

                case (3):
                    do {
                        try {
                            double revenue = this.getRevenueOnDate(2);
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input. 3");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("There is no such option!");
                        }
                    } while (y);
                    break;
                case (4):
                    recordsOfSales.printAllSalesRecords();
                    break;
                case (-1):
                    return;
                default:
                    System.out.println("Please select a valid option");
            }
        }

    }

    /**
     * getRevenueToday method will print all the invoices made today as well as
     * print out the total revenue made
     * @return revenue made today
     */
    public double getRevenueToday() {
        System.out.println("GENERATING SALES RECORD...");
        Date today = new GregorianCalendar().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateToCheck = sdf.format(today);
        double revenue = recordsOfSales.GenerateSalesReport(dateToCheck, "dd-MM-yyyy");
        return revenue;
    }

    /**
     * getRevenueOnDate method will print all the invoices made on a given date
     * as well as a print out of the total revenue made
     * @param choice the date
     * @return the revenue on date
     */
    public double getRevenueOnDate(int choice) {
    	System.out.println("GENERATING SALES RECORD...");
        Scanner input = new Scanner(System.in);
        boolean x = true;
        String dateToCheck = "";
        double revenue = 0;
        //these variables are need for addItem() method
        //while loop to remain this method if wrong type selected
        if (choice == 1) { //get revenue on date
            System.out.println("Enter specific date in dd-mm-yyyy format: (Enter -1 to go back)");
            dateToCheck = input.next();
            if (dateToCheck.equals("-1")) {
                return revenue;
            }
            System.out.println("############# Sales report for: " + dateToCheck + " ###############");
            revenue = recordsOfSales.GenerateSalesReport(dateToCheck, "dd-MM-yyyy");
        } else if (choice == 2) { //get revenue in month
            System.out.println("Enter specific month in mm-yy format: (Enter -1 to go back)");
            dateToCheck = input.next();
            if (dateToCheck.equals("-1")) {
                return revenue;
            }
            
            
            System.out.println("############# Sales report for: " + dateToCheck + " ###############");
            revenue = recordsOfSales.GenerateSalesReport(dateToCheck, "MM-yy");
        }
        return revenue;

    }

    /**
     * saveSalesRecord saves all the invoices made to a file
     * @throws FileNotFoundException if the file is not found
     * @throws IOException if there is a problem with writing the object to 
     * the file
     */
    public static void saveSalesRecord() throws FileNotFoundException, IOException {
        File file = new File("SalesRecord.txt");
        ArrayList<OrderSheetPerTable> sales = SalesRecords.allTheSalesRecords;
        FileOutputStream fo = new FileOutputStream(file);
        ObjectOutputStream output = new ObjectOutputStream(fo);

        for (int i = 0; i < sales.size(); i++) {
            output.writeObject(sales.get(i));
        }

        System.out.println("Sales saved Successfully");
        output.close();
        fo.close();
    	
    }

    /**
     * loadSalesRecord will retrieve all the sales from the file
     * @throws FileNotFoundException if the file is not found
     * @throws IOException if there was an error in reading from the file 
     * during input
     * @throws ClassNotFoundException if the class for storing the read data 
     * cannot be found
     */
    public void loadSalesRecord() throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File("SalesRecord.txt");
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream input1 = new ObjectInputStream(fi);

        ArrayList<OrderSheetPerTable> sales = new ArrayList<OrderSheetPerTable>();

        try {
            while (true) {
                OrderSheetPerTable o = (OrderSheetPerTable) input1.readObject();
                sales.add(o);
            }
        } catch (EOFException ex) {
            recordsOfSales.setSalesRecords(sales);

            System.out.println("Sales loaded Successfully");
        }
    	

    }
    
    

    /**
     * displayMenuOptions method will display all the sales record options
     */
    public void displayMenuOptions() {
        System.out.println(SPACING);
        System.out.println("*            SALES RECORDS OPTIONS           *");
        System.out.println("*  Please select option: (Enter -1 to return)*");
        System.out.println("*    1. Calculate Revenue today              *");
        System.out.println("*    2. Calculate Revenue on specific date   *");
        System.out.println("*    3. Calculate Revenue in specific month  *");
        System.out.println("*    4. See all sales records                *");
        System.out.println(SPACING);
    }
}
