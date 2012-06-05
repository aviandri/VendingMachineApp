package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.item.Item;

public abstract class ItemContainerAbs implements ItemContainer {
	
	private Map<String, List<Item>> itemGroup;
	
	public abstract String[] getItemTypes();
	
	public abstract VendingMachine getVendingMachine();
	
	//initiate Item group
	public void initiateItemGroup(){
		this.itemGroup = new HashMap<String, List<Item>>(); 
		for(int i=0 ; i < getItemTypes().length ; i++){
			List<Item> itemList = new LinkedList<Item>();
			this.itemGroup.put(getItemTypes()[i],  itemList);
		}
	}
	
	
	/**
	 * Get Item list of a particular type
	 * 
	 * @param itemType
	 * @return
	 */
	public List<Item> getItemsByType(String itemType){
		List<String> itemTypeList = new ArrayList<String>(Arrays.asList(getItemTypes()));
		if(!itemTypeList.contains(itemType)){
			System.out.println("Item is not supported");
			getVendingMachine().operate();
		}
		return this.itemGroup.get(itemType);
	}
	
	
	/**
	 * Get all items contained in Item container
	 * 
	 * @return list of {@link Item}
	 */
	public List<Item> getItemList(){
		List<Item> itemList = new ArrayList<Item>();
		for (List<Item> items : this.itemGroup.values()) {
			for (Item item: items) {
				itemList.add(item);
			}
		};
		return itemList;
	}
	
	/**
	 * Add item to Item container
	 * 
	 * @param item
	 */
	public void addItem(Item item){
		List<Item> itemList = this.itemGroup.get(item.getClass().getName());
		itemList.add(item);
	}
	
	/**
	 * Clear items in item container
	 */
	public void clearItemGroup(){
		for (List<Item> items: this.itemGroup.values()) {
			items.clear();
		}
	}
	
	public Map<String, List<Item>> getItemGroup() {
		return itemGroup;
	}

	public void setItemGroup(Map<String, List<Item>> itemGroup) {
		this.itemGroup = itemGroup;
	}
	
	

	
	
}
