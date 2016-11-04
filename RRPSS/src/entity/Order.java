package entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Order {
	private int orderID;
	private int staffID;
	private ArrayList<MenuItem> menuItems;
	private ArrayList<Integer> quantityMenuItems;
	private ArrayList<Double> subTotalCost;
	//private ArrayList<SpecialPackage> mPackage;
	//private ArrayList<Integer> quantityMenuPackages;
	private int tableID;
	private Date time;
	private double totalPrice;
	
	public Order(int orderID, int staffID, ArrayList<MenuItem> menuItems, ArrayList<Integer> quantityMenuItems,
			int tableID, Date time, double totalPrice) {
		this.orderID = orderID;
		this.staffID = staffID;
		menuItems = new ArrayList<MenuItem>();
		quantityMenuItems = new ArrayList <Integer>();
		subTotalCost= new ArrayList <Double>();
		this.tableID = tableID;
		time = Calendar.getInstance().getTime();
		this.totalPrice = 0;
	}
	
	
	private int findMenuItem (String name){
		for (int i = 0; i < menuItems.size(); i++){
			if (menuItems.get(i).getName().compareTo(name) == 0){
				return i;
			}
		}
		return -1;
	}
	
	public void addMenuItem (MenuItem item, int quantity){
		int pos = -1;
		for (int i = 0; i < menuItems.size(); i++){
			if (menuItems.get(i).getName().compareTo(item.getName()) == 0){
				pos = i;
			}
		}
		if (pos == -1){
			menuItems.add(item);
			quantityMenuItems.add(quantity);
		} else {
			quantityMenuItems.set(pos, quantityMenuItems.get(pos));
		}
			
	}
	
	public boolean removeMenuItem (String itemName, int quantity){
		int position = -1;
		for (int i = 0; i < menuItems.size(); i++){
			if (menuItems.get(i).getName().compareTo(itemName) == 0){
				position = i;
				break;
			}
		}
		if (quantity > quantityMenuItems.get(position) || quantity < 1){
			return false;
		}
		if (quantityMenuItems.get(position) <=1){
			menuItems.remove(position);
			quantityMenuItems.remove(position);
			}
		else{
			quantityMenuItems.set(position, quantityMenuItems.get(position)-1);
		}
		return true;
	}
	
	//public void addPackage
	//public boolean removePackage
	
	public void CalculatePrice(){
		totalPrice = 0;
		for (int i=0;i < menuItems.size(); i++){
			totalPrice = totalPrice + menuItems.get(i).getPrice()*quantityMenuItems.get(i);
		}
		
		//Recalculate Packages
	}
	
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public ArrayList<MenuItem> getmenuItems() {
		return menuItems;
	}

	public void setmenuItems(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public ArrayList<Integer> getQuantityMenuItems() {
		return quantityMenuItems;
	}

	public void setQuantityMenuItems(ArrayList<Integer> quantityMenuItems) {
		this.quantityMenuItems = quantityMenuItems;
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ArrayList<Double> getSubTotalCost() {
		return subTotalCost;
	}

	public void setSubTotalCost(ArrayList<Double> subTotalCost) {
		this.subTotalCost = subTotalCost;
	}
	
	
	
	
	
	
	
}
