package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.item.CannedCoffee;
import model.item.Item;
import model.item.SportDrinks;
import model.item.WaterPetBottle;

public class DrinkItemsContainer extends ItemContainerAbs implements ItemContainer{
	//Available item types, edit this array to edit the supported item type
	private final String[] itemTypes = new String[]{CannedCoffee.class.getName(), 
													SportDrinks.class.getName(), 
													WaterPetBottle.class.getName()};
	private final Map<Integer, String> itemTypesMap;
	private VendingMachine vendingMachine;
	
	//Initiate ItemContainer attributes
	public DrinkItemsContainer(VendingMachine vendingMachine){
		super();
		this.vendingMachine = vendingMachine;
		initiateItemGroup();
		itemTypesMap = new HashMap<Integer, String>();
		for(int i=0 ; i < itemTypes.length ; i++){
			itemTypesMap.put(i+1, itemTypes[i]);
		}
		populateInitialItems();		
	}
	
	
	
	public VendingMachine getVendingMachine() {
		return vendingMachine;
	}


	@Override
	public String[] getItemTypes() {
		return itemTypes;
	}

	//Initiate initial items contains in the ItemContainer
	private void populateInitialItems(){
		//Adding 2 Canned coffee in the container
		for(int i=0 ; i < 2 ; i++){
			CannedCoffee coffee = new CannedCoffee();
			
			List<Item> items = this.getItemGroup().get(coffee.getClass().getName());
			items.add(coffee);
		}
		//Adding 4 Sport drinks in the container
		for(int i=0 ; i < 4 ; i++){
			SportDrinks sportDrinks = new SportDrinks();
			List<Item> items = this.getItemGroup().get(sportDrinks.getClass().getName());
			items.add(sportDrinks);
		}
	}
	
	
	private Item popItemByType(String itemType){
		List<String> itemTypeList = new ArrayList<String>(Arrays.asList(itemTypes));
		if(!itemTypeList.contains(itemType)){
			System.out.println("Item is not supported");
			this.getVendingMachine().operate();
		}
		LinkedList<Item> itemList = (LinkedList<Item>)this.getItemGroup().get(itemType);
		if(itemList.size() < 1){
			System.out.println("Item requested is not available");
			this.getVendingMachine().operate();
		}
		return ((LinkedList<Item>)this.getItemGroup().get(itemType)).pop();
	}
	
	/**
	 * Remove purchased item from item container
	 * 
	 * @param itemType
	 * @return {@link Item}
	 */
	public Item purchaseItem(String itemType){
		return popItemByType(itemType);
	}
	
	/**
	 * Get item price of an item type
	 * 
	 * @param itemType
	 * @return 
	 */
	public int getItemPrice(String itemType){
		List<String> itemTypeList = new ArrayList<String>(Arrays.asList(itemTypes));
		if(!itemTypeList.contains(itemType)){
			System.out.println("Item is not supported");
			this.getVendingMachine().operate();			
		}
		Item item = null;
		try {
			item = (Item) Class.forName(itemType).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item.getPrice();
	}
	
	public int getItemPrice(int intType){
		return getItemPrice(this.itemTypesMap.get(intType));
	}
	
	public Item purchaseItem(int intType){
		return purchaseItem(this.itemTypesMap.get(intType));
	}
	
}
