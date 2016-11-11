package controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Menu;
import entity.MenuItem;

import entity.Restaurant;
import entity.OrderSheetPerTable;
import entity.SalesRecords;
import entity.Staff;
import entity.StaffAll;
import entity.Table;
import entity.TableAll;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * OrderController Class organises the orders in the restaurant
 * 
 * @since 9/11/2016
 *
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */
public class OrderController {

    private final static String SPACING = "**********************************************";
    private final static String SPACING2 = "##############################################";
    private TableAll tIR;
    private Menu menuToOrderFrom;
    private StaffAll allTheStaff;

    /**
     * OrderController Constructor will invoke the creation of menu controller 
     * and table records
     */
    public OrderController(){
    	tIR = TableController.TableRecords;
    	menuToOrderFrom = MenuController.menu;
    	allTheStaff = new StaffAll();
    	
    }

    /**
     * @throws InputMismatchException there is a input error
     */
    public void run() throws InputMismatchException {
    	
        boolean x = true;boolean y = true;
        Scanner input = new Scanner(System.in);
        Scanner inputString = new Scanner(System.in);
        String staffId = "";
        do {
            y = true;
            System.out.println("Enter staff ID: (Enter -1 to go back)");
            try {
                staffId = inputString.nextLine();
                if(!(allTheStaff.checkId(staffId))){
                	staffId="-1";
                	System.out.println("Invalid staff ID");
                }
                System.out.println(SPACING2);
                y = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid staff ID");
                input.nextLine();
            }
        } while (y);
        if (staffId.equals("-1")) {
            return;
        }
        while (x) {
            y = true;
            int choice=0;
            do{
            	try{
                	this.displayMenuOptions();
                	choice = input.nextInt();
                	y= false;
                }catch(InputMismatchException e){
                	System.out.println("Please choose a valid option");
                	input.nextLine();
                }
            }while(y);
            
            y=true;
            System.out.println(SPACING2);
            switch (choice) {
                case (1):
                    do {
                        try {
                            this.addOrderToOrderSheet(staffId);
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        } catch (ParseException e) {
                            System.out.println("Reservations not removed.");
                        }
                    } while (y);
                    break;
                case (2):
                    do {
                        try {
                            this.updateOrderSheetPerTable(staffId);
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        }
                    } while (y);
                    break;

                case (3):
                    do {
                        try {
                            this.removeOrderFromOrderSheet(staffId);
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input. 3");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("There is no such option!");
                        }
                    } while (y);
                    break;
                case (4):
                    try {
                        this.PrintInvoiceAndCheckOut(staffId);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        System.out.println("An error has occured, this checkout could not be saved.");
                    }
                     {
                        try {
                            SalesRecordsController.saveSalesRecord();
                        } catch (IOException ex) {
                            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case (5):
                    this.viewOrder();
                    break;
                case (6):
                    this.showAllOrders();
                    break;
                case (-1):
                    return;
                default:
                    System.out.println("Please select a valid option");
            }
        }
    }

    /**
     * addOrderToOrderSheet method will create a new order
     * @throws IndexOutOfBoundsException index to the arraylist is out of range
     * @throws ParseException error has been reached unexpectedly whilst parsing
     * @param staffId the staff id
     */
    public void addOrderToOrderSheet(String staffId) throws IndexOutOfBoundsException, ParseException {
        //1. Ask for table number
        //2. Check if table has been initialised. If have proceed, if havent, must initialise ordersheet first. Input required: staffId, date, table Num)
        //3. Once init, drop down menu, get input for which item. Change status of table to 'occupied'
        //4. Ask for quantity
        //5. Add item to that tables ordersheet. 
        Date today = new GregorianCalendar().getTime();
        Scanner input = new Scanner(System.in);
        //Scanner inputString = new Scanner(System.in);
        boolean x = true;
        boolean y = true;
        System.out.println("Key in order:");
        String restaurantShift = ReservationController.checkSlot(today);
        while (x) {
            int tableNumber = 0;
            
            
            Table tempTable = null;
            y = true;
            do {
                System.out.println("Please enter table number first: (Enter -1 to go back)");
                try {
                	
                    tableNumber = input.nextInt();
                    System.out.println(SPACING2);

                    if (tableNumber == -1) {
                        return;
                    }
        
                    tempTable = tIR.getTable(tableNumber);
                    tIR.initialiseOrderSheetForTable(tableNumber, staffId, today);
                    y = false;
                } catch (InputMismatchException e) {
                    System.out.println("Please input a number");
                    input.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid option. Please try again");
                    input.nextLine();
                }
            } while (y);
            if (tableNumber == -1) {
                continue;
            }
            //Get table object with corresponding table number
            //Check if orderSheet for table has been initialised;
            y = true;

            OrderSheetPerTable orderSheetTemp = tempTable.getOrderSheet();
            MenuItem tempMenuItem = MenuController.selectMenuItem();
            if (tempMenuItem != null) {
                System.out.println("Please input quantity: (Enter -1 to go back)");
                int quantity = 0;
                y = true;
                do {
                    try {
                        quantity = input.nextInt();
                        System.out.println(SPACING2);
                        y = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number.");
                        input.nextLine();
                    }
                } while (y);
                if (quantity < 0) {
                    continue;
                }
                orderSheetTemp.addOrder(tempMenuItem, quantity, staffId);
                if (!(tempTable.isOccupied())) {
                    tempTable.setIsOccupied(true);
                }
                if (restaurantShift.equals("AM") && tempTable.isReservedAM()) {
                    tempTable.setIsReservedAM(false);
                } else if (restaurantShift.equals("PM") && tempTable.isReservedPM()) {
                    tempTable.setIsReservedPM(false);
                }

                //System.out.println("ADDED IN TO ORDERRRR");
                orderSheetTemp.printOrder();
                x = false;
            } else {
                System.out.println("No menu item selected. Order cancelled");
            }
        }
    }

    //Method to update orderSheet
    /**
     * updateOrderSheetPerTable will update the order
     * @param staffId the staff id
     */
    public void updateOrderSheetPerTable(String staffId) {
        Scanner input = new Scanner(System.in);
        //Scanner inputString = new Scanner(System.in);
        boolean x = true;
        boolean y = true;
        System.out.println("Edit exisiting order:");
        while (x) {
            int tableNumber = 0;
            
        
            do {
                y = true;
                System.out.println("Please enter table number first: (Enter -1 to go back)");
                try {
                    tableNumber = input.nextInt();
                    System.out.println(SPACING2);
                    y = false;
                } catch (InputMismatchException e) {
                    System.out.println("Please input a number");
                    input.nextLine();
                }
            } while (y);
            if (tableNumber == -1) {
                return;
            }
            //Get table object with corresponding table number
            //Check if orderSheet for table has been initialised;

            Table tempTable = tIR.getTable(tableNumber);
            if (tIR.checkOrderSheetInit(tableNumber)) {
                OrderSheetPerTable orderSheetTemp = tempTable.getOrderSheet();

                int choice = 0;
                int quantity = 0;
                MenuItem tempMenuItem = null;
                do {
                    y = true;
                    orderSheetTemp.printOrder();
                    System.out.println("Select item to change quantity for: (Enter -1 to go back)");
                    try {
                        choice = input.nextInt();
                        System.out.println(SPACING2);
                        if(choice==-1)break;
                        tempMenuItem = orderSheetTemp.getOrders().get(choice - 1);
                        y = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number");
                        input.nextLine();
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                    	System.out.println("Order does not exist.Please try again.");
                    	input.nextLine();
                    }catch (IndexOutOfBoundsException e) {
                    	System.out.println("Order does not exist.Please try again.");
                    	input.nextLine();
                    }
                } while (y);
                if (choice < 0) {
                    continue;
                }

                do {
                    y = true;
                    try {
                        System.out.println("Input new quantity: (Enter -1 to go back)");
                        quantity = input.nextInt();
                        System.out.println(SPACING2);
                        y = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number: ");
                        input.nextLine();
                    }
                } while (y);
                if (quantity < 0) {
                    continue;
                }
                if (tempMenuItem != null) {
                    orderSheetTemp.changeQuantity(tempMenuItem, quantity, staffId);
                    orderSheetTemp.printOrder();
                    System.out.println("Order has been updated");
                    x = false;
                } else {
                    System.out.println("An error occured. No orders were updated.");
                }

            } else {
                System.out.println("This table has no orders in it");
            }

        }

    }

    /**
     * removeOrderFromOrderSheet method will remove the order
     * @param staffId the staff id
     */
    public void removeOrderFromOrderSheet(String staffId) {
        Scanner input = new Scanner(System.in);
       // Scanner inputString = new Scanner(System.in);
        boolean x = true;
        boolean y = true;
        System.out.println("Remove order:");
        while (x) {
            int tableNumber = 0;
            
            do {
                y = true;
                System.out.println("Please enter table number first: (Enter -1 to go back)");
                try {
                    tableNumber = input.nextInt();
                    System.out.println(SPACING2);
                    y = false;
                } catch (InputMismatchException e) {
                    System.out.println("Please input a number");
                    input.nextLine();
                }
            } while (y);
            if (tableNumber == -1) {
                return;
            }
            //Get table object with corresponding table number
            //Check if orderSheet for table has been initialised;

            Table tempTable = tIR.getTable(tableNumber);
            if (tIR.checkOrderSheetInit(tableNumber)) {
                OrderSheetPerTable orderSheetTemp = tempTable.getOrderSheet();

                int choice = 0;
                MenuItem tempMenuItem = null;
                do {
                    y = true;
                    orderSheetTemp.printOrder();
                    System.out.println("Select order to remove: (Enter -1 to go back)");
                    try {
                        choice = input.nextInt();
                        System.out.println(SPACING2);
                        if (choice == -1) {
                            break;
                        }
                        tempMenuItem = orderSheetTemp.getOrders().get(choice - 1);
                        y = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number");
                        input.nextLine();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Order does not exist.Please try again.");
                        input.nextLine();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Order does not exist.Please try again.");
                        input.nextLine();
                    }
                } while (y);
                if (choice < 0) {
                    continue;
                }
                if (tempMenuItem != null) {
                    orderSheetTemp.removeOrder(tempMenuItem);
                    orderSheetTemp.printOrder();
                    System.out.println("Order has been removed.");
                    x = false;
                } else {
                    System.out.println("An error occured. No orders were removed.");
                }

            } else {
                System.out.println("This table has no orders in it");
            }
        }
    }

    /**
     * PrintInvoiceAndCheckOut will print an invoice and check out the order 
     * @throws FileNotFoundException the file could not be found in the 
     * directory
     * @throws IOException input or output has been entered or received 
     * incorrectly 
     * @param staffId the staff id
     */
    public void PrintInvoiceAndCheckOut(String staffId) throws FileNotFoundException, IOException {
        Scanner input = new Scanner(System.in);
        //Scanner inputString = new Scanner(System.in);
        boolean x = true;
        boolean y = true;
        System.out.println("Print invoice and checkout.");
        while (x) {
            int tableNumber = 0;
            
            do {
                y = true;
                System.out.println("Please enter table number first: (Enter -1 to go back)");
                try {
                    tableNumber = input.nextInt();
                    System.out.println(SPACING2);
                    y = false;
                } catch (InputMismatchException e) {
                    System.out.println("Please input a number");
                    input.nextLine();
                }
            } while (y);
            if (tableNumber == -1) {
                return;
            }
            //Get table object with corresponding table number
            //Check if orderSheet for table has been initialised;

            Table tempTable = tIR.getTable(tableNumber);
            Date today = new GregorianCalendar().getTime();
            if (tIR.checkOrderSheetInit(tableNumber)) {
                OrderSheetPerTable orderSheetTemp = tempTable.getOrderSheet();
                tempTable.customerCheckout(today);
                SalesRecordsController.saveSalesRecord();
                x = false;
            } else {
                System.out.println("This table has no orders in it");
            }
        }
    }

    /**
     * viewOrder method will show the order by table
     */
    public void viewOrder() {
        Scanner input = new Scanner(System.in);
        boolean x = true;
        while (x) {
            Table tempTable = null;
            System.out.println("Please input table number: (Enter -1 to return)");
            int tableNumber;
            try {
                tableNumber = input.nextInt();
                System.out.println(SPACING2);
                if (tableNumber == -1) {
                    return;
                }
                tempTable = tIR.getTable(tableNumber);
            } catch (InputMismatchException e) {
                System.out.println("Please input a number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid table number");
            }
            tempTable.thisTableOrdered();
            x = false;
        }

    }

    /**
     * showAllOrders method will display all the orders
     */
    public void showAllOrders() {
        System.out.println("Displaying all orders:");
        ArrayList<OrderSheetPerTable> allOrdersAtTheMoment = tIR.consolidateOrderSheets();
        for (int i = 0; i < allOrdersAtTheMoment.size(); i++) {
            OrderSheetPerTable tempOrderSheet = allOrdersAtTheMoment.get(i);
            int tableNumberTemp = tempOrderSheet.getTableNum();
            System.out.println("Table number: " + tableNumberTemp);
            tempOrderSheet.printOrder();
        }
    }

    /**
     * displayMenuOptions will show all the order options
     */
    public void displayMenuOptions() {
        System.out.println(SPACING);
        System.out.println("*                ORDER OPTIONS               *");
        System.out.println("*  Please select option: (Enter -1 to return)*");
        System.out.println("*  1. Add Order                              *"); //Each table must initialise order sheet first before orders can be added in.  
        System.out.println("*  2. Update pre-exisiting order             *");
        System.out.println("*  3. Remove pre-exisiting order             *");
        System.out.println("*  4. Customer Checkout                      *");
        System.out.println("*  5. View Order                             *");
        System.out.println("*  6. View All Orders                        *");
        System.out.println(SPACING);
    }

}//End of class

