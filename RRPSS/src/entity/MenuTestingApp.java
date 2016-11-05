package entity;
import controllers.MenuController;
import controllers.OrderController;
import controllers.ReservationController;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import controllers.TableController;

public class MenuTestingApp {
	public static void main(String[] args){
		System.out.println("Start of program");
		Scanner input = new Scanner(System.in);
		MenuController mC = new MenuController();
		ReservationController reserveC = new ReservationController();
		TableController tableC = new TableController();
		OrderController orderC = new OrderController();
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
