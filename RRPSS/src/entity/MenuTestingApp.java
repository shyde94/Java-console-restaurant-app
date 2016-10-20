package entity;
import controllers.MenuController;

public class MenuTestingApp {
	public static void main(String[] args){
		/*Menu menu = new Menu();
		menu.addItem("potato", 5.00, "Chips","desserts");
		MenuItem temp = menu.getDessertsM().get(0);
		temp.printItem();
		menu.addItem("chicken rice", 4.00, "Hainanese chicken rice there you go", "mains");
		menu.getMainsM().get(0).printItem();
		menu.addItem("Fish and chips", 5.0, "chips with fish", "mains");
		menu.getMainsM().get(1).printItem();*/
		
		MenuController mC = new MenuController();
		mC.CreateMenuItem();
		mC.printMenuL();
	}
}
