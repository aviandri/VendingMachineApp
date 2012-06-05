package exception;

public class VendingMachineException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public VendingMachineException(String message){
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
	
	

}
