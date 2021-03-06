package controllers;

import entity.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Menu;
import entity.MenuItem;
import entity.PromotionalPackage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * MenuController class controls restaurant menu. 
 * Includes methods to create, append and delete items and promotional packages. 
 * Also serves as a basic UI for user to use, with necessary control flows
 * 
 * @since 9/11/2016
 * 
 * @author Shide 
 * @author Eeyern 
 * @author Grace 
 * @author Xi Tong 
 *
 */
public class MenuController {

    public static Menu menu;

    public MenuController() {
        menu = new Menu();
    }

    public Menu getMenu() {
        return this.menu;
    }
    
    private final static String SPACING = "**********************************************";
    private final static String SPACING2 = "##############################################";
    /**
     * Allows user to choose desired option based on a drop down list.
     * Methods will be called based on what user inputs. 
     * @throws InputMismatchException invalid option
     */
    public void run() throws InputMismatchException {
        boolean x = true;
        Scanner input = new Scanner(System.in);
        while (x) {
            boolean y = true;
            this.displayMenuOptions();
            int choice = input.nextInt();
            System.out.println(SPACING2);
            switch (choice) {
                case (1):
                    do {
                        try {
                            this.createMenuItem();
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        } 
                    } while (y);
                    break;
                     case (2):
                    addPPackage();
                    //System.out.println("Promotional Package Created Successfully");
                    break;
                case (3):
                    do {
                        try {
                            this.updateMenuItem();
                           
                            //System.out.println("Item Updated Successfully");
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        }
                    } while (y);
                    break;

                case (4):
                    do {
                        try {
                            this.deleteMenuItem();
                            //System.out.println("Item Deleted Successfully");
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input. 3");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("There is no such option!");
                        }
                    } while (y);
                    break;
                case (5):
                    ArrayList<MenuItem> menuItems = menu.getMenuItem();
                   menu.displayMenuAndItems(menuItems);
                   // menu.printMenuItemsMenu();
                   // menu.printPromotionalPackages();
                    break;
                case (-1):
                    return;
                default:
                    System.out.println("Please select a valid option");
            }
        }
    }
    
    public void displayMenuOptions() {		//Display main menu options
        System.out.println(SPACING);
        System.out.println("*                MENU OPTIONS                *");
        System.out.println("*  Please select option: (Enter -1 to return)*");
        System.out.println("*  1. Create new menu item                   *");
        System.out.println("*  2. Create Promotional Package             *");
        System.out.println("*  3. Update menu item / Propotional package *");
        System.out.println("*  4. Remove menu item/ Promotional package  *");
        System.out.println("*  5. Display menu                           *");
        System.out.println(SPACING);
    }

