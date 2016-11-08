package entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.sun.glass.ui.Pixels.Format;
import java.io.Serializable;

public class OrderSheetPerTable implements Serializable{
	private int tableNum;
	private int staffId;
	private Date dateNTime;
	private Date invoiceDateTime;
	private ArrayList<MenuItem> orders;
	private ArrayList<Integer> quantityFEO;
	private ArrayList<Double> subTotalPrice;
	private ArrayList<Integer> staffIds;
	private double totalBill;
	
	public OrderSheetPerTable(int tableNum, int staffId, Date today){
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
	
	public int getTableNum() {
		return tableNum;
	}
	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public Date getDateNTime() {
		return dateNTime;
	}
	public void setDateNTime(Date dateNTime) {
		this.dateNTime = dateNTime;
	}
	public ArrayList<MenuItem> getOrders() {
		return orders;
	}
	public void setOrders(ArrayList<MenuItem> orders) {
		this.orders = orders;
	}
	public ArrayList<Integer> getQuantityForEachOrder() {
		return quantityFEO;
	}
	public void setQuantityForEachOrder(ArrayList<Integer> quantityForEachOrder) {
		this.quantityFEO = quantityForEachOrder;
	}
	public ArrayList<Double> getSubTotalPrice() {
		return subTotalPrice;
	}
	public void setSubTotalPrice(ArrayList<Double> subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}
	public ArrayList<Integer> getStaffIDs(){
		return staffIds;
	}
	public void setStaffIds(ArrayList<Integer> staffIds){
		this.staffIds = staffIds; 
	}
	
	public double getTotalBill(){
		return totalBill;
	}
	
	public void setTotalBill(double bill){
		totalBill = bill;
	}
	
	public void setInvoiceDateTime(Date InvoiceDateTime){
		this.invoiceDateTime = InvoiceDateTime; 
	}
	public Date getInvoiceDateTime(){
		return invoiceDateTime;
	}
	//Add an order into ArrayList Orders...equivalent of customer ordering 1 dish
	public void addOrder(MenuItem item, int quantity,int staffId){
		if(orders.contains(item)){
			int existingItem = orders.indexOf(item);
			int existingQuantity = quantityFEO.get(existingItem);
			quantityFEO.set(existingItem, existingQuantity + quantity);
			subTotalPrice.set(existingItem, item.getPrice()*(existingQuantity + quantity));
			staffIds.set(existingItem, staffId);
		}
		else{
			orders.add(item);
			quantityFEO.add(quantity);
			subTotalPrice.add(item.getPrice() * quantity);
			staffIds.add(staffId);
			System.out.println("Item ordered: "+ item.getName() + " Quantity: " + quantity);
		}
		
	}
	
	//Update quantity of an order
	public void changeQuantity(MenuItem item, int quantity, int staffId){
		int i = orders.indexOf(item);
		if(i!=-1){
			quantityFEO.set(i,quantity);
			subTotalPrice.set(i, item.getPrice()*quantity);
			staffIds.set(i,staffId);
		}else{
			System.out.println("You have not ordered " + item.getName());
		}
	}
	//To remove order from OrderSheet
	public void removeOrder(MenuItem item){
		int i = orders.indexOf(item);
		if(i!=-1){
			orders.remove(i);
			quantityFEO.remove(i);
			subTotalPrice.remove(i);
			staffIds.remove(i);
		}else{
			System.out.println("You have not ordered" + item.getName());
		}
	}
	
	public void printOrder(){ 	//Prints out all orders in orderSheet
		for(int i=0;i<orders.size();i++){
			System.out.println(i+1+ ". Item:" + orders.get(i).getName() + ", Quantity: " + quantityFEO.get(i) +", Staff ID: "+ staffIds.get(i));
		}
	}
	
	
	public double billBeforeGST(){
		double beforeGST=0;
		for(int i=0;i<subTotalPrice.size();i++){
			beforeGST += subTotalPrice.get(i);
		}
		return beforeGST;
	}
	
	public void calTotalBill(Date timeOfInvoice){
		 //This means that customer is done. Ready to pay the bill.
		setInvoiceDateTime(timeOfInvoice);
		totalBill=billBeforeGST() + calGST();
		
		 System.out.println("TotalBill: " + totalBill);
		 
	}
	public double calGST(){
		
		double billBeforeGST= billBeforeGST();
		
		double gst = (22.5/100) * billBeforeGST;
		return gst;
	}
	
	public void printBill(){
		for(int i=0;i<orders.size();i++){
			System.out.println(i+1+ ". Item:" + orders.get(i).getName() + ", Quantity: " + quantityFEO.get(i) +", Price: "+ subTotalPrice.get(i));
		}
		System.out.println("SubTotal: "+ billBeforeGST());
		System.out.println("GST: " + calGST());
		System.out.println("Total bill: " + totalBill);
		//TotalBill should be subTotal + GST
		
	}
	

	public void printOrderSheetDetails(){
		System.out.println("");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HHmm");
		String dateToday = sdf.format(invoiceDateTime);
		System.out.println("Date and Time of Invoice: " + dateToday);
		System.out.println("Items ordered:");
		printBill();
		
	}
	
	
	
}
