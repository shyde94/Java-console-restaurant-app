package entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MenuItem implements Serializable{
	private double price;
	private String name;
	private String description;
	private String type;
        
	
	
	//Constructors
        public MenuItem(){}
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
        
        
  










//IGNORE EVERYTHING BELOW THIS LINE
        
        /**
         * Notify all promotional packages containing MenuItem to update
         */
        //public void notifyPPackage (){
        //    for (int i = 0; i< containsPackage.size();i++){
        //        containsPackage.get(i).update(this);
        //    }
        //}
        /**
         * Notify all promotional packages containing MenuItem to update
         * Replacing the old item with a different menu item
         * @param menuItem replaces the old menu item in the PPackage
         */
        //public void notifyPPackage(MenuItem menuItem){
        //for (int i = 0; i< containsPackage.size();i++){
        //        containsPackage.get(i).update(this, menuItem);
        //    }
        //}
        
        /**
         * Attach PPackage to this specified menu item
         * PPackage will receive updates as this item is updated
         * @param promotionalPackage  Reference for the attached PPackage 
         */
        //public void attach (PromotionalPackage promotionalPackage){
        //    containsPackage.add(promotionalPackage);
        //}
        
        /**
         * Detach PPackage to this specified menu item
         * PPackage will no longer receive updates as this item is updated
         * @param promotionalPackage Reference for the attached PPackage
         */
        //public void detach (PromotionalPackage promotionalPackage){
        //   containsPackage.remove(promotionalPackage);
        //}


	
}
