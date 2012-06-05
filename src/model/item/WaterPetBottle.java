package model.item;

import model.ItemAbs;

public class WaterPetBottle extends ItemAbs implements Item {
	public static final String NAME = "Water Pet Bottle";
	public static final int PRICE = 100;
	
	public WaterPetBottle() {
		super(NAME, PRICE);
	}
}
