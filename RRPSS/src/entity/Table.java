package entity;

import java.util.Date;

/**
 * Table class involves all the information of a specific table
 * @since 9/11/2016
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */
public class Table {

    private int tableNumber;
    private int tableSeats;
    private boolean isReservedAM;
    private boolean isReservedPM;
    private boolean isOccupied;
    private OrderSheetPerTable orderSheet;

    /**
     * Table Constructor will contain all the details of a table
     *
     * @param tableNumber The number assigned to the table
     * @param tableSeats The number of seats on this table
     * @param isReservedAM Whether the table has been reserved in AM
     * @param isReservedPM Whether the table has been reserved in PM
     * @param isOccupied Whether the table is currently being occupied
     */
    public Table(int tableNumber, int tableSeats, boolean isReservedAM, boolean isReservedPM, boolean isOccupied) {
        this.tableNumber = tableNumber;
        this.tableSeats = tableSeats;
        this.isReservedAM = isReservedAM;
        this.isReservedPM = isReservedPM;
        this.isOccupied = isOccupied;
        this.orderSheet = null;

    }

    /**
     * getTableNumber method retrieves the table number
     *
     * @return the table number
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * setTableNumber method assigns the table number
     *
     * @param number the assigned table number
     */
    public void setTableNumber(int number) {
        this.tableNumber = number;
    }

    /**
     * getSeats method retrieves the seats of the table
     *
     * @return the seats of the table
     */
    public int getSeats() {
        return tableSeats;
    }

    /**
     * setSeats method will assign number of seats
     *
     * @param number assigned the number of seats
     */
    public void setSeats(int number) {
        this.tableSeats = number;
    }

    /**
     * isReservedAM retrieves whether the reserved nature of the table at AM
     *
     * @return table at AM is true or false
     */
    public boolean isReservedAM() {
        return isReservedAM;
    }

    /**
     * setIsReservedAM will assign the table to be available/unavailable at AM
     *
     * @param b table is reserved/unreserved AM
     */
    public void setIsReservedAM(boolean b) {
        this.isReservedAM = b;
    }

    /**
     * isReservedPM retrieves whether the reserved nature of the table at PM
     *
     * @return table at PM is true or false
     */
    public boolean isReservedPM() {
        return isReservedPM;
    }

    /**
     * setIsReservedPM will assigned the table to be available/unavailable at PM
     *
     * @param b table is reserved/unreserved PM
     */
    public void setIsReservedPM(boolean b) {
        this.isReservedPM = b;
    }

    /**
     * isOccupied method highlights if table is occupied
     *
     * @return occupied true or false
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * setIsOccupied will assign table to be occupied/unoccupied
     *
     * @param b occupied true or false
     */
    public void setIsOccupied(boolean b) {
        this.isOccupied = b;
    }

    /**
     * getOrderSheet method retrieve all the orders at the table
     *
     * @return orders at the table
     */
    public OrderSheetPerTable getOrderSheet() {
        return orderSheet;
    }

    /**
     * displayTable method will show the information related to the table
     */
    public void displayTable() {
        System.out.println("");
        System.out.println("Table Number: " + getTableNumber());
        System.out.println("Number of Seats: " + getSeats());
        if (isReservedAM) {
            System.out.println("Reserved in AM");
        }
        if (isReservedPM) {
            System.out.println("Reserved in PM");
        }
        if (isOccupied) {
            System.out.println("Table Occupied");
        }
        if(!(isOccupied || isReservedAM || isReservedPM)){
        	System.out.println("Available");
        }
    }

    /**
     * initOrderSheet method will create a new order sheet for the table
     *
     * @param tableNum the table number
     * @param staffId the staff id
     * @param today the current date today or the system date
     */
    public void initOrderSheet(int tableNum, int staffId, Date today) {
        orderSheet = new OrderSheetPerTable(tableNum, staffId, today);
    }

    /**
     * thisTableOrdered method will check whether the table has ordered or not
     * and print out the query
     */
    public void thisTableOrdered() {
        System.out.println("Table number: " + getTableNumber());
        if (orderSheet != null) {
            orderSheet.printOrder();
        } else {
            System.out.println("There are no orders. ");
        }
    }

    /**
     * customerCheckout method will transfer all the orders to a bill in sales
     * records
     *
     * @param timeOfInvoice the date that the function was called or the system
     * date
     */
    public void customerCheckout(Date timeOfInvoice) {
        orderSheet.calTotalBill(timeOfInvoice); //calculates total bill including gst, timeOfInvoice is time/date this function was called
        orderSheet.printBill();//Print invoice
        SalesRecords.allTheSalesRecords.add(orderSheet);//Adds this transaction to sale records
        setIsOccupied(false);//Change status of table back to unoccupied
        orderSheet = null; //set orderSheet back to null for turnover. 
    }
}
