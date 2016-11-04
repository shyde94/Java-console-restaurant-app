package entity;

import java.util.ArrayList;

public class PromotionalPackage extends Menu {

    private String pName;
    private double pPrice;
    private String pDesc;
    private ArrayList<MenuItem> pItemList;
    private static Menu pMenu;

    public PromotionalPackage(String pName, Double pPrice, String pDesc, ArrayList<MenuItem> pItemList) {;
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDesc = pDesc;
        this.pItemList = pItemList;
    }

    //Print Method for all the Promotional Packages
    
    
    
    
    //update
    public void update(MenuItem menuItem){
        pItemList.remove(menuItem);
        double totalPrice = 0;
        for (int i = 0; i < pItemList.size(); i++ ){
            totalPrice = totalPrice + pItemList.get(i).getPrice();
        }
        if (totalPrice < pPrice){
            totalPrice = pPrice;
        }
    }
    
    public void update (MenuItem oldItem, MenuItem newItem){
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
    
    /*
    public void printPPackage(){
        System.out.println(getName()+ "   "+ getPrice()+"   "+getDesc());
        System.out.println()
                
    }
    */
    
    
    public String getName() {
        return pName;
    }

    public double getPrice() {
        return pPrice;
    }

    public String getDesc() {
        return pDesc;
    }
    
    
    
    
    public ArrayList<MenuItem> getItemList() {
        return pItemList;
    }

    
    //Set the menu containing the promotional package
    public static void setMenu(Menu menu) {
        pMenu = menu;
    }

}
