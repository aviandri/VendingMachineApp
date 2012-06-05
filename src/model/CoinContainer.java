package model;

import java.util.List;

import model.coin.Coin;
import exception.VendingMachineException;

public interface CoinContainer {

	public String[] getCoinTypes();

	public int getTotalValue();

	public List<Coin> getCoinsByType(String cointType)
			throws VendingMachineException;

}
