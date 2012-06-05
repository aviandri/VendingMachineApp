package model.coin;

public class CoinAbs implements Coin {
	private int value;
	
	public CoinAbs(int value){
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