    //Menu Item Functionality
    //createMenuItem() - 1st option, to add item into menu. Introduce file writing operator
    /**
     * Interacts with user for necessary inputs to create a new menu item. 
     */
    public void createMenuItem() {
        Scanner input = new Scanner(System.in);
        boolean x = true;
        String type = "";
        String name = "";
        double price = 0;
        String desc = "";
        //these variables are need for addItem() method
        while (x) {	//while loop to remain this method if wrong type selected
            int choice;
            System.out.println("######## Create new menu item ########");
            System.out.println("What type of item do you wish to add?");
            MenuController.showMenuTypes();
            choice = input.nextInt();
            System.out.println(SPACING2);
            if (choice == -1) {
                return;
            }
            String temp_type = menu.getTypes().get(choice - 1);
            //System.out.println("Invalid Option. No items added");		
            if (!temp_type.equals("")) {
                //Input name
                boolean y = true;
                System.out.println("Enter name of new menu item: (Enter -1 to go back)");
                input.nextLine();
                name = input.nextLine();
                if (name.equals("-1")) {
                    continue; //continue used to go back to choose mains, drinks or desserts
                }
                //Input price
                do {
                    try {
                        System.out.println("Enter price: (Enter -1 to go back)");
                        price = input.nextDouble();
                        if(price <-1){
                        	System.out.println("Please enter a positive number.");
                        	continue;
                        }
                        y = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter the price in digits");
                        input.nextLine();
                    }
                } while (y);
                if (price == -1) {
                    continue;
                }

                //input description
                System.out.println("Enter description of new item: (Enter -1 to go back)");
                input.nextLine();
                desc = input.nextLine();
                if (desc.equals("-1")) {
                    continue;
                }

                //Call method to add item into menu by type
                menu.addItem(name, price, desc, temp_type);
                try {
					saveMenu();
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Menu could not be saved");
	
				}
                return; //exit createMenuItem() once new item is added
            }
        }
    }
    /**
     * Interacts with user for necessary inputs to create update item in menu.
     */
    public void updateMenuItem() {
        Scanner input = new Scanner(System.in);
        Scanner inputString = new Scanner(System.in);
        boolean x = true;
        String type = "";
        boolean y = true;
        while (x) {	//while loop to remain this method if wrong type selected
            int choice = 0;
            System.out.println("######## Update menu item ########");
            //menu.printMenuL();
            String temp_type = "";
            ArrayList<MenuItem> updateItem = new ArrayList<MenuItem>();
            //This part needs to be changed to handle exceptions!
            do {
                y = true;
                MenuController.showMenuTypes();
                try {
                    choice = input.nextInt();
                    System.out.println(SPACING2);
                    if (choice == -1) {
                        break;
                    }
                    temp_type = menu.getTypes().get(choice - 1);
                    updateItem = menu.reOrderItems(temp_type);
                    y = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid choice.");
                    input.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid choice.");
                    input.nextLine();
                }
            } while (y);
            if (choice == -1) {
                return;
            }
            int index = 0;
            do {

                String name = "";
                double price = -1;
                String desc = "";
                try {
                    System.out.println("Choose item to update: (Enter -1 to go back)");
                    printTempArrayList(updateItem);
                    index = input.nextInt();
                    System.out.println(SPACING2);
                    if (index == -1) {
                        y = false;
                        continue;
                    }
                    index--;
                    name = updateItem.get(index).getName();
                    price = updateItem.get(index).getPrice();
                    desc = updateItem.get(index).getDescription();	
                    String yesno;

                    System.out.println("Item to update:");
                    //	menu.getTypeM(type).get(index).printItem();
                    y = true;
                    
                    do{
                    	System.out.println("Update name? Enter y for yes, n for no, -1 to go back");
                        yesno = inputString.nextLine();
                        if (yesno.equals("-1")) {
                            break;
                        }
                        
                        switch (yesno) {
                            case ("y"):
                                System.out.println("Enter new name:");
                                name = inputString.nextLine();
                                System.out.println("New name: " + name);
                                y = false;
                                break;
                            case ("n"):
                            	y=false;
                                break;
                            default:
                                System.out.println("Invalid option. Enter y or n");
                        }
                    }while(y);
                    if (yesno.equals("-1")) {
                        continue;
                    }

                    System.out.println("Update price? Enter y for yes, n for no, -1 to go back");
                    yesno = inputString.nextLine();
                    if (yesno.equals("-1")) {
                        continue;
                    }
                    switch (yesno) {
                        case ("y"):
                            System.out.println("Enter price:");
                            price = input.nextDouble();
                            if(price <-1){
                            	System.out.println("Please enter a positive number.");
                            	continue;
                            }
                            break;
                        case ("n"):
                            break;
                        default:
                            System.out.println("Invalid option. Enter y or n");
                    }

                    System.out.println("Update description? Enter y for yes, n for no, -1 to go back");
                    //input.nextLine();
                    yesno = inputString.nextLine();
                    if (yesno.equals("-1")) {
                        continue;
                    }
                    switch (yesno) {
                        case ("y"):
                            System.out.println("Enter description:");
                            desc = inputString.nextLine();
                            System.out.println("New description: " + desc);
                            
                            break;
                        case ("n"):
                            break;
                        default:
                            System.out.println("Invalid option. Enter y or n");
                    }
                    menu.updateItem(name, price, desc, temp_type, index);
                    try {
    					saveMenu();
    				} catch (ClassNotFoundException | IOException e) {
    					System.out.println("Menu could not be saved");
    	
    				}

                    y = false;
                    x = false;
                    return;

                } catch (InputMismatchException e) {
                    System.out.println("Please enter index in digits");
                    input.nextLine();
                } catch (IllegalStateException e) {
                    System.out.println("Please enter index in digits");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Item does not exist. Please try again");
                }
                
            } while (y);

        }
    }
    /**
     * Interacts with user for necessary inputs to remove desired item.
     */
    public void deleteMenuItem() {
        Scanner input = new Scanner(System.in);
        boolean x = true;
        int index = 0;
        boolean y = true;
        int choice=0;
        while (x) {
            System.out.println("######## Remove Item: ########");
            String temp_type = "";
            ArrayList<MenuItem> deleteItem = new ArrayList<MenuItem>();
            
            //	menu.printMenuL();
            do {
                y = true;
                MenuController.showMenuTypes();
                try {
                    choice = input.nextInt();
                    System.out.println(SPACING2);
                    if (choice == -1) {
                        break;
                    }
                    temp_type = menu.getTypes().get(choice - 1);
                    deleteItem = menu.reOrderItems(temp_type);
                    y = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid choice.");
                    input.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid choice.");
                    input.nextLine();
                }
            } while (y);
            if(choice==-1)return;

            do {
                try {
                    if (deleteItem.isEmpty()) {
                        System.out.println("There are no items to delete.");
                        break;
                    } else {
                        System.out.println("Choose item to remove: (Enter -1 to go back)");
                        printTempArrayList(deleteItem);
                        index = input.nextInt();
                        System.out.println(SPACING2);
                    }
                    if (index == -1) {
                        continue;
                    }
                    menu.removeItem(index - 1, temp_type);
                    try {
    					saveMenu();
    				} catch (ClassNotFoundException | IOException e) {
    					System.out.println("Menu could not be saved");
    	
    				}
                    y = false;
                    x = false;

                } catch (InputMismatchException e) {
                    System.out.println("Please enter index in digits");
                    input.nextLine();
                } catch (IllegalStateException e) {
                    System.out.println("Please enter index in digits");
                    input.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Item does not exist. Please try again");
                    input.nextLine();
                }
            } while (y);
        }
    }
    /**
     * Interacts with user to retrieve desired item.
     * @return MenuItem Object to be used in other functions. 
     */
    public static MenuItem selectMenuItem() {
        MenuItem tempMenuItem = null;
        Scanner input = new Scanner(System.in);
        boolean x = true;
        int index = 0;
        String temp_type;
        ArrayList<MenuItem> toSelect = null;
        while (x) {

            boolean y = true;
            int choice = 0;
            do {
                showMenuTypes();
                try {
                    choice = input.nextInt();
                    System.out.println(SPACING2);
                    if (choice == -1) {
                        break;
                    }
                    temp_type = menu.getTypes().get(choice - 1);
                    toSelect = menu.reOrderItems(temp_type);
                    y = false;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number.");
                    input.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid option. Please try again.");
                    input.nextLine();
                }
            } while (y);
            if (choice == -1) {
                return tempMenuItem;
            }
            String type = "";

            y = true;
            do {
                try {
                    if (toSelect.isEmpty()) {
                        System.out.println("There are no items to in menu.");
                        index = -1;
                    } else {
                        System.out.println("Choose item to select: (Enter -1 to go back)");
                        printTempArrayList(toSelect);
                        index = input.nextInt();
                        System.out.println(SPACING2);

                    }
                    if (index == -1) {
                        break;
                    }
                    tempMenuItem = toSelect.get(index - 1);
                    y = false;
                    x = false;

                } catch (InputMismatchException e) {
                    System.out.println("Please enter index in digits");
                    input.nextLine();
                } catch (IllegalStateException e) {
                    System.out.println("Please enter index in digits");
                    input.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Item does not exist. Please try again");
                    input.nextLine();
                }
            } while (y);
        }
        return tempMenuItem;
    }

