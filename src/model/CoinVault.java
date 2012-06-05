package model;

import java.util.List;

import model.coin.Coin;
import model.coin.Coin100JPY;
import model.coin.Coin10JPY;
import model.coin.Coin500JPY;
import model.coin.Coin50JPY;
import exception.VendingMachineException;

public class CoinVault extends CoinContainerAbs implements CoinContainer {
	private String[] coinTypes = new String[]{	Coin100JPY.class.getName(), 
		 	Coin10JPY.class.getName(), 
		 	Coin500JPY.class.getName(), 
		 	Coin50JPY.class.getName()};
	private VendingMachine vendingMachine;
	
	public CoinVault(VendingMachine vendingMachine){
		this.vendingMachine = vendingMachine;
		initiateCoinGroup();
		populateInitialVault();
		
	}
	
	public VendingMachine getVendingMachine() {
		return vendingMachine;
	}

	public String[] getCoinTypes() {
		return coinTypes;
	}
	
	private void populateInitialVault(){
		for(int i=0 ; i< 10 ; i++){
			addCoin(new Coin10JPY());
		}
		for(int i=0 ; i< 4 ; i++){
			addCoin(new Coin100JPY());
		}
	}
	
	/**
	 * Define weather change of a particular coin type is available 
	 * 
	 * @param coinType
	 * @return {@link Boolean}
	 */
	public boolean isChangeAvailable(String coinType){
		if(coinType.equals(Coin10JPY.class.getName())){
			List<Coin> coins = getCoinsByType(coinType);
			if(coins.size() < 9){
				return false;
			}			
		}else if(coinType.equals(Coin100JPY.class.getName())){
			List<Coin> coins = getCoinsByType(coinType);
			if(coins.size() < 4){
				return false;
			}			
		}
		return true;		
	}
	
	/**
	 * @param coin
	 * @return
	 * @throws VendingMachineException
	 * 
	 * Return true if the coin param is accepted after validated with some rules
	 */
	public boolean validateChange(Coin coin){		
		if(!coin.getClass().getName().equals(Coin10JPY.class.getName())){
			List<Coin> coins = this.getCoinGroup().get(Coin10JPY.class.getName());
			if(coins.size() < 1){
				System.out.println("Machine cannot accept "+coin.getValue()+
						"JPY because we don't have channge for it. Please use 10JPY coin money");
				this.getVendingMachine().operate();
			}
		}
		
		return false;
	}
}
