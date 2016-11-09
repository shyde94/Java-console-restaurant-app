package entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * PromotionalPackage Class is inherited from MenuItem and contains more than
 * 1 menu item
 * 
 * 9/11/2016
 *
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */
public class PromotionalPackage extends MenuItem implements Serializable {
    private ArrayList<MenuItem> pItemList;
 
/**
 * PromotionalPackage Constructor describes a promotional package, taken from
 * MenuItem
 * @param pName the promotional package name
 * @param pPrice the promotional package price
 * @param pDesc the promotional package description
 * @param ptype the promotional package type
 * @param a the arraylist of items in the promotional package
 */
    public PromotionalPackage(String pName, Double pPrice, String pDesc, String ptype, ArrayList<MenuItem> a){
       
        super(pName, pPrice, pDesc,ptype);
        pItemList = a;
        
        
    }
    
    /**
     * getItemList method retrieves the MenuItems in the promotional package
     * @return 
     */
    public ArrayList<MenuItem> getItemList() {
        return pItemList;
    }

    /**
     * printPromotionalPackage method will print out the promotional package 
     * details
     */
    public void printPromotionalPackage(){
        System.out.println("Name: " + super.getName());
        System.out.println("Price: " + super.getPrice());
        for(int i=0;i<pItemList.size();i++){
            pItemList.get(i).printItem();
        }
    }
    //Set the menu containing the promotional package
   

}