    /**
     * Interacts with user for necessary inputs to create a new promotional package.
     */
    public void addPPackage() {
        Scanner input = new Scanner(System.in);
        Scanner inputString = new Scanner(System.in);
        boolean x = true;
        String name = "";
        double price = 0;
        String desc = "";
        int choice = 0;
        String type = "Promotional Package";
        //these variables are need for addItem() method
        while (x) {	//while loop to remain this method if wrong type selected
            System.out.println("######## Create new Promotional Package ########");
            //Input name
            boolean y = true;
            System.out.println("Enter name of Promotional Package: (Enter -1 to go back)");
            //input.nextLine();
            name = inputString.nextLine();
            if (name.equals("-1")) {
                return; //continue used to go back to choose mains, drinks or desserts
            }
            //Input Price
            do {
                try {
                    System.out.println("Enter price: (Enter -1 to go back)");
                    price = input.nextDouble();
                    if (price == -1) {
                    	input.nextLine();
                        break;
                    }
                  //Input Description
                    System.out.println("Enter description of new Promotional Package: (Enter -1 to go back)");
                    input.nextLine();
                    desc = inputString.nextLine();
                    if (desc.equals("-1")) {
                        continue;
                    }
                    y = false;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter the price in digits");
                    input.nextLine();
                }
            } while (y);
            if (price == -1) {
                continue;
            }

            

            //Input menuItemArray
            ArrayList<MenuItem> menuItemArray = new ArrayList<MenuItem>();
            System.out.println("Add items to : " + name);
            y = true;
            MenuItem selectedMenuItem = null;
            do {
                boolean z = true;
                selectedMenuItem = selectMenuItem();
                if(selectedMenuItem == null){
                	break;
                }
                menuItemArray.add(selectedMenuItem);
               
                
                do {
                    System.out.println(SPACING);
                    System.out.println("Select next option: (Enter -1 to go back)");
                    System.out.println("1. Add more items");
                    System.out.println("2. Save promotional package");
                    System.out.println(SPACING);
                    try {
                        choice = input.nextInt();
                        System.out.println(SPACING2);
                        if (choice < -1) {
                            System.out.println("Invalid Option.");
                            continue;
                        }
                        z = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid option");
                    }
                } while (z);
                if (choice == -1) {
                	menuItemArray.remove(menuItemArray.size()-1);
                    continue;
                }
                if(choice == 2)break;
            } while (y);
            if(selectedMenuItem == null){
            	input.nextLine();
            	continue;
            }
          
                menu.addPPackage(name, price, desc,type, menuItemArray); x = false;

                try {
                    saveMenu();
                } catch (IOException ex) {
                    //Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("An error occured. Changes made could not be saved");
                } catch (ClassNotFoundException ex) {
                   // Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                   System.out.println("An error occured. Changes made could not be saved");
                   
                }

            return; //exit createMenuItem() once new item is added
        }
    }

