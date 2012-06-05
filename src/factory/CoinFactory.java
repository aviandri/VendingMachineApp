package factory;

import exception.VendingMachineException;
import model.coin.Coin;
import model.coin.Coin100JPY;
import model.coin.Coin10JPY;
import model.coin.Coin500JPY;
import model.coin.Coin50JPY;

/**
 * @author aviandrihidayat
 *
 */
public class CoinFactory {
	
	/**
	 * Intatiate Coin class based on integer value
	 * 
	 * @param coinInt
	 * @return
	 * @throws VendingMachineException
	 */
	public static Coin inatiateCoin(int coinInt) throws VendingMachineException{
		Coin coin = null;
		switch(coinInt){
			case 10 :	coin = new Coin10JPY();
						break;
			case 50 :	coin = new Coin50JPY();
						break;
			case 100:	coin = new Coin100JPY();
						break;				
			case 500:	coin = new Coin500JPY();
						break;
			default :	throw new VendingMachineException("Money type not supported");
		}
		return coin;
	}
}
