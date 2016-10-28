package entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Order {
	private int orderID;
	private int staffID;
	private ArrayList<MenuItem> mItems;
	private ArrayList<Integer> quantityMenuItems;
	private ArrayList<Double> subTotalCost;
	//private ArrayList<SpecialPackage> mPackage;
	//private ArrayList<Integer> quantityMenuPackages;
	private int tableID;
	private Date time;
	private double totalPrice;
	
	public Order(int orderID, int staffID, ArrayList<MenuItem> mItems, ArrayList<Integer> quantityMenuItems,
			int tableID, Date time, double totalPrice) {
		this.orderID = orderID;
		this.staffID = staffID;
		mItems = new ArrayList<MenuItem>();
		quantityMenuItems = new ArrayList <Integer>();
		subTotalCost= new ArrayList <Double>();
		this.tableID = tableID;
		time = Calendar.getInstance().getTime();
		this.totalPrice = 0;
	
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

	public ArrayList<MenuItem> getmItems() {
		return mItems;
	}

	public void setmItems(ArrayList<MenuItem> mItems) {
		this.mItems = mItems;
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
