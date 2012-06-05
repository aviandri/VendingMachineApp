package model;

import java.util.List;

import model.coin.Coin;
import model.coin.Coin100JPY;
import model.coin.Coin10JPY;
import model.coin.Coin500JPY;
import model.coin.Coin50JPY;
import exception.VendingMachineException;

/**
 * @author aviandrihidayat
 *
 */
public class InputCoinContainer extends CoinContainerAbs implements CoinContainer{
	
	private String[] coinTypes = new String[]{	Coin100JPY.class.getName(), 
											 	Coin10JPY.class.getName(), 
											 	Coin500JPY.class.getName(), 
											 	Coin50JPY.class.getName()};
	
	private VendingMachine vendingMachine;
	
	
	public InputCoinContainer(VendingMachine vendingMachine){
		this.vendingMachine = vendingMachine;
		initiateCoinGroup();
	}
	
	public String[] getCoinTypes() {
		return coinTypes;
	}
	
	public VendingMachine getVendingMachine() {
		return this.vendingMachine;
	}

	/**
	 * Input coin to Input Coin container
	 * 
	 * @param coinInt
	 * @throws VendingMachineException
	 */
	public void insertCoin(Coin coin){
		List<Coin> coinList = this.getCoinGroup().get(coin.getClass().getName());
		coinList.add(coin);
	}
	

}
