package model;

import model.coin.Coin100JPY;
import model.coin.Coin10JPY;
import model.coin.Coin500JPY;
import model.coin.Coin50JPY;

public class ReturnGate extends CoinContainerAbs implements CoinContainer {
	private String[] coinTypes = new String[] { Coin100JPY.class.getName(),
			Coin10JPY.class.getName(), Coin500JPY.class.getName(),
			Coin50JPY.class.getName() };
	private VendingMachine vendingMachine;

	public ReturnGate(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
		initiateCoinGroup();
	}

	public VendingMachine getVendingMachine() {
		return this.vendingMachine;
	}

	public String[] getCoinTypes() {
		return coinTypes;
	}

}
