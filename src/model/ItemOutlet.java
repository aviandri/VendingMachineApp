package model;

import model.item.CannedCoffee;
import model.item.SportDrinks;
import model.item.WaterPetBottle;

public class ItemOutlet extends ItemContainerAbs implements ItemContainer {
	private final String[] itemTypes = new String[] {
			CannedCoffee.class.getName(), SportDrinks.class.getName(),
			WaterPetBottle.class.getName() };
	private VendingMachine vendingMachine;

	public ItemOutlet(VendingMachine vendingMachine) {
		initiateItemGroup();
	}

	public VendingMachine getVendingMachine() {
		return this.vendingMachine;
	}

	public String[] getItemTypes() {
		return itemTypes;
	}

}
