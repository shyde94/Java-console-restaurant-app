package entity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Menu {

    private ArrayList<MenuItem> menuItem;
    private ArrayList<String> types;

    // requirement to separate menus into different categories
    // Constructor:
    public Menu() {
        menuItem = new ArrayList<MenuItem>();
        types = new ArrayList<String>();
        types.add("Mains"); //Testing Menu Types
        types.add("Desserts");
        types.add("Drinks");
    }

    public ArrayList<String> getTypes() {
        return this.types;
    }

    public void addTypes(String name) {
        types.add(name);
    }

    public void printTypes() {
        System.out.println("Types are: ");
        for (int i = 0; i < types.size(); i++) {
            System.out.println((i + 1) + "." + types.get(i));
        }
    }

    public ArrayList<MenuItem> getMenuItem() {
        return this.menuItem;
    }
    
    public void setMenuItem(ArrayList<MenuItem> menu){
        this.menuItem = menu;
    }

    public void addItem(String name, double price, String desc, String type) {
        MenuItem item = new MenuItem(name, price, desc, type);
        menuItem.add(item);
        System.out.println("Item has been added");

    }

    public void updateItem(String name, double price, String desc, String type, int n) {
        MenuItem temp = null;
        temp = menuItem.get(n);
        ArrayList<MenuItem> updateItem = reOrderItems(type);
        temp = updateItem.get(n);
        temp.setName(name);
        temp.setPrice(price);
        temp.setDesc(desc);
        System.out.println("Item has been updated");
    }

    public ArrayList<MenuItem> reOrderItems(String itemType) {
        ArrayList<MenuItem> updateItem = new ArrayList<MenuItem>();
        int size = menuItem.size();
        // System.out.println("Size: " + size);
        for (int i = 0; i < menuItem.size(); i++) {
            if (menuItem.get(i).getType().equals(itemType)) {
                //System.out.println(menuItem.get(i));
                updateItem.add(menuItem.get(i));
            }
        }
        return updateItem;

    }

    public void removeItem(int n, String type) {
        menuItem.remove(n);
        System.out.println("Item has been removed");
    }

    public void displayMenuAndItems(ArrayList<MenuItem> mItem) {
        //Updates controller ArrayList to Menu Object ArrayList
        for (int j = 0; j < types.size(); j++) { 
            System.out.println("######" + types.get(j)+"######");                  
            for (int i = 0; i < mItem.size(); i++) {
                if (mItem.get(i).getType().equals(types.get(j))){
                    System.out.printf("%-30s", mItem.get(i).getName());
                    System.out.printf("%20s%n",
                          new DecimalFormat("$###,##0.00").format(mItem.get(i).getPrice()));
                    System.out.println("\"" + mItem.get(i).getDescription() + "\"");
                }
            }
        }
    }

    public void printMenuItem() {
        int x;
        System.out.println("Menu:");
        for (MenuItem s : menuItem) {
            x = menuItem.indexOf(s);
            x++;
            System.out.print(x + ". ");
            s.printItem();
        }
    }

}
