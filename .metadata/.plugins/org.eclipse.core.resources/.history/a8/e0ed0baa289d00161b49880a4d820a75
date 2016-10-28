package controllers;
import entity.ReservationAll;
import entity.TableAll;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.InputMismatchException;

import entity.Reservation;

public class ReservationController{
	
	private ReservationAll allTheReservations;
	private TableAll allTheTables;
	
	
	public ReservationController(){
		allTheReservations = new ReservationAll();
		allTheTables = new TableAll();
	}
	
	public void run() throws InputMismatchException{ 
		boolean x = true; 
		Scanner input = new Scanner(System.in);
		while(x){
			boolean y = true;
			this.displayMenuOptions();
			int choice = input.nextInt();
			switch(choice){
				case(1):
					do{
						try{
							this.createReservation();
							y = false;
						}
						catch(InputMismatchException e){
							System.out.println("Invalid option");
						}
					}while(y);
					break;
				case(2): 
					do{
						try{
							this.checkReservation();
							y = false;
						}
						catch(InputMismatchException e){
							System.out.println("Invalid option");
						}
					}while(y);
					break;
					
				case(3): 
					do{
						try{
							this.removeReservation();
							y = false;
						}
						catch(InputMismatchException e){
							System.out.println("Invalid Input. 3");
						}
						catch(ArrayIndexOutOfBoundsException e){
							System.out.println("There is no such option!");
						}
					}while(y);
					break;
				case(-1):return;
				default: System.out.println("Please select a valid option");
			}
		}
	}
	
	private void removeReservation() {
		// TODO Auto-generated method stub
		
	}

	private void checkReservation() {
		// TODO Auto-generated method stub
		
	}

	private void createReservation() {
		// TODO Auto-generated method stub
		
	}

	public void displayMenuOptions(){		//Display main menu options
		System.out.println("Menu Options (Enter -1 to go back)");
		System.out.println("1. Create new reservation");
		System.out.println("2. Check reservation");
		System.out.println("3. Remove reservation");
		
	}
	
}