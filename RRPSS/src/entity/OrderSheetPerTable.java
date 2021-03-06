package entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;


import java.io.Serializable;
/**
 * OrderSheetPerTable class contains of what each table has ordered during 
 * each turnover. One OrderSheetPerTable object to one Table Object.
 * 
 * @since 9/11/2016
 * 
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */
public class OrderSheetPerTable implements Serializable {

    private int tableNum;
    private String staffId;
    private Date dateNTime;
    private Date invoiceDateTime;
    private ArrayList<MenuItem> orders;
    private ArrayList<Integer> quantityFEO;
    private ArrayList<Double> subTotalPrice;
    private ArrayList<String> staffIds;
    private double totalBill;
    private final static String SPACING2 = "##############################################";

    /**
     * Constructor for OrderSheetPerTable Class
     * @param tableNum table number
     * @param staffId staff who created order sheet
     * @param today date and time ordersheet was created
     */
    public OrderSheetPerTable(int tableNum, String staffId, Date today) {
        this.setTableNum(tableNum);
        this.setStaffId(staffId);
        this.setDateNTime(today);
        orders = new ArrayList<MenuItem>();
        quantityFEO = new ArrayList<Integer>();
        subTotalPrice = new ArrayList<Double>();
        staffIds = new ArrayList<String>();
        totalBill = 0;
        invoiceDateTime = null;
    }
    /**
     * getTableNum method retrives table number in orderSheet
     * @return the table number
     */
    public int getTableNum() {
        return tableNum;
    }
    /**
     * setableNum method assigns table number of ordersheet
     * @param tableNum the table number
     */
    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }
    /**
     * getStaffId method retrieves staff id in order sheet
     * @return the staff id
     */
    public String getStaffId() {
        return staffId;
    }
    /**
     * setStaffId method assigns staff id in order sheet
     * @param staffId2 the staff id
     */
    public void setStaffId(String staffId2) {
        this.staffId = staffId2;
    }
    /**
     * getDateNTime method retrieves date and time ordersheet was instantiated
     * @return the date and time
     */
    public Date getDateNTime() {
        return dateNTime;
    }
    /**
     * setDateNTime method assigns date and time ordersheet was instantiated
     * @param dateNTime the date and time
     */
    public void setDateNTime(Date dateNTime) {
        this.dateNTime = dateNTime;
    }

    /**
     * getORders method retrieves all the orders that customers at one table 
     * make
     * @return the menu items orders
     */
    public ArrayList<MenuItem> getOrders() {
        return orders;
    }
    /**
     * setOrders assign set of menuItems to ordersheet
     * @param orders refer to menuItem objects
     */
    public void setOrders(ArrayList<MenuItem> orders) {
        this.orders = orders;
    }
    /**
     * getQuantityForEachOrder retrieve ArrayList of Integer 
     * containing quantity ordered for each item
     * @return quantityFEO returns the quantity of each order
     */
    public ArrayList<Integer> getQuantityForEachOrder() {
        return quantityFEO;
    }
    /**
     * setQuantityForEachOrder assigns quantityFEO to specified ArrayList
     * @param quantityForEachOrder ArrayList of Integer objects
     */
    public void setQuantityForEachOrder(ArrayList<Integer> quantityForEachOrder) {
        this.quantityFEO = quantityForEachOrder;
    }
    /**
     * getSubTotalPrice method retrieves ArrayList of Double type 
     * containing price of each item ordered * quantity
     * @return subTotalPrice is the price before GST
     */
    public ArrayList<Double> getSubTotalPrice() {
        return subTotalPrice;
    }
    /**
     * setSubTotalPrice method assigns subTotalPrice to given ArrayList
     * @param subTotalPrice ArrayList<Double> object to assign
     */
    
    /**
     * setSubTotalPrice method assigns subTotalPrice to given ArrayList
     * @param subTotalPrice the subTotalPrice arraylist
     */
    public void setSubTotalPrice(ArrayList<Double> subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }
    
    /**
     * getStaffIDs method retrieves staffIDs that keyed orders in for table 
     * @return staffIds arraylist
     */
    public ArrayList<String> getStaffIDs() {
        return staffIds;
    }
    /**
     * setStaffIds method assigns staffIds to given ArrayList
     * @param staffIds staffIds arraylist
     */
    public void setStaffIds(ArrayList<String> staffIds) {
        this.staffIds = staffIds;
    }
    /**
     * getTotalBil method retrieves total bill
     * @return totalBill attribute of order sheet. 
     */
    public double getTotalBill() {
        return totalBill;
    }
    /**
     * setTotalBill method assigns totalBill to given argument
     * @param bill argument to assign to totalBill
     */
    public void setTotalBill(double bill) {
        totalBill = bill;
    }
    /**
     * setInvoiceDateTime method assigns date and time invoice was 
     * created to invoiceDateTime
     * @param InvoiceDateTime Date object
     */
    public void setInvoiceDateTime(Date InvoiceDateTime) {
        this.invoiceDateTime = InvoiceDateTime;
    }	
    
    /**
     * getInvoiceDateTime method retrieves date and time invoice was created
     * @return Date Object invoiceDateTime
     */
    public Date getInvoiceDateTime() {
        return invoiceDateTime;
    }
    //Add an order into ArrayList Orders...equivalent of customer ordering 1 dish
    /**
     * AddOrder method to add an order into order sheet
     * @param item MenuItem object to be added
     * @param quantity Desired number of MenuItem objects
     * @param staffId StaffId that keyed in order.
     */
    public void addOrder(MenuItem item, int quantity, String staffId) {
    	ArrayList<String> nameItems = new ArrayList<String>();
    	for(int i=0;i<orders.size();i++){
    		nameItems.add(orders.get(i).getName());
    	}
        if (nameItems.contains(item.getName())) {
            int existingItem = nameItems.indexOf(item.getName());
            int existingQuantity = quantityFEO.get(existingItem);
            quantityFEO.set(existingItem, existingQuantity + quantity);
            subTotalPrice.set(existingItem, item.getPrice() * (existingQuantity + quantity));
            staffIds.set(existingItem, staffId);
            
        } else {
            orders.add(item);
            quantityFEO.add(quantity);
            subTotalPrice.add(item.getPrice() * quantity);
            staffIds.add(staffId);
        }

    }
    
   

 
    /**
     * ChangeQuantity method to amend quantity of an item that was ordered.
     * @param item the item to amend the quantity for
     * @param quantity the new quantity
     * @param staffId the staff 
     */
    public void changeQuantity(MenuItem item, int quantity, String staffId) {
        int i = orders.indexOf(item);
        if (i != -1) {
            quantityFEO.set(i, quantity);
            subTotalPrice.set(i, item.getPrice() * quantity);
            staffIds.set(i, staffId);
        } else {
            System.out.println("You have not ordered " + item.getName());
        }
    }
 
    /**
     * removeOrder removes an order from order sheet. 
     * @param item MenuItem in order to remove from
     */
    public void removeOrder(MenuItem item) {
        int i = orders.indexOf(item);
        if (i != -1) {
            orders.remove(i);
            quantityFEO.remove(i);
            subTotalPrice.remove(i);
            staffIds.remove(i);
        } else {
            System.out.println("You have not ordered" + item.getName());
        }
    }
    /**
     * printOrder method to display contents of order
     */
    public void printOrder() { 	//Prints out all orders in orderSheet
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(i + 1 + ". Item:" + orders.get(i).getName() + ", Qty: " + quantityFEO.get(i) + ", Staff ID: " + staffIds.get(i));
        }
    }
    /**
     * billBeforeGST method calculate price of all orders, excluding GST
     * @return the price of all orders before GST
     */
    public double billBeforeGST() {
        double beforeGST = 0;
        for (int i = 0; i < subTotalPrice.size(); i++) {
            beforeGST += subTotalPrice.get(i);
        }
        return beforeGST;
    }
    
    /**
     * calTotalBill method Calculates total bill, including gst.
     * @param timeOfInvoice date/time of checkout
     */
    public void calTotalBill(Date timeOfInvoice) {
        //This means that customer is done. Ready to pay the bill.
        setInvoiceDateTime(timeOfInvoice);
        totalBill = billBeforeGST() + calGST();
    }
    /**
     * calGST method calculates the GST
     * @return gst
     */
    public double calGST() {

        double billBeforeGST = billBeforeGST();

        double gst = (22.5 / 100) * billBeforeGST;
        return gst;
    }
    /**
     * printBill method prints invoice
     */
    public void printBill() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	
		System.out.println("==========Emperial Fortune Cookies Restaurant============");
		System.out.println("Staff ID      : " + getStaffId() );
		System.out.println("Table ID        : " + tableNum );
		System.out.println("Date(DD/MM/YYYY): " + sdf.format(dateNTime) );
		System.out.println("---------------------------------------------------------");
		
		double sum = 0;
		
		if(orders.size() > 0){
			System.out.println("Menu Items  : ");
			
			for(int i = 0; i < orders.size(); i++ )
			{
                            if (!(orders.get(i).getType().equals("Promotional Package"))){
				System.out.printf("    %dx %-30s $%3.2f\n" , (quantityFEO.get(i)) , (orders.get(i)).getName() , ( (quantityFEO.get(i)) * (orders.get(i)).getPrice() ));
				sum += orders.get(i).getPrice()*(quantityFEO.get(i));
                            }
			}
		}
		
		if(orders.size() > 0){
			System.out.println("---------------------------------------------------------");
			System.out.println("Promotional Set Packages  : ");
			
			for(int i = 0; i < orders.size(); i++ )
			{
                            if (orders.get(i).getType().equals("Promotional Package")){
				System.out.printf("    %dx %-30s $%3.2f\n" , (quantityFEO.get(i)) , (orders.get(i)).getName() , ( (quantityFEO.get(i)) * (orders.get(i)).getPrice() ));
				sum += orders.get(i).getPrice()*(quantityFEO.get(i));
                            }
			}
		}
		
		System.out.println("---------------------------------------------------------");
			System.out.printf("SubTotal               :              $%.2f\n" , ( billBeforeGST()));
		
		System.out.printf("Taxes                  :              $%.2f\n" , ( calGST()));
		System.out.println("---------------------------------------------------------	");
		System.out.printf("TOTAL                  :              $%.2f\n" , (totalBill) );
		System.out.println("\n============= Thank you! Please come again! =============\n");
    }
    /**
     * printOrderSheetDetails method Print all attributes of order sheet
     */
    public void printOrderSheetDetails() {
        System.out.println("");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HHmm");
        String dateToday = sdf.format(invoiceDateTime);
        System.out.println(SPACING2);
        System.out.println("Date and Time of Invoice: " + dateToday);
        System.out.println("Items ordered:");
        printBill();
        System.out.println(SPACING2);

    }

}
