package co.com.s4n.deliveries.model;

import java.util.ArrayList;

import co.com.s4n.deliveries.exception.NotEnoughRoomInTransportException;

public class Drone {

	private String id;
	
	private Position currentPosition = new Position("ORIGIN", Direction.NORTH, 0, 0);
	
	private boolean available;
	
	private int room = 3; //TODO: load this value from properties
	
	private ArrayList<Lunch> lunches = null;
	
	private ArrayList<Position> destinationDeliveryCoordinates;
	
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

	public void deliverShipments() {
		//TODO: rotate and translate
		for(Position position : this.getDestinationDeliveryCoordinates()) {
			sendDeliveryToLocation(position);
		}
	}
	
	private void sendDeliveryToLocation(Position destination) {
		int xAxisDistanceInBlocks = Math.abs(currentPosition.getX() - destination.getX());//get the absolute value of the distance in blocks
		int yAxisDistanceInBlocks = Math.abs(currentPosition.getY() - destination.getY());
		if(currentPosition.getX() > destination.getX()) {
			//Left movement --> west way
		} else if(currentPosition.getX() < destination.getX()) {
			//right movement --> east way
		} else {
			//no movement -- stay quiet
		}
		
		if(currentPosition.getY() > destination.getY()) {
			//move down --> south
		} else if(currentPosition.getY() < destination.getY()) {
			//move up --> North
		} else {
			//no movement -- stay quiet
		}
		
		
		move(xAxisDistanceInBlocks, yAxisDistanceInBlocks);
	}
	
	private void move(int xAxisBlocks, int yAxisBlocks) {
		
	}
	
	public void setShipmentsCargo(ArrayList<Lunch> lunches) throws NotEnoughRoomInTransportException {
		if(lunches.size() > room) {
			throw new NotEnoughRoomInTransportException();
		}
		this.lunches = lunches;
	}
}
