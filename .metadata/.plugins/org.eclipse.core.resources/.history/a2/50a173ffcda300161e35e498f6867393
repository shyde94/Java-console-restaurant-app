package entity;
import controllers.MenuController;
import controllers.OrderController;
import controllers.ReservationController;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import controllers.TableController;

import java.io.EOFException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MenuTestingApp {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("Start of program");
		Scanner input = new Scanner(System.in);
		MenuController mC = new MenuController();
		ReservationController reserveC = new ReservationController();
		TableController tableC = new TableController();
		OrderController orderC = new OrderController();
                
                  //Testing File IO
        File file = new File("Menu.txt");
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Banana", 1.00, "Yellow", "Breakfast"));
        menuItems.add(new MenuItem("Apple", 0.40, "Red", "Breakfast"));
        menuItems.add(new MenuItem("Meat Pie", 0.40, "Served warm with a cup of coffee", "Lunch"));

        //Serialize collection of menu items
        //Takes File
        FileOutputStream fo = new FileOutputStream(file);
        //Make Object and takes FileOutputStream
        ObjectOutputStream output = new ObjectOutputStream(fo);
        //Write object into file
        for (int i = 0; i < menuItems.size(); i++) {
            output.writeObject(menuItems.get(i));
        }

        output.close();
        fo.close();

        //Import back into collection
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream input1 = new ObjectInputStream(fi);
        ArrayList<MenuItem> menuItems2 = new ArrayList<MenuItem>();

        try {
            while (true) {
                MenuItem m = (MenuItem) input1.readObject();
                menuItems2.add(m);
            }
        } catch (EOFException ex) {
            //Printing the menu items using printItem()
            for (int i = 0; i < menuItems2.size(); i++) {
                menuItems2.get(i).printItem();
            }
            //Printing the menu and seeing the serializable objects
            System.out.println("Serializable Objects");
            for (int i = 0; i < menuItems2.size(); i++) {
                System.out.println(menuItems2);
            }
        }
		boolean x = true;
		while(x){
			//displayMenu();
			
			try{tableC.run();}
			catch(InputMismatchException e){
				System.out.println("Error caught by MenuTestingApp2");
			}
			try{
				
				mC.run();
			}
			catch(InputMismatchException e){
				System.out.println("Error caught by MenuTestingApp2");
			}
			try{
				orderC.run();
				
			}
			catch(InputMismatchException e){
				System.out.println("Error caught by MenuTestingApp");
				input.next();
			}catch(IndexOutOfBoundsException e){
				System.out.println("Invalid input. Try again");
				input.nextLine();
			}
			try{
				reserveC.run();
				
			}
			catch(InputMismatchException e){
				System.out.println("Error caught by MenuTestingApp");
				input.next();
				
			}
		}
	}		
		
	
	
	public static void displayMenu(){
		System.out.println("Bae restaurant Menu. Please select your action");
		System.out.println("1. Order");
		System.out.println("2. Menu");
		System.out.println("3. Promotional Packages");
		System.out.println("4. Reservations");
		System.out.println("5. Check table ability");
		System.out.println("6. Print sale revenue report by period.");
		
		
	}
}
