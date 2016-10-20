package entity;
import java.util.ArrayList;

public class Menu {
	private ArrayList<MenuItem> mains;
	private ArrayList<MenuItem> drinks;
	private ArrayList<MenuItem> desserts; 
	
	//requirement to separate menus into different categories 
	
	//Constructor:
	public Menu(){
		mains = new ArrayList<MenuItem>();
		drinks = new ArrayList<MenuItem>();
		desserts = new ArrayList<MenuItem>();
		
	}
	
	public ArrayList<MenuItem> getMainsM(){
		return this.mains;
	}
	public ArrayList<MenuItem> getDrinksM(){
		return this.drinks;
	}
	public ArrayList<MenuItem> getDessertsM(){
		return this.desserts;
	}
	
	
	public void addItem(String name, double price, String desc, String type){
		MenuItem item = new MenuItem(name,price,desc,type);
		switch(type){
		case "mains": mains.add(item); System.out.println("Item successfully added");break;
		case "drinks": drinks.add(item);System.out.println("Item successfully added"); break;
		case "desserts": desserts.add(item);System.out.println("Item successfully added"); break;
		default: System.out.println("Nothing added");
		}
	}
	
	public void updateItem(String name, double price, String desc, String type, int n){
		MenuItem temp = null;
		switch(type){
		case "mains": 
			temp = mains.get(n);break;
		case "drinks":
			temp = drinks.get(n);break;
		case "desserts":
			temp = desserts.get(n);break;
		default: System.out.println("Invalid option, no items updated");
		}
		//Use try-catch block here?? KIV
		temp.setName(name);
		temp.setPrice(price);
		temp.setDesc(desc);
		temp.setType(type);
		
	}
	
	public void removeItem(int n, String type){
		MenuItem temp = null;
		switch(type){
		case "mains": 
			mains.remove(n);break;
		case "drinks":
			drinks.remove(n);break;
		case "desserts":
			desserts.remove(n);break;
		default: System.out.println("Invalid option, no items removed");
		}
	}
	
}
