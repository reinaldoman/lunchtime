package co.com.s4n.deliveries.model.transport;

import java.util.ArrayList;

import co.com.s4n.deliveries.model.content.DeliveryContent;
import co.com.s4n.deliveries.model.location.Direction;
import co.com.s4n.deliveries.model.location.Position;

public class Vehicle {

	protected long id;
	protected Position currentPosition = new Position("ORIGIN", Direction.NORTH, 0, 0);
	protected boolean available;
	protected int deliveryCapacity;
	protected ArrayList<DeliveryContent> contents;
	
	/*
	 * Moves current vehicle to a given position
	 */
	protected void moveToPosition(Position destination) {
		
		int xAxisDistanceInBlocks = currentPosition.getX() - destination.getX();
		String message = "Moving to position: (";
		//X axis movement
		if(xAxisDistanceInBlocks > 0) {
			currentPosition.setDirection(Direction.WEST);
			if(currentPosition.getX() == xAxisDistanceInBlocks) 
				currentPosition.setX(currentPosition.getX() - 1);
			for(int x = currentPosition.getX(); x > destination.getX(); x--) {
				currentPosition.setX(x-1);
				System.out.println(message + currentPosition.getX() + "," + currentPosition.getY() + ") " + currentPosition.getDirection());
			}
		} else if(xAxisDistanceInBlocks < 0) {
			currentPosition.setDirection(Direction.EAST);
			if(currentPosition.getX() == xAxisDistanceInBlocks) 
				currentPosition.setX(currentPosition.getX() + 1);
			for(int x = currentPosition.getX(); x < xAxisDistanceInBlocks; x++) {
				currentPosition.setX(x+1);
				System.out.println(message + currentPosition.getX() + "," + currentPosition.getY() + ") " + currentPosition.getDirection());
			}
		}


		int yAxisDistanceInBlocks = currentPosition.getY() - destination.getY();

		//Y axis movement
		if(yAxisDistanceInBlocks > 0) {
			currentPosition.setDirection(Direction.SOUTH);
			if(currentPosition.getY() == yAxisDistanceInBlocks) 
				currentPosition.setY(currentPosition.getY() - 1);
			
			for(int y = currentPosition.getY(); y > destination.getY(); y--) {
				currentPosition.setY(y-1);
				System.out.println(message + currentPosition.getX() + "," + currentPosition.getY() + ") " + currentPosition.getDirection());
			}
		} else if(yAxisDistanceInBlocks < 0) {
			currentPosition.setDirection(Direction.NORTH);
			if(currentPosition.getY() == yAxisDistanceInBlocks) 
				currentPosition.setY(currentPosition.getY() + 1);
			for(int y = currentPosition.getY(); y < destination.getY(); y++) {
				currentPosition.setY(y+1);
				System.out.println(message + currentPosition.getX() + "," + currentPosition.getY() + ") " + currentPosition.getDirection());
			}
		}
		
	}
}
