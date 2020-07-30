package co.com.s4n.deliveries.util.loader;

import java.util.ArrayList;

import co.com.s4n.deliveries.model.Position;

public class RoutesDescriptor {
	
	private String transportId;
	
	private ArrayList<Position> destinationDeliveryCoordinates = new ArrayList<Position>();

	public String getTransportId() {
		return transportId;
	}

	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}

	public ArrayList<Position> getDestinationDeliveryCoordinates() {
		return destinationDeliveryCoordinates;
	}

	public void setDestinationDeliveryCoordinates(ArrayList<Position> destinationPositions) {
		this.destinationDeliveryCoordinates = destinationPositions;
	}

}
