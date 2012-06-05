package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.coin.Coin;
import model.coin.Coin100JPY;
import model.coin.Coin10JPY;
import model.coin.Coin50JPY;

/**
 * 
 * @author aviandrihidayat
 *
 */
/**
 * @author aviandrihidayat
 *
 */
public abstract class CoinContainerAbs implements CoinContainer{
	private Map<String, List<Coin>> coinGroup;
	
	
	public abstract String[] getCoinTypes();
	
	public abstract VendingMachine getVendingMachine();
	
	/**
	 * intatiate coin group that is grouped by coin type
	 */
	public void initiateCoinGroup(){
		this.coinGroup = new HashMap<String, List<Coin>>();
		String[] coinTypes = getCoinTypes();
		for(int i=0 ; i < coinTypes.length ; i++){
			List<Coin> coinList = new LinkedList<Coin>();
			this.coinGroup.put(coinTypes[i], coinList);
		}
	}
	
	public Map<String, List<Coin>> getCoinGroup() {
		return coinGroup;
	}

	public void setCoinGroup(Map<String, List<Coin>> coinGroup) {
		this.coinGroup = coinGroup;
	}

	/**
	 * Return a list of coins with the param type
	 * 
	 *  @param String
	 *  @return list of coins
	 */
	public List<Coin> getCoinsByType(String cointType){
		List<String> coinTypeList = new ArrayList<String>(Arrays.asList(getCoinTypes()));
		if(!coinTypeList.contains(cointType)){
			System.out.println("This type of coin is not supported");
			getVendingMachine().operate();
		}
		return this.getCoinGroup().get(cointType);
	}
	
	/** 
	 * Return the total value of coin container
	 * 
	 * @return the total value of coin container
	 * 
	 */
	public int getTotalValue() {
		int totalValue = 0;
		for (List<Coin> coins : coinGroup.values()) {
			for (Coin coin : coins) {
				totalValue = totalValue + coin.getValue();
			}
		}
		return totalValue;
	}
	
	/**
	 * List of all coins contained in the coin container
	 * 
	 * @return list of all coins contained in the coin container
	 */
	public List<Coin> getCoinList(){
		List<Coin> coinList = new ArrayList<Coin>();
		for (List<Coin> coins : getCoinGroup().values()) {
			for (Coin coin : coins) {
				coinList.add(coin);
			}
		};
		return coinList;
	}
	
	/**
	 * 
	 * 
	 * @param value an integer value that will substract the coin container 
	 * @return return list of coins used to substract 
	 */
	public List<Coin> substract(int value){
		int restOfValue = value;
		List<Coin> consumedCoin = new LinkedList<Coin>();
		while(restOfValue != 0){
			LinkedList<Coin> coin100List = (LinkedList<Coin>) this.coinGroup.get(Coin100JPY.class.getName());
			LinkedList<Coin> coin50List = (LinkedList<Coin>) this.coinGroup.get(Coin50JPY.class.getName());
			LinkedList<Coin> coin10List = (LinkedList<Coin>) this.coinGroup.get(Coin10JPY.class.getName());
			
			if(coin100List.size() > 0 && restOfValue / 100 >= 1 ){
				Coin coin = coin100List.pop();
				consumedCoin.add(coin);
				restOfValue = restOfValue - coin.getValue();
				continue;
			}else if(coin50List.size() > 0 && restOfValue / 50 >= 1){
				Coin coin = coin50List.pop();
				consumedCoin.add(coin);
				restOfValue = restOfValue - coin.getValue();
				continue;
			}else if(coin10List.size() > 0 && restOfValue / 10 >= 1){
				Coin coin = coin10List.pop();
				consumedCoin.add(coin);
				restOfValue = restOfValue - coin.getValue();
				continue;
			}
		}
		return consumedCoin;
	}
	
	/**
	 * 
	 * @param coin object that will be added to coin container
	 */
	public void addCoin(Coin coin){
		List<Coin> coinList = this.coinGroup.get(coin.getClass().getName());
		coinList.add(coin);
	}
	
	/**
	 * Clear the content of coin container
	 * 
	 */
	public void clearCoinGroup(){
		for (List<Coin> coins: this.coinGroup.values()) {
			coins.clear();
		}
	}
	
	
	
}
