package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import dispacher.ActionDispatcher;

import model.coin.Coin;
import model.item.Item;
import exception.VendingMachineException;
import factory.CoinFactory;

/**
 * @author aviandrihidayat
 *
 */
public class VendingMachine {
	private InputCoinContainer inputContainer;
	private DrinkItemsContainer itemContainer;
	private CoinVault vault;
	private ItemOutlet outlet;
	private ReturnGate returnGate;
	private VendingMonitor vendingMonitor;
	private ActionDispatcher actionDispacher;

	public VendingMachine() {
		this.inputContainer = new InputCoinContainer(this);
		this.itemContainer = new DrinkItemsContainer(this);
		this.vault = new CoinVault(this);
		this.outlet = new ItemOutlet(this);
		this.returnGate = new ReturnGate(this);
		this.vendingMonitor = new VendingMonitor(this);
		this.actionDispacher = new ActionDispatcher(this);
	}

	public InputCoinContainer getInputContainer() {
		return inputContainer;
	}

	public void setInputContainer(InputCoinContainer inputContainer) {
		this.inputContainer = inputContainer;
	}

	public DrinkItemsContainer getItemContainer() {
		return itemContainer;
	}

	public void setItemContainer(DrinkItemsContainer itemContainer) {
		this.itemContainer = itemContainer;
	}

	public CoinVault getVault() {
		return vault;
	}

	public void setVault(CoinVault vault) {
		this.vault = vault;
	}

	public ItemOutlet getOutlet() {
		return outlet;
	}

	public void setOutlet(ItemOutlet outlet) {
		this.outlet = outlet;
	}

	public ReturnGate getReturnGate() {
		return returnGate;
	}

	public void setReturnGate(ReturnGate returnGate) {
		this.returnGate = returnGate;
	}

	/**
	 * Insert coin to vending machine
	 * 
	 * @param coinInt
	 */
	public void insertCoin(int coinInt) {
		Coin coin = null;
		try {
			coin = CoinFactory.inatiateCoin(coinInt);
		} catch (VendingMachineException e) {
			System.out.println(e.getMessage());
			this.operate();
		}
		this.vault.validateChange(coin);
		this.inputContainer.insertCoin(coin);
	}
	
	
	/**
	 * Choose item to purchase
	 * 
	 * @param intType
	 */
	public void selectItem(int intType) {
		int itemPrice = this.itemContainer.getItemPrice(intType);
		if (itemPrice > this.inputContainer.getTotalValue()) {
			System.out.println("You need to put more money to buy that item");
			this.operate();
		}
		int restOfInput = this.inputContainer.getTotalValue() - itemPrice;
		Item item = this.itemContainer.purchaseItem(intType);
		for (Coin coin : this.inputContainer.getCoinList()) {
			this.vault.addCoin(coin);
		}
		this.inputContainer.clearCoinGroup();
		this.vault.substract(itemPrice);
		List<Coin> coinList = this.vault.substract(restOfInput);
		for (Coin coin : coinList) {
			this.inputContainer.addCoin(coin);
		}
		this.outlet.addItem(item);
	}

	/**
	 * Pickup items from outlet, clear outlet from items
	 * 
	 */
	public void getItems() {
		if(this.outlet.getItemList().size() < 1){
			System.out.println("No Item in outlet found");
		}
		this.outlet.clearItemGroup();
	}

	/**
	 * Generate return coins
	 * 
	 */
	public void returnCoins() {
		int restOfInputValue = this.inputContainer.getTotalValue();
		if(restOfInputValue <= 0){
			System.out.println("No return coin");
			return;
		}
		List<Coin> coinList = this.inputContainer.substract(restOfInputValue);
		for (Coin coin : coinList) {
			this.vault.addCoin(coin);
		}
		coinList = this.vault.substract(restOfInputValue);
		for (Coin coin : coinList) {
			this.returnGate.addCoin(coin);
		}
	}

	/**
	 * Pickup return coins, clear up return gate container
	 * 
	 */
	public void pickUpReturnCoins() {
		if(this.returnGate.getCoinList().size() < 1){
			System.out.println("No return coin found");
		}
		this.returnGate.clearCoinGroup();
	}

	/**
	 * Output vending machine monitor
	 * 
	 */
	private void showMonitor() {
		this.vendingMonitor.show();
	}
	

	/**
	 * Operate vending machine
	 */
	public void operate() {
		showMonitor();
		System.out.println("Choose action : \n(1) Insert Coin, " +
				"\n(2) Choose Item to Buy, " +
				"\n(3) Pickup Items from outlet, " +
				"\n(4) Generate return coins, " +
				"\n(5) Pickup return coins");
		
		System.out.print(">");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String input = "";
		try {
			input = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int action = 0;
		try{
			action = Integer.parseInt(input);
		}catch(NumberFormatException e){
			System.out.println("Action not supported");
			operate();
		}
		this.actionDispacher.doAction(action);
	}
}
