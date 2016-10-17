package entity;

public class MenuItem {
	private double price;
	private String name;
	private String description;
	
	
	//Constructors
	public MenuItem(){
		price = 0;
		name = "nil";
		description = "nil";
	}
	
	public MenuItem(double price, String name, String x){
		this.price = price;
		this.name = name;
		this.description = x;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getName(){
		return this.name;
	}
}
