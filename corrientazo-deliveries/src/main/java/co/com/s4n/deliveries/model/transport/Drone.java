package co.com.s4n.deliveries.model.transport;

import java.io.IOException;
import java.util.ArrayList;

import co.com.s4n.deliveries.common.util.properties.Constants;
import co.com.s4n.deliveries.common.util.properties.PropertiesUtil;
import co.com.s4n.deliveries.exception.NotEnoughRoomInTransportException;
import co.com.s4n.deliveries.model.content.DeliveryContent;
import co.com.s4n.deliveries.model.location.Position;

public class Drone extends Vehicle{

	
	private ArrayList<Position> destinationDeliveryCoordinates;
	
	public Drone() {
		try {
			room = Integer.parseInt(PropertiesUtil.getPropertyValue(Constants.MAX_ROOM_FOR_DRONE));
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position position) {
		this.currentPosition = position;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void deliver() {
		//TODO some real log here
		System.out.println("delivering drone " + id);
		for(Position position : this.getDestinationDeliveryCoordinates()) {
			goToDestination(position);
		}
	}
	
	private void goToDestination(Position destination) {
		System.out.println("moving drone at: (" + destination.getX() + "," + destination.getY() + ")");
		int xAxisDistanceInBlocks = currentPosition.getX() - destination.getX();
		int yAxisDistanceInBlocks = currentPosition.getY() - destination.getY();
		currentPosition = destination;
//		if(currentPosition.getX() > destination.getX()) {
//			//Left movement --> west way
//		} else if(currentPosition.getX() < destination.getX()) {
//			//right movement --> east way
//		} else {
//			//no movement -- stay quiet
//		}
//		
//		if(currentPosition.getY() > destination.getY()) {
//			//move down --> south
//		} else if(currentPosition.getY() < destination.getY()) {
//			//move up --> North
//		} else {
//			//no movement -- stay quiet
//		}
		
	}
	
	
	public void setShipmentsCargo(ArrayList<DeliveryContent> lunches) throws NotEnoughRoomInTransportException {
		if(lunches.size() > room) {
			throw new NotEnoughRoomInTransportException();
		}
		this.contents = lunches;
	}
}
