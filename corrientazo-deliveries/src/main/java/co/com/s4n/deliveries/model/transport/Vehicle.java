package co.com.s4n.deliveries.model.transport;

import java.util.ArrayList;

import co.com.s4n.deliveries.model.content.DeliveryContent;
import co.com.s4n.deliveries.model.location.Direction;
import co.com.s4n.deliveries.model.location.Position;

public class Vehicle {

	protected long id;
	protected Position currentPosition = new Position("ORIGIN", Direction.NORTH, 0, 0);
	protected boolean available;
	protected int room;
	protected ArrayList<DeliveryContent> contents;
}
