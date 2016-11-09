package entity;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class StaffAll {
	private ArrayList<Staff> allTheStaff;
	
	public StaffAll(){
		allTheStaff = new ArrayList<Staff>();
		readFile();
	}
	
	public void readFile(){
		Scanner x;
		try{
			System.out.println("Reading in staff details");
			x = new Scanner(new File("staff.txt"));
			while(x.hasNext()){
				
				String name = x.next();
				String gender = x.next();
				String employeeId = x.next();
				String jobTitle = x.next();
				
				Staff tempStaff = new Staff(name,gender,employeeId,jobTitle);
				allTheStaff.add(tempStaff);
			}
		}
		catch(Exception e){
			System.out.println("End of file");
		}
	}
	
	public boolean checkId(String id){
		for(int i=0;i<allTheStaff.size();i++){
			if(id.equals(allTheStaff.get(i).getEmployeeID())){
				return true;
			}
		}
		return false;
	}
	
	public void printStaffDetails(){
		for(int i=0;i<allTheStaff.size();i++){
			allTheStaff.get(i).printStaffDetails();
		}
	}
	
	
}
