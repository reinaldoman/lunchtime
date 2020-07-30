package co.com.s4n.deliveries.exception;

public class NotEnoughRoomInTransportException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughRoomInTransportException() {
        super("Cargo room exceded");
    }
}
