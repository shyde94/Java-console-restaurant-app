package entity;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Customer extends Person
{
	/**
	 * Generated serial version ID for serializable classes
	 */
	private static final long serialVersionUID = 2611166586818608297L;
	
	private static List<Customer> customer;
	
	private static Scanner sc;
	
	private int customerID;

	private int contactNumber;

	private boolean isMember;

	public Customer(String name, int age, Gender gender,
			int customerID, int contactNumber, boolean isMember) {
		
		super(name, age, gender);
		
		this.customerID = customerID;
		this.contactNumber = contactNumber;
		this.isMember = isMember;
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int newCustomerID) {
		customerID = newCustomerID;
	}
	
	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int newContactNumber) {
		contactNumber = newContactNumber;
	}
	

	public boolean checkMembership() {
		return isMember;
	}
	
	public void setMembership(boolean isMember) {
		this.isMember = isMember;
	}
	
	public void displayCustomerDetails() {
		System.out.print("Name: " + getName());
		System.out.print("Age: " + getAge());
		System.out.print("Gender: " + 
				getGender().toStrValue());
		System.out.print("Customer ID: " + getCustomerID());
		System.out.print("Contact Number: " + 
				getContactNumber());
		System.out.print("Is Member: " + 
				(checkMembership() ? "Yes" : "No"));
	}
	public void displayCustomerSummary() {
		System.out.println(getName());
		System.out.println(getContactNumber());
		System.out.println((checkMembership() ? "Yes" : "No"));
	}
	
	public static Customer selectCustomer()
	{
		if(customer.isEmpty())
		{
			System.out.print("\nThere are no customers"
					+ " registered in the system!");
			
			return null;
		}

		int customerIndex = 0;
		int maxCustomerIndex = customer.size();

		do
		{
			try
			{
				int currIndex = 0;

				System.out.println();
				System.out.printf("Customer Name");
				System.out.printf("Customer Contact Number");
				System.out.printf("Member");

				// Display customer
				for(Customer customer : customer)
				{
					System.out.printf("%-5s", "(" + (++currIndex) + ")");
					customer.displayCustomerSummary();
				}

				System.out.printf("%nPlease select a customer: ");

				customerIndex = sc.nextInt();
				sc.nextLine();

				// Valid customerIndex from 1 to maxCustomerIndex
				if (customerIndex < 1 || customerIndex > maxCustomerIndex)
				{
					System.out.print("\nInvalid input! ");
					System.out.println("Failed to select customer,"
							+ " please try again..");
					continue;
				}
			}
			catch(InputMismatchException ex)
			{
				System.out.print("\nInvalid input! ");
				System.out.println("Failed to select customer,"
						+ " please try again..");

				sc.nextLine(); // Clear the garbage input
			}
			catch(Exception ex)
			{
				System.out.println("Failed to select customer,"
						+ " please try again..");

				sc.nextLine(); // Clear the garbage input
			}

		} while(customerIndex < 1 || customerIndex > maxCustomerIndex);

		return customer.get(customerIndex - 1);
	}	
	
}
