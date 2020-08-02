package co.com.s4n.deliveries.model.transport;

import java.io.IOException;

import co.com.s4n.deliveries.common.util.properties.Constants;
import co.com.s4n.deliveries.common.util.properties.PropertiesUtil;
import co.com.s4n.deliveries.model.location.Direction;
import co.com.s4n.deliveries.model.location.Position;

public class Vehicle implements Traceable{

	protected long id;
	protected Position currentPosition = new Position("ORIGIN", Direction.NORTH, 0, 0);
	protected long speed;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Position getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	protected boolean available;
	/*
	 * Moves current vehicle to a given position
	 */
	protected void moveToPosition(Position destination) {
		
		int xAxisDistanceInBlocks = currentPosition.getX() - destination.getX();
		String message = "Moving to position: (";
		//X axis movement
		if(xAxisDistanceInBlocks > 0) {
			currentPosition.setDirection(Direction.WEST);
			if(currentPosition.getX() == xAxisDistanceInBlocks) { 
//				currentPosition.setX(currentPosition.getX() - 1);
				moveAlong(currentPosition.getX() - 1, currentPosition.getY());
			}
			for(int x = currentPosition.getX(); x > destination.getX(); x--) {
//				currentPosition.setX(x-1);
				moveAlong(x-1, currentPosition.getY());
				System.out.println(message + currentPosition.getX() + "," + currentPosition.getY() + ") " + currentPosition.getDirection());
			}
		} else if(xAxisDistanceInBlocks < 0) {
			currentPosition.setDirection(Direction.EAST);
			if(currentPosition.getX() == xAxisDistanceInBlocks) 
//				currentPosition.setX(currentPosition.getX() + 1);
				moveAlong(currentPosition.getX() + 1, currentPosition.getY());
			for(int x = currentPosition.getX(); x < xAxisDistanceInBlocks; x++) {
//				currentPosition.setX(x+1);
				moveAlong(x + 1, currentPosition.getY());
				System.out.println(message + currentPosition.getX() + "," + currentPosition.getY() + ") " + currentPosition.getDirection());
			}
		}


		int yAxisDistanceInBlocks = currentPosition.getY() - destination.getY();

		//Y axis movement
		if(yAxisDistanceInBlocks > 0) {
			currentPosition.setDirection(Direction.SOUTH);
			if(currentPosition.getY() == yAxisDistanceInBlocks) 
//				currentPosition.setY(currentPosition.getY() - 1);
				moveAlong(currentPosition.getX(), currentPosition.getY() - 1);
			
			for(int y = currentPosition.getY(); y > destination.getY(); y--) {
				currentPosition.setY(y-1);
				moveAlong(currentPosition.getX(), y-1);
				System.out.println(message + currentPosition.getX() + "," + currentPosition.getY() + ") " + currentPosition.getDirection());
			}
		} else if(yAxisDistanceInBlocks < 0) {
			currentPosition.setDirection(Direction.NORTH);
			if(currentPosition.getY() == yAxisDistanceInBlocks) 
//				currentPosition.setY(currentPosition.getY() + 1);
				moveAlong(currentPosition.getX(), currentPosition.getY() + 1);
			for(int y = currentPosition.getY(); y < destination.getY(); y++) {
//				currentPosition.setY(y+1);
				moveAlong(currentPosition.getX(), y+1);
				System.out.println(message + currentPosition.getX() + "," + currentPosition.getY() + ") " + currentPosition.getDirection());
			}
		}
		
	}
	
	void moveAlong(int x, int y) {
		//next line for testing simulation
				try {
					Thread.sleep(Long.parseLong(PropertiesUtil.getPropertyValue(Constants.DRONE_SPEED)) * 1000);
					currentPosition.setX(x);
					currentPosition.setY(y);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				trace(true);
	}
	
	@Override
	public void trace(boolean detail) {
		traceService.trace(this, detail);
	}
	
}
