package co.com.s4n.deliveries.model.transport;

import java.io.IOException;
import java.util.ArrayList;

import co.com.s4n.deliveries.common.util.properties.Constants;
import co.com.s4n.deliveries.common.util.properties.PropertiesUtil;
import co.com.s4n.deliveries.exception.NotEnoughRoomInTransportException;
import co.com.s4n.deliveries.model.content.DeliveryContent;
import co.com.s4n.deliveries.model.location.Direction;
import co.com.s4n.deliveries.model.location.Position;
import co.com.s4n.deliveries.model.movement.MovementType;

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
			System.out.println("Next delivery from (" + currentPosition.getX() + "," + currentPosition.getY() + ") to (" + position.getX() + "," + position.getY() + ")");
			goToDestination(position);
		}
	}

	private void goToDestination(Position destination) {
		move(destination);
//		currentPosition.setDirection(destination.getDirection());
	}

	/*
	 * Moves current drone to a given position
	 */
	private void move(Position destination) {
		translateToCoordinates(destination);
	}
	
	private void translateToCoordinates(Position destination) {
		
		int xAxisDistanceInBlocks = currentPosition.getX() - destination.getX();
		int yAxisDistanceInBlocks = currentPosition.getY() - destination.getY();
		
		//X axis movement
		if(xAxisDistanceInBlocks != 0) {
			if(xAxisDistanceInBlocks < 0) {
				currentPosition.setDirection(Direction.EAST);
			}
			else if(xAxisDistanceInBlocks > 0) {
				currentPosition.setDirection(Direction.WEST);
			}
			int nextXPosition = 0;
			for(int x = currentPosition.getX(); x < Math.abs(xAxisDistanceInBlocks); x++) {
				if(currentPosition.getDirection() == Direction.EAST)
					nextXPosition++;
				else 
					nextXPosition--;
				System.out.println("moving to position (" + nextXPosition + "," + currentPosition.getY() + ") on direction " + currentPosition.getDirection());
				currentPosition.setX(nextXPosition);
			}
		}
		
		//Y axis movement
		if(yAxisDistanceInBlocks != 0) {
			if(yAxisDistanceInBlocks < 0) {
				currentPosition.setDirection(Direction.NORTH);
			}
			if(yAxisDistanceInBlocks > 0) {
				currentPosition.setDirection(Direction.SOUTH);
			}
			int nextYPosition = 0;
			for(int y = currentPosition.getY(); y < Math.abs(yAxisDistanceInBlocks); y++) {
				if(currentPosition.getDirection() == Direction.NORTH)
					nextYPosition++;
				else 
					nextYPosition--;
				System.out.println("moving to position (" + currentPosition.getX() + "," + nextYPosition + ") on direction " + currentPosition.getDirection());
				currentPosition.setY(nextYPosition);
			}
		}
	}
	
	public void setShipmentsCargo(ArrayList<DeliveryContent> lunches) throws NotEnoughRoomInTransportException {
		if(lunches.size() > room) {
			throw new NotEnoughRoomInTransportException();
		}
		this.contents = lunches;
	}
}