    /**
     * Saves Menu into text file, all changes made are recorded using this method. 
     * @throws FileNotFoundException if the file is not found
     * @throws IOException if there is an error with input and output
     * @throws ClassNotFoundException if the class is not found
     */
    public void saveMenu() throws FileNotFoundException, IOException, ClassNotFoundException {
        //Testing File IO
        File file = new File("Menu.txt");

        //replace with existing menu array
        ArrayList<MenuItem> menuItems = menu.getMenuItem();
        ArrayList<PromotionalPackage> pPackageTemp = menu.getPPackage();
        /*if(pPackageTemp.isEmpty()){
            System.out.println("THIS IS EMPTY!!!");
        }
        for(int i=0;i<pPackageTemp.size();i++){
            System.out.println("Start of for loop: " + i);
            pPackageTemp.get(i).printPromotionalPackage();
        }*/
        //Serialize collection of menu items
        //Takes File
        FileOutputStream fo = new FileOutputStream(file);
        //Make Object and takes FileOutputStream
        ObjectOutputStream output = new ObjectOutputStream(fo);
        //Write object into file
        for (int i = 0; i < menuItems.size(); i++) {
            //menuItems.get(i).printItem();
            output.writeObject(menuItems.get(i));
        }
        for(int i = 0; i<pPackageTemp.size();i++){
            //pPackageTemp.get(i).printPromotionalPackage();
              output.writeObject(pPackageTemp.get(i));
        }
   
        System.out.println("Menu saved successfully");
        output.close();
        fo.close();
    }
    /**
     * Loads menu from text file when program starts. 
     * @throws IOException if there is an error with the input and output
     * @throws ClassNotFoundException if the class is not found
     */
    public void loadMenu() throws IOException, ClassNotFoundException {
        //Import back into collection
        File file = new File("Menu.txt");
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream input1 = new ObjectInputStream(fi);
        ArrayList<MenuItem> menuItemsArray = new ArrayList<MenuItem>();
        ArrayList<PromotionalPackage> promoPack = new ArrayList<PromotionalPackage>();
        try {
            while (true) {
                MenuItem m = (MenuItem) input1.readObject();
                if(m.getClass() == PromotionalPackage.class){
                    //System.out.println("Yoohoo!");
                    promoPack.add((PromotionalPackage)m);
                }
                else{
                    menuItemsArray.add(m);
                }   
            }
        } catch (EOFException ex) {
            //Printing the menu items using displayMenuAndItems()()
            //Print the menu and items as well as passing the updated menu to menu object
            menu.setMenuItem(menuItemsArray);
            menu.setPPackage(promoPack);
            System.out.println("Menu loaded successfully");
            //menu.displayMenuAndItems(menuItemsArray);
        }

    }
    /**
     * Displays options related to menu control.
     */


    public static void showMenuTypes() {
        System.out.println(SPACING);
        System.out.println("Please select item type: (Enter -1 to go back)");
        menu.printTypes();
        System.out.println(SPACING);
    }

    public static void printTempArrayList(ArrayList<MenuItem> a) {
        //System.out.println("banana");
        for (int i = 0; i < a.size(); i++) {
            System.out.print((i + 1) + ". ");
            a.get(i).printItem();
        }
    }

}
