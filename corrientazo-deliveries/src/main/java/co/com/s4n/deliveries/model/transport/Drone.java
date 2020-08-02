package co.com.s4n.deliveries.model.transport;

import java.io.IOException;
import java.util.ArrayList;

import co.com.s4n.deliveries.common.util.properties.Constants;
import co.com.s4n.deliveries.common.util.properties.PropertiesUtil;
import co.com.s4n.deliveries.exception.NotEnoughRoomInTransportException;
import co.com.s4n.deliveries.model.content.DeliveryContent;
import co.com.s4n.deliveries.model.location.Position;

public class Drone extends Vehicle implements Deliverable{


	private ArrayList<Position> destinationDeliveryCoordinates;
	
	protected int deliveryCapacity;
	protected ArrayList<DeliveryContent> contents;

	public Drone() {
		try {
			deliveryCapacity = Integer.parseInt(PropertiesUtil.getPropertyValue(Constants.DELIVERY_CAPACITY));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Position> getDestinationDeliveryCoordinates() {
		return destinationDeliveryCoordinates;
	}

	public void setDestinationDeliveryCoordinates(ArrayList<Position> destinationDeliveryCoordinates) {
		this.destinationDeliveryCoordinates = destinationDeliveryCoordinates;
	}

	public void setShipmentsCargo(ArrayList<DeliveryContent> lunches) throws NotEnoughRoomInTransportException {
		if(lunches.size() > this.deliveryCapacity) {
			throw new NotEnoughRoomInTransportException();
		}
		this.contents = lunches;
	}

	@Override
	public void deliverAddresses() {
		System.out.println("Drone " + id + " about to deliver addresses");
		for(Position position : this.getDestinationDeliveryCoordinates()) {
			trace(true);
			System.out.println("---> (" + currentPosition.getX() + "," + currentPosition.getY() + ") ---> (" + position.getX() + "," + position.getY() + ")");
			moveToPosition(position);
			if(currentPosition.getDirection() != position.getDirection()) {
				System.out.println("Rotating  " + position.getDirection());
				currentPosition.setDirection(position.getDirection()); //Ensure at the end position direction be as desired
			}
			trace(false);
		}
		System.out.println("Final position: (" + currentPosition.getX() + "," + currentPosition.getY() + ") " + currentPosition.getDirection());
		
	}

}
