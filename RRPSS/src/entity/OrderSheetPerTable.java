package entity;

import java.util.ArrayList;
import java.util.Date;

public class OrderSheetPerTable {
	private int tableNum;
	private int staffId;
	private Date dateNTime;
	private ArrayList<MenuItem> orders;
	private ArrayList<Integer> quantityFEO;
	private ArrayList<Double> subTotalPrice;
	private ArrayList<Integer> staffIds;
	
	public OrderSheetPerTable(int tableNum, int staffId, Date today){
		this.setTableNum(tableNum);
		this.setStaffId(staffId);
		this.setDateNTime(today);
		orders = new ArrayList<MenuItem>();
		quantityFEO = new ArrayList<Integer>();
		subTotalPrice = new ArrayList<Double>();
		staffIds = new ArrayList<Integer>();
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
	//Add an order into ArrayList Orders...equivalent of customer ordering 1 dish
	public void addOrder(MenuItem item, int quantity,int staffId){
		orders.add(item);
		quantityFEO.add(quantity);
		subTotalPrice.add(item.getPrice() * quantity);
		staffIds.add(staffId);
		System.out.println("Item ordered: "+ item.getName() + " Quantity: " + quantity);
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
	
	public void printOrder(){
		for(int i=0;i<orders.size();i++){
			System.out.println(i+1+ ". Item:" + orders.get(i).getName() + ", Quantity: " + quantityFEO.get(i) +", Staff ID: "+ staffIds.get(i));
		}
	}
	
	
	
}