package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Menu;
import entity.MenuItem;
import entity.OrderSheetPerTable;
import entity.Table;
import entity.TableAll;

public class OrderController {
	private TableAll tIR;
	private Menu menuToOrderFrom;
	
	
	public OrderController(){
		tIR = TableController.TableRecords;
		menuToOrderFrom = MenuController.menu;
		
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
                            this.addOrderToOrderSheet();
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        }
                    } while (y);
                    break;
                case (2):
                    do {
                        try {
                            this.updateOrderSheetPerTable();
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        }
                    } while (y);
                    break;

               /* case (3):
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
                    break;*/
                case (-1):
                    return;
                default:
                    System.out.println("Please select a valid option");
            }
        }
    }
	
	public void displayMenuOptions(){
		System.out.println("Please select option: (Enter -1 to return)");
		System.out.println("1. Add Order"); //Each table must initialise order sheet first before orders can be added in.  
		System.out.println("2. Update pre-exisiting order");
		System.out.println("3. Remove pre-exisiting order");
		System.out.println("4. View orders from specific table");
		System.out.println("5. View All orders");
	}
	
	
	public void addOrderToOrderSheet() throws IndexOutOfBoundsException{
		//1. Ask for table number
		//2. Check if table has been initialised. If have proceed, if havent, must initialise ordersheet first. Input required: staffId, date, table Num)
		//3. Once init, drop down menu, get input for which item. Change status of table to 'occupied'
		//4. Ask for quantity
		//5. Add item to that tables ordersheet. 
		Date today = new GregorianCalendar().getTime();
		Scanner input = new Scanner(System.in);
		boolean x = true;
		boolean y = true;
		System.out.println("Key in order:");
		while(x){
			int tableNumber=0;
			int staffId=0;
			do{
				y = true;
				System.out.println("Enter staff ID (Enter -1 to go back)");
				try{
					staffId = input.nextInt();
					y = false;
				}catch(InputMismatchException e){
					System.out.println("Invalid staff ID");
					input.nextLine();
				}
			}while(y);
			if(staffId<0)return;
			Table tempTable = null;
			y = true;
			do{
				System.out.println("Please enter table number first: (Enter -1 to go back)");
				try{
					tableNumber = input.nextInt();
					tempTable = tIR.getTable(tableNumber);
					tIR.initialiseOrderSheetForTable(tableNumber, staffId, today);
					y = false;
				}catch(InputMismatchException e){
					System.out.println("Please input a number");
					input.nextLine();
				}catch(IndexOutOfBoundsException e){
					System.out.println("Invalid option. Please try again");
					input.nextLine();
				}
			}while(y);
			if(tableNumber ==-1)continue;
			//Get table object with corresponding table number
			//Check if orderSheet for table has been initialised;
			y = true;
			
			
			OrderSheetPerTable orderSheetTemp = tempTable.getOrderSheet();
			MenuItem tempMenuItem = MenuController.selectMenuItem();
			if(tempMenuItem != null){
				System.out.println("Please input quantity");
				int quantity=0;
				y = true;
				do{
					try{
						quantity = input.nextInt();
						y = false;
					}catch(InputMismatchException e){
						System.out.println("Please enter a number.");
						input.nextLine();
					}
				}while(y);
				if(quantity<0)continue;
				orderSheetTemp.addOrder(tempMenuItem, quantity, staffId);
				tempTable.setIsOccupied(true);
				System.out.println("ADDED IN TO ORDERRRR");
				orderSheetTemp.printOrder();
				x = false;
			}
			else{
				System.out.println("No menu item selected. Order cancelled");
			}
		}
	}
	
	
	
	//Method to update orderSheet
	public void updateOrderSheetPerTable(){
		Date today = new GregorianCalendar().getTime();
		Scanner input = new Scanner(System.in);
		boolean x = true;
		boolean y = true;
		System.out.println("Edit exisiting order:");
		while(x){
			int tableNumber=0;
			int staffId=0;
			do{
				y = true;
				System.out.println("Enter staff ID (Enter -1 to go back)");
				try{
					staffId = input.nextInt();
					y = false;
				}catch(InputMismatchException e){
					System.out.println("Invalid staff ID");
					input.nextLine();
				}
			}while(y);
			
			if(staffId<0)return;
			do{
				y = true;
				System.out.println("Please enter table number first: (Enter -1 to go back)");
				try{
					tableNumber = input.nextInt();
					y = false;
				}catch(InputMismatchException e){
					System.out.println("Please input a number");
					input.nextLine();
				}
			}while(y);
			if(tableNumber ==-1)continue;
			//Get table object with corresponding table number
			//Check if orderSheet for table has been initialised;
			
			Table tempTable = tIR.getTable(tableNumber);
			if(tIR.checkOrderSheetInit(tableNumber)){
				OrderSheetPerTable orderSheetTemp = tempTable.getOrderSheet();
				
				int choice = 0; int quantity =0;
				MenuItem tempMenuItem=null;
				do{
					y = true;
					orderSheetTemp.printOrder();
					System.out.println("Select item to change quantity for: (Enter -1 to go back)");
					try{
						choice = input.nextInt();
						y = false;
					}catch(InputMismatchException e){
						System.out.println("Please enter a number");
						input.nextLine();
					}
				}while(y);	
				if(choice<0)continue;
				
				do{	
					y = true;
					try{
						tempMenuItem = orderSheetTemp.getOrders().get(choice-1);
						System.out.println("Input new quantity: (Enter -1 to go back)");
						quantity = input.nextInt();
						y = false;
					}catch(InputMismatchException e){
						System.out.println("Please enter a number: ");
						input.nextLine();
					}catch(ArrayIndexOutOfBoundsException e){
						System.out.println("Order does not exist.Please try again.");
						input.nextLine();
					}catch(IndexOutOfBoundsException e){
						System.out.println("Order does not exist.Please try again.");
						input.nextLine();
					}
				}while(y);
				if(quantity < 0)continue;
				if(tempMenuItem != null){
					orderSheetTemp.changeQuantity(tempMenuItem, quantity, staffId);
					System.out.println("Order has been updated");
					x = false;
				}
				else{
					System.out.println("An error occured. No orders were updated.");
				}
				
				
			}
			else{
				System.out.println("This table has no orders in it");
			}

		}
	
	}
	
	
}
