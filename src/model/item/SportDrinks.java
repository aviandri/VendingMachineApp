package model.item;

import model.ItemAbs;

public class SportDrinks extends ItemAbs implements Item {
	public static final String NAME = "Sport Drinks";
	public static final int PRICE = 150;
	
	public SportDrinks() {
		super(NAME, PRICE);
	}
}
