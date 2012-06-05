package dispacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.VendingMachine;

public class ActionDispatcher {
	private VendingMachine vendingMachine;
	
	public ActionDispatcher(VendingMachine vendingMachine){
		this.vendingMachine = vendingMachine;
	}
	
	public void doAction(int command){
		switch(command) {
			case 1 :insertCoinAction();
					break;
			case 2: purchaseAction();
					break;
			case 3:	getItemsAction();
					break;					
			case 4:	returnCoinsAction();
					break;	
			case 5:	getReturnCoins();
					break;
			default:unsupportedAction();
					break;
						
		}
	}
	
	public void unsupportedAction(){
		System.out.println("Action not supported"); 
		this.vendingMachine.operate();
	}
	public void insertCoinAction() {
		String coinString = getInput("insert coin (10, 50, 100, 500)");
		int coinInt = Integer.parseInt(coinString);
		this.vendingMachine.insertCoin(coinInt);
		this.vendingMachine.operate();
	}
	
	public void purchaseAction() {
		String coinString = getInput("Choose the item you want to purchase \n (1) Canned Coffee \n (2) Sport Drinks \n (3) Water Pet Bottle");
		int coinInt = Integer.parseInt(coinString);
		this.vendingMachine.selectItem(coinInt);
		this.vendingMachine.operate();
	}
	
	public void getItemsAction(){
		System.out.println("Pick up purchased items");
		this.vendingMachine.getItems();
		this.vendingMachine.operate();
	}
	
	public void returnCoinsAction(){
		System.out.println("Return coins");
		this.vendingMachine.returnCoins();
		this.vendingMachine.operate();
	}
	
	public void getReturnCoins(){
		System.out.println("Pick up return coins");
		this.vendingMachine.pickUpReturnCoins();
		this.vendingMachine.operate();
	}
	
	public String getInput(String message){
		System.out.println(message);
		System.out.print(">");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = reader.readLine();
		} catch (IOException e) {
			System.out.println("Error input");
			this.vendingMachine.operate();
		}
		return input;
	} 
	
	

}
