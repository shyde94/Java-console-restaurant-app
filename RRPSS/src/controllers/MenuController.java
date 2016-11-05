package controllers;

import entity.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Menu;
import entity.MenuItem;

public class MenuController {

    public static Menu menu;

    public MenuController() {
        menu = new Menu();
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void run() throws InputMismatchException {
        boolean x = true;
        Scanner input = new Scanner(System.in);
        while (x) {
            boolean y = true;
            this.displayMenuOptions();
            int choice = input.nextInt();
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
                    do {
                        try {
                            this.updateMenuItem();
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        }
                    } while (y);
                    break;

                case (3):
                    do {
                        try {
                            this.deleteMenuItem();
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input. 3");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("There is no such option!");
                        }
                    } while (y);
                    break;
                case (4):
                    menu.printMenuItem();
                    break;
                case (-1):
                    return;
                default:
                    System.out.println("Please select a valid option");
            }
        }
    }

    //createMenuItem() - 1st option, to add item into menu. Introduce file writing operator
    public void createMenuItem() {
        menu.addItem("pizza1", 8, "temp1", "Mains");
        menu.addItem("pizza2", 9, "temp2", "Mains");
        menu.addItem("pizza3", 18, "temp3", "Mains");
        menu.addItem("pizza1", 8, "temp1", "Mains");
        menu.addItem("pizza2", 9, "temp2", "Mains");
        menu.addItem("pizza3", 18, "temp3", "Mains");
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
            this.showMenuTypes();
            choice = input.nextInt();
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
                return; //exit createMenuItem() once new item is added
            }
        }
    }

    public void updateMenuItem() {
        Scanner input = new Scanner(System.in);
        boolean x = true;
        String type = "";
        while (x) {	//while loop to remain this method if wrong type selected
            int choice = 0;
            System.out.println("######## Update menu item ########");
            //menu.printMenuL();
            this.showMenuTypes();
            choice = input.nextInt();
            if (choice == -1) {
                return;
            }
            String temp_type = menu.getTypes().get(choice - 1);
            ArrayList<MenuItem> updateItem = menu.reOrderItems(temp_type);

            boolean y = true;
            int index = 0;
            do {

                String name = "";
                double price = -1;
                String desc = "";
                try {
                    System.out.println("Choose item to update: (Enter -1 to go back)");
                    printTempArrayList(updateItem);
                    index = input.nextInt();
                    if (index == -1) {
                        y = false;
                        continue;
                    }
                    index--;

                    String yesno;

                    System.out.println("Item to update:");
                    //	menu.getTypeM(type).get(index).printItem();

                    System.out.println("Update name? Enter y for yes, n for no, -1 to go back");
                    input.nextLine();
                    yesno = input.nextLine();
                    if (yesno.equals("-1")) {
                        continue;
                    }
                    switch (yesno) {
                        case ("y"):
                            System.out.println("Enter new name:");
                            name = input.nextLine();
                            System.out.println("New name: " + name);
                            break;
                        case ("n"):
                            break;
                        default:
                            System.out.println("Invalid option. Enter y or n");
                    }

                    System.out.println("Update price? Enter y for yes, n for no, -1 to go back");
                    yesno = input.nextLine();
                    if (yesno.equals("-1")) {
                        continue;
                    }
                    switch (yesno) {
                        case ("y"):
                            System.out.println("Enter price:");
                            price = input.nextDouble();
                            break;
                        case ("n"):
                            break;
                        default:
                            System.out.println("Invalid option. Enter y or n");
                    }

                    System.out.println("Update description? Enter y for yes, n for no, -1 to go back");
                    input.nextLine();
                    yesno = input.nextLine();
                    if (yesno.equals("-1")) {
                        continue;
                    }
                    switch (yesno) {
                        case ("y"):
                            System.out.println("Enter description:");
                            desc = input.nextLine();
                            System.out.println("New description: " + desc);
                            break;
                        case ("n"):
                            break;
                        default:
                            System.out.println("Invalid option. Enter y or n");
                    }
                    menu.updateItem(name, price, desc, temp_type, index);
                    y = false;
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

    public void deleteMenuItem() {
        Scanner input = new Scanner(System.in);
        boolean x = true;
        int index = 0;
        while (x) {
            System.out.println("######## Remove Item: ########");
            //	menu.printMenuL();
            this.showMenuTypes();
            int choice = input.nextInt();
            if(choice == -1)return;
            String type = "";
            String temp_type = menu.getTypes().get(choice - 1);
            ArrayList<MenuItem> deleteItem = menu.reOrderItems(temp_type);
            
            boolean y = true;
            do {
                try {
                	if(deleteItem.isEmpty()){
                		System.out.println("There are no items to delete.");
                		break;
                	}else{
                		System.out.println("Choose item to remove: (Enter -1 to go back)");
                		printTempArrayList(deleteItem);
                		index = input.nextInt();
                		
                		
                	} 
                	if(index==-1)continue;
                	menu.removeItem(index, type);
            		y = false;

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
    
   public static MenuItem selectMenuItem(){
	   MenuItem tempMenuItem = null;
	   Scanner input = new Scanner(System.in);
       boolean x = true;
       int index = 0; String temp_type; ArrayList<MenuItem> toSelect = null;
       while (x) {
           showMenuTypes();
           boolean y = true;int choice=0;
           do{
        	   try{
        		   choice = input.nextInt();
        		   temp_type = menu.getTypes().get(choice - 1);
                   toSelect = menu.reOrderItems(temp_type);
        		   y = false;
        	   }catch(InputMismatchException e){
        		   System.out.println("Please enter a number.");
        		   input.nextLine();
        	   }catch(IndexOutOfBoundsException e){
        		   System.out.println("Invalid option. Please try again.");
        		   input.nextLine();
        	   }
           }while(y);
           if(choice == -1) return tempMenuItem;
           String type = "";
          
           
           y = true;
           do {
               try {
               	if(toSelect.isEmpty()){
               		System.out.println("There are no items to in menu.");
               		index = -1;
               	}else{
               		System.out.println("Choose item to select: (Enter -1 to go back)");
               		printTempArrayList(toSelect);
               		index = input.nextInt();
               		
               		
               	} 
               	if(index==-1)break;
               tempMenuItem = toSelect.get(index);
               System.out.println("here");
           		y = false;x = false;

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

    public void displayMenuOptions() {		//Display main menu options
        System.out.println("Menu Options (Enter -1 to go back)");
        System.out.println("1. Create new menu item");
        System.out.println("2. Update menu item");
        System.out.println("3. Remove menu item");
        System.out.println("4. Display menu");
    }

    public static void showMenuTypes() {
        System.out.println("Please select item type: (Enter -1 to go back)");
        menu.printTypes();
    }

    public static void printTempArrayList(ArrayList<MenuItem> a) {
        //System.out.println("banana");
        for (int i = 0; i < a.size(); i++) {
            System.out.print((i + 1) + ". ");
            a.get(i).printItem();
        }
    }

}
