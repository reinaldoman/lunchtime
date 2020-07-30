package co.com.s4n.deliveries.model;

import java.io.IOException;
import java.util.ArrayList;

import co.com.s4n.deliveries.exception.NotEnoughRoomInTransportException;
import co.com.s4n.deliveries.util.Constants;
import co.com.s4n.deliveries.util.PropertiesUtil;

public class Drone {

	private String id;
	
	private Position currentPosition = new Position("ORIGIN", Direction.NORTH, 0, 0);
	
	private boolean available;
	
	private int room;
	
	private ArrayList<Lunch> lunches;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
		for(Position position : this.getDestinationDeliveryCoordinates()) {
			goToDestination(position);
		}
	}
	
	private void goToDestination(Position destination) {
		System.out.println("delivering lunch at: (" + destination.getX() + "," + destination.getY() + ")");
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
	
	
	public void setShipmentsCargo(ArrayList<Lunch> lunches) throws NotEnoughRoomInTransportException {
		if(lunches.size() > room) {
			throw new NotEnoughRoomInTransportException();
		}
		this.lunches = lunches;
	}
}
