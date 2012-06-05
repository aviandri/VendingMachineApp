package model;

import java.util.List;

import model.coin.Coin;
import model.coin.Coin100JPY;
import model.coin.Coin10JPY;
import model.coin.Coin50JPY;
import model.item.Item;


public class VendingMonitor {
	private VendingMachine vendingMachine;
	
	public VendingMonitor(VendingMachine vendingMachine){
		this.vendingMachine = vendingMachine;
	}
	
	public void show(){
		StringBuffer output = new StringBuffer();
		output.append("[Input Amount]\t");
		output.append(vendingMachine.getInputContainer().getTotalValue()+"JPY\n");
		
		output.append("[Change]\t");
		output.append("100JPY\t");
		if(this.vendingMachine.getVault().isChangeAvailable(Coin100JPY.class.getName())){
			output.append("Change");
		}else{
			output.append("No Change");
		}
		
		output.append("\n");
		output.append("\t\t");
		output.append("50JPY\t");
		if(this.vendingMachine.getVault().isChangeAvailable(Coin50JPY.class.getName())){
			output.append("Change");
		}else{
			output.append("No Change");
		}
		
		output.append("\n");
		output.append("\t\t");
		output.append("10JPY\t");
		if(this.vendingMachine.getVault().isChangeAvailable(Coin10JPY.class.getName())){
			output.append("Change");
		}else{
			output.append("No Change");
		}
		
		output.append("\n");
		output.append("[Return gate]");
		output.append("\t");
		if(this.vendingMachine.getReturnGate().getCoinList().size() < 1){
			output.append("Empty");
			output.append("\n");
		}else{
			for (Coin coin : this.vendingMachine.getReturnGate().getCoinList()) {
				output.append("\t\t");
				output.append(coin.getValue()+"JPY");
				output.append("\n");
			}
		}
		
		output.append("[Items for sale]");
		output.append("\n");
		
		for(int i= 0 ; i < this.vendingMachine.getItemContainer().getItemTypes().length  ; i++ ){
			Item item = null;
			String itemType = this.vendingMachine.getItemContainer().getItemTypes()[i];
			try {
				item = (Item)Class.forName(itemType).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			output.append("\t\t");
			output.append((i+1)+"."+item.getName());
			output.append("\t");
			output.append(item.getPrice() + "JPY");
			
			List<Item> itemList = this.vendingMachine.getItemContainer().getItemsByType(itemType);
			if(itemList.size() < 1){
				output.append("\t");
				output.append("Out of stock");
			}
			output.append("\n");
			
		}
		
		output.append("[Outlet]");
		output.append("\t");
		if(this.vendingMachine.getOutlet().getItemList().size() < 1 ){
			output.append("Empty");
		}else{
			for (Item outlet: this.vendingMachine.getOutlet().getItemList()) {
				output.append(outlet.getName());
				output.append("\n");
				output.append("\t\t");
			}

		}
		
		System.out.println(output);
	}

}
