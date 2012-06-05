package model.item;

import model.ItemAbs;

public class CannedCoffee extends ItemAbs implements Item {
	public static final String NAME = "Canned Coffee";
	public static final int PRICE = 120;
	
	public CannedCoffee() {
		super(NAME, PRICE);
	}
	
	
}
