package entity;

import java.io.Serializable;

public class MenuItem implements Serializable{
	private double price;
	private String name;
	private String description;
	private String type;
	
	
	//Constructors
	public MenuItem(){
		price = 0;
		name = "nil";
		description = "nil";
		type = "nil";
	}
	
	public MenuItem(String name, double price, String desc, String type){
		this.price = price;
		this.name = name;
		this.description = desc;
		this.type = type;
	}
	
	//accessors & mutators
	public double getPrice(){
		return this.price;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public void setDesc(String desc){
		this.description = desc;
	}
	public void setType(String type){
		this.type = type;
	}
	
	public void printItem(){
		System.out.println( this.name + " --- "+ this.description + ", $" + this.price + " ("+this.type+")");
	}
	
}
