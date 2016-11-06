package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class PromotionalPackage extends MenuItem implements Serializable {
    private ArrayList<MenuItem> pItemList;
 

    public PromotionalPackage(String pName, Double pPrice, String pDesc, String ptype, ArrayList<MenuItem> a){
       
        super(pName, pPrice, pDesc,ptype);
        pItemList = a;
        
        
    }

    //Print Method for all the Promotional Packages

    
    
    //Removes an item from the promotional package and updates the price
    /*public void update(MenuItem menuItem){
        pItemList.remove(menuItem);
        double totalPrice = 0;
        for (int i = 0; i < pItemList.size(); i++ ){
            totalPrice = totalPrice + pItemList.get(i).getPrice();
        }
        if (totalPrice < pPrice){
            totalPrice = pPrice;
        }
    }*/
    
    //Replaces one item with another in the promotional package
    //and updates the price
    /*public void update (MenuItem oldItem, MenuItem newItem){
        pItemList.remove(oldItem);
        pItemList.add(newItem);
        double totalPrice = 0;
        for (int i = 0; i < pItemList.size(); i++ ){
            totalPrice = totalPrice + pItemList.get(i).getPrice();
        }
        if (totalPrice < pPrice){
            totalPrice = pPrice;
        }
    }   
    */
    /*
    public void printPPackage(){
        System.out.println(getName()+ "   "+ getPrice()+"   "+getDesc());
        System.out.println()
                
    }
    */
    
    /*
    public String getName() {
        return pName;
    }

    public double getPrice() {
        return pPrice;
    }

    public String getDesc() {
        return pDesc;
    }
    
    */
    
    
    public ArrayList<MenuItem> getItemList() {
        return pItemList;
    }

    public void printPromotionalPackage(){
        System.out.println("Name: " + super.getName());
        System.out.println("Price: " + super.getPrice());
        for(int i=0;i<pItemList.size();i++){
            pItemList.get(i).printItem();
        }
    }
    //Set the menu containing the promotional package
   

}
