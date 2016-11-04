package entity;

import java.util.ArrayList;

public class Menu {
	private ArrayList<MenuItem> menuItem;
	private ArrayList<String> types;

	// requirement to separate menus into different categories

	// Constructor:
	public Menu() {
		menuItem = new ArrayList<MenuItem>();
		types = new ArrayList <String>();
	}

	public ArrayList<String> getTypes() {
		return this.types;
	}
	
	public void addTypes (String name){
		types.add(name);
		
	}
	
	public void printTypes(){
		System.out.println("Types are: ");
		for (int i=0; i<types.size(); i++){
			System.out.println((i+1)+"."+ types.get(i));
		}
	}
	
	
	
	
	public void addItem(String name, double price, String desc, String type) {
		MenuItem item = new MenuItem(name, price, desc, type);
		System.out.println("Item has been added");
		
	}

	public void updateItem(String name, double price, String desc, String type, int n) {
		MenuItem temp = null;
		temp = menuItem.get(n);
		System.out.println("Item has been updated");
	}

	public void removeItem(int n, String type) {
		menuItem.remove(n);
		System.out.println("Item has been removed");
	}

	public void printmenuItem() {
		int x;
		System.out.println("Main Courses:");
		for (MenuItem s : menuItem) {
			x = menuItem.indexOf(s);
			x++;
			System.out.print(x + ". ");
			s.printItem();
		}
	}

}
