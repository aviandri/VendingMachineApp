package model;

import model.item.Item;

public abstract class ItemAbs implements Item {

	private String name;
	private int price;
	
	public ItemAbs(String name, int price){
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return this.name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
