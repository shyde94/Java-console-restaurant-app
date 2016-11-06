package entity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Menu {

    private ArrayList<MenuItem> menuItem;
    private ArrayList<String> types;
    private ArrayList<PromotionalPackage> pPackage;

    // requirement to separate menus into different categories
    // Constructor:
    public Menu() {
        menuItem = new ArrayList<MenuItem>();
        types = new ArrayList<String>();
        pPackage = new ArrayList<PromotionalPackage>();
        types.add("Mains"); //Testing Menu Types
        types.add("Desserts");
        types.add("Drinks");
        types.add("Promotional Package");
    }

    // Menu Type Functions
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

    //Promotional Package Functions
    public ArrayList<PromotionalPackage> getPPackage() {
        return this.pPackage;
    }

    public void setPPackage(ArrayList<PromotionalPackage> PPackage) {
        this.pPackage = PPackage;
    }

    //Menu Item Functions
    public ArrayList<MenuItem> getMenuItem() {
        return this.menuItem;
    }

    public void setMenuItem(ArrayList<MenuItem> menu) {
        this.menuItem = menu;
    }

    /**
     * Retrieves the position of the menu item id in the menuItem array
     *
     * @param id the position of the menu item in the menuItem array
     * @return the reference of the menu item
     */
    public MenuItem getMenuItemID(int id) {
        return menuItem.get(id - 1);
    }

    public void addItem(String name, double price, String desc, String type) {
        MenuItem item = new MenuItem(name, price, desc, type);
        menuItem.add(item);
        System.out.println("Item has been added");

    }
    
      public void addPPackage(String name, double price, String desc,String type, ArrayList<MenuItem> a) {
        PromotionalPackage tempPP = new PromotionalPackage(name, price, desc,type, a);
        System.out.println("Adding promotional package");
        pPackage.add(tempPP);
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
        //    menuItem.get(n).notifyPPackage();
        System.out.println("Item has been removed");
    }

   /* public void displayMenuAndItems(ArrayList<MenuItem> mItem) {
        for (int j = 0; j < types.size(); j++) {
            System.out.println("######" + types.get(j) + "######");
            for (int i = 0; i < mItem.size(); i++) {
                if (mItem.get(i).getType().equals(types.get(j))) {
                    System.out.printf("%-30s", mItem.get(i).getName());
                    System.out.printf("%20s%n",
                            new DecimalFormat("$###,##0.00").format(mItem.get(i).getPrice()));
                    System.out.println("\"" + mItem.get(i).getDescription() + "\"");
                }
            }
        }
        //Promotional Packages
        System.out.println("******" + "Promotional Packages" + "******");
        for (int i = 0; i < pPackage.size(); i++) {
            System.out.println("i:" + i);
            System.out.printf("%-30s", "******" + pPackage.get(i).getName() + "******");
            System.out.printf("%20s%n",
                    new DecimalFormat("$###,##0.00").format(pPackage.get(i).getPrice()));
            System.out.println("\"" + pPackage.get(i).getDesc() + "\"");
            ArrayList<MenuItem> pItemList = pPackage.get(i).getItemList();
            for (int j = 0; j < pItemList.size(); j++) {
                System.out.println("- " + pItemList.get(j).getName());
            }
        }
    }*/
    
    public void printMenuItemsMenu(){
        System.out.println("Printing Items in ItemMenu");
        for(int i=0;i<menuItem.size();i++){
            menuItem.get(i).printItem();
        }
    }
    
    public void printPromotionalPackages(){
         System.out.println("Printing Items in Promotional Package");
        for(int i=0;i<pPackage.size();i++){
            pPackage.get(i).printPromotionalPackage();
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

    //Promotional Package Functions
  

}
