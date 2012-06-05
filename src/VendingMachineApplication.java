

import model.VendingMachine;


public class VendingMachineApplication{

	/**
	 * @param args
	 * @throws VendingMachineException 
	 * @throws IOException 
	 */
	public static void main(String[] args)  {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.operate();		
	}

}
