package co.com.s4n.deliveries.model.transport;

import java.io.IOException;
import java.util.ArrayList;

import co.com.s4n.deliveries.common.util.properties.Constants;
import co.com.s4n.deliveries.common.util.properties.PropertiesUtil;
import co.com.s4n.deliveries.exception.NotEnoughRoomInTransportException;
import co.com.s4n.deliveries.model.content.DeliveryContent;
import co.com.s4n.deliveries.model.location.Direction;
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
		move(destination);
	}

	/*
	 * Moves current drone to a given position
	 */
	private void move(Position destination) {
		int xAxisDistanceInBlocks = currentPosition.getX() - destination.getX();
		int yAxisDistanceInBlocks = currentPosition.getY() - destination.getY();
		//X axis movement 
		if(currentPosition.getX() != destination.getX()) {
			if(currentPosition.getDirection() != destination.getDirection()) {
				//rotate 180 degrees
				currentPosition.setDirection(currentPosition.getDirection() == Direction.EAST ? Direction.WEST : Direction.EAST);
			}
			else
				for(int x = currentPosition.getX(); x < xAxisDistanceInBlocks; x++) {
					System.out.println("moving to position (" + x + "," + currentPosition.getY() + ")");
					currentPosition.setX(x);
					currentPosition.setDirection(x > 0 ? Direction.EAST : Direction.WEST);
				}
			//Y axis movement 
		} else if(currentPosition.getY() != destination.getY()) {
			if(currentPosition.getDirection() != destination.getDirection()) {
				//rotate 180 degrees
				currentPosition.setDirection(currentPosition.getDirection() == Direction.NORTH ? Direction.SOUTH: Direction.NORTH);
			}
			else
				for(int y = currentPosition.getY(); y < yAxisDistanceInBlocks; y++) {
					System.out.println("moving to position (" + currentPosition.getX() + "," + y + ")");
					currentPosition.setY(y);
					currentPosition.setDirection(y > 0 ? Direction.NORTH : Direction.SOUTH);
				}
		} 
		System.out.println("IS IT EQUAL?");
		if (currentPosition.getX() != destination.getX() || currentPosition.getY() != destination.getY()) {
			System.out.println("something went wrong with coordinates!!");


			if(currentPosition.getDirection() != destination.getDirection()) {
				System.out.println("something went wrong with direction!!");
			}
		} 
		System.out.println("IS IT EQUAL OR NOT?");

		currentPosition = destination;//current position should be same as destination now
	}

	public void setShipmentsCargo(ArrayList<DeliveryContent> lunches) throws NotEnoughRoomInTransportException {
		if(lunches.size() > room) {
			throw new NotEnoughRoomInTransportException();
		}
		this.contents = lunches;
	}
}
