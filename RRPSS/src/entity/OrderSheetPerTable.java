package entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;


import java.io.Serializable;
/**
 * OrderSheetPerTable class contains of what each table has ordered during 
 * each turnover. One OrderSheetPerTable object to one Table Object.
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */
public class OrderSheetPerTable implements Serializable {

    private int tableNum;
    private int staffId;
    private Date dateNTime;
    private Date invoiceDateTime;
    private ArrayList<MenuItem> orders;
    private ArrayList<Integer> quantityFEO;
    private ArrayList<Double> subTotalPrice;
    private ArrayList<Integer> staffIds;
    private double totalBill;
    private final static String SPACING2 = "##############################################";

    /**
     * Constructor for OrderSheetPerTable Class
     * @param tableNum table number
     * @param staffId staff who created order sheet
     * @param today date and time ordersheet was created
     */
    public OrderSheetPerTable(int tableNum, int staffId, Date today) {
        this.setTableNum(tableNum);
        this.setStaffId(staffId);
        this.setDateNTime(today);
        orders = new ArrayList<MenuItem>();
        quantityFEO = new ArrayList<Integer>();
        subTotalPrice = new ArrayList<Double>();
        staffIds = new ArrayList<Integer>();
        totalBill = 0;
        invoiceDateTime = null;
    }
    /**
     * Get table number of orderSheet
     * @return
     */
    public int getTableNum() {
        return tableNum;
    }
    /**
     * set Table number of ordersheet
     * @param tableNum
     */
    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }
    /**
     * Retrieve staff id in order sheet
     * @return
     */
    public int getStaffId() {
        return staffId;
    }
    /**
     * assign staff id in order sheet
     * @param staffId
     */
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
    /**
     * Retrieve date and time ordersheet was instantiated
     * @return
     */
    public Date getDateNTime() {
        return dateNTime;
    }
    /**
     * Assign date and time ordersheet was instantiated
     * @param dateNTime
     */
    public void setDateNTime(Date dateNTime) {
        this.dateNTime = dateNTime;
    }
    /**
     * Retrieve all the orders that customers at one table make
     */
    public ArrayList<MenuItem> getOrders() {
        return orders;
    }
    /**
     * Assign set of menuItems to ordersheet
     * @param orders refer to menuItem objects
     */
    public void setOrders(ArrayList<MenuItem> orders) {
        this.orders = orders;
    }
    /**
     * Retrieve ArrayList of Integer containing quantity ordered for each item
     * @return ArrayList<Integer> quantityFEO
     */
    public ArrayList<Integer> getQuantityForEachOrder() {
        return quantityFEO;
    }
    /**
     * Assign quantityFEO to specified ArrayList
     * @param quantityForEachOrder ArrayList of Integer objects
     */
    public void setQuantityForEachOrder(ArrayList<Integer> quantityForEachOrder) {
        this.quantityFEO = quantityForEachOrder;
    }
    /**
     * Retrieve ArrayList of Double type containing price of each item ordered * quantity
     * @return ArrayList<Double> subTotalPrice;
     */
    public ArrayList<Double> getSubTotalPrice() {
        return subTotalPrice;
    }
    /**
     * Assign subTotalPrice to given ArrayList
     * @param subTotalPrice ArrayList<Double> object to assign
     */
    public void setSubTotalPrice(ArrayList<Double> subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }
    /**
     * Retrieve staffIDs that keyed orders in for table 
     * @return ArrayList<Integer> containing staffID
     */
    public ArrayList<Integer> getStaffIDs() {
        return staffIds;
    }
    /**
     * Assign staffIds to given ArrayList
     * @param staffIds ArrayList<Integer> object to staffIds
     */
    public void setStaffIds(ArrayList<Integer> staffIds) {
        this.staffIds = staffIds;
    }
    /**
     * Retrieve total bill
     * @return totalBill attribute of order sheet. 
     */
    public double getTotalBill() {
        return totalBill;
    }
    /**
     * Assigns totalBill to given argument
     * @param bill argument to assign to totalBill
     */
    public void setTotalBill(double bill) {
        totalBill = bill;
    }
    /**
     * Assign date and time invoice was created to invoiceDateTime
     * @param InvoiceDateTime Date object
     */
    public void setInvoiceDateTime(Date InvoiceDateTime) {
        this.invoiceDateTime = InvoiceDateTime;
    }	
    
    /**
     * Retrieve date and time invoice was created
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
    public void addOrder(MenuItem item, int quantity, int staffId) {
        if (orders.contains(item)) {
            int existingItem = orders.indexOf(item);
            int existingQuantity = quantityFEO.get(existingItem);
            quantityFEO.set(existingItem, existingQuantity + quantity);
            subTotalPrice.set(existingItem, item.getPrice() * (existingQuantity + quantity));
            staffIds.set(existingItem, staffId);
        } else {
            orders.add(item);
            quantityFEO.add(quantity);
            subTotalPrice.add(item.getPrice() * quantity);
            staffIds.add(staffId);
            System.out.println("Item ordered: " + item.getName() + " Qty: " + quantity);
        }

    }

    //Update quantity of an order
    /**
     * ChangeQuantity method to amend quantity of an item that was ordered.
     * @param item the item to amend the quantity for
     * @param quantity the new quantity
     * @param staffId the staff 
     */
    public void changeQuantity(MenuItem item, int quantity, int staffId) {
        int i = orders.indexOf(item);
        if (i != -1) {
            quantityFEO.set(i, quantity);
            subTotalPrice.set(i, item.getPrice() * quantity);
            staffIds.set(i, staffId);
        } else {
            System.out.println("You have not ordered " + item.getName());
        }
    }
    //To remove order from OrderSheet
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
     * Caluclate price of all orders, excluding GST
     * @return
     */
    public double billBeforeGST() {
        double beforeGST = 0;
        for (int i = 0; i < subTotalPrice.size(); i++) {
            beforeGST += subTotalPrice.get(i);
        }
        return beforeGST;
    }
    
    /**
     * Calculates total bill, including gst.
     * @param timeOfInvoice date/time of checkout
     */
    public void calTotalBill(Date timeOfInvoice) {
        //This means that customer is done. Ready to pay the bill.
        setInvoiceDateTime(timeOfInvoice);
        totalBill = billBeforeGST() + calGST();
    }
    /**
     * Calculates gst 
     * @return gst
     */
    public double calGST() {

        double billBeforeGST = billBeforeGST();

        double gst = (22.5 / 100) * billBeforeGST;
        return gst;
    }
    /**
     * Prints invoice
     */
    public void printBill() {
		System.out.println("==========Emperial Fortune Cookies Restaurant============");
		System.out.println("Staff ID      : " + getStaffId() );
		System.out.println("Table ID        : " + tableNum );
		System.out.println("Date(DD/MM/YYYY): " + ( dateNTime ).getDate() + "/" + ( dateNTime ).getMonth() + "/" + ( ( dateNTime ).getYear() + 1900 ));
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
     * Print all attributes of order sheet
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
