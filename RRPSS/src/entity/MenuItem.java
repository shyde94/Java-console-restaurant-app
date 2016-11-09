package entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * MenuItem Class contains details about a menu item
 * 
 * 9/11/2016
 * 
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */

public class MenuItem implements Serializable{
	private double price;
	private String name;
	private String description;
	private String type;
        
	
	
	/**
         * MenuItem constructor with no arguments
         */
        public MenuItem(){}
        
        /**
         * MenuItem Constructor contains information about a menu item
         * @param name the menu item name
         * @param price the menu item price
         * @param desc the menu item description
         * @param type the menu item price
         */
	public MenuItem(String name, double price, String desc, String type){
		this.price = price;
		this.name = name;
		this.description = desc;
		this.type = type;
          
	}
	
	//accessors & mutators
        
        /**
         * getPrice method retrieves the price
         * @return the price
         */
	public double getPrice(){
		return this.price;
	}
	
        /**
         * getDescription method retrieves the description
         * @return the description
         */
	public String getDescription(){
		return this.description;
	}
	
        /**
         * getName method retrieves the menu item name
         * @return the name
         */
	public String getName(){
		return this.name;
	}
	
        /**
         * getType method retrieves the menu item type
         * @return the type
         */
	public String getType(){
		return this.type;
	}
	
        /**
         * setName method assigns the menu item name
         * @param name the name
         */
	public void setName(String name){
		this.name = name;
	}
        
        /**
         * setPrice method assigns the menu item price
         * @param price the price
         */
	public void setPrice(double price){
		this.price = price;
	}
        
        /**
         * setDesc method assigns the description of the menu item
         * @param desc the description
         */
	public void setDesc(String desc){
		this.description = desc;
	}
        
        /**
         * setType method assigns the menu item type
         * @param type the type
         */
	public void setType(String type){
		this.type = type;
	}
        
        /**
         * printItem method prints information relating to the item
         */
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
