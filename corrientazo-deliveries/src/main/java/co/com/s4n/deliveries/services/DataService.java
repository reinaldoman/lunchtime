package co.com.s4n.deliveries.services;

import java.io.IOException;
import java.util.ArrayList;

import co.com.s4n.deliveries.exception.InvalidEntryException;
import co.com.s4n.deliveries.exception.NotEnoughRoomInTransportException;
import co.com.s4n.deliveries.model.Drone;
import co.com.s4n.deliveries.model.Lunch;
import co.com.s4n.deliveries.model.Position;
import co.com.s4n.deliveries.util.loader.DeliveryLoader;
import co.com.s4n.deliveries.util.loader.RoutesDescriptor;

public class DataService {

	private DeliveryLoader deliveryLoader = new DeliveryLoader();
	
	public ArrayList<Drone> getAllDrones() throws IOException{
		return deliveryLoader.listAvailableDrones();
	}
	
	public Drone getDroneById(Long id) throws IOException {
		
		RoutesDescriptor routesDescriptor = null;
		try {
			routesDescriptor = deliveryLoader.loadData(id.toString());
		} catch (InvalidEntryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//TODO: remove hard code and invoke dataService
		Drone drone = new Drone();
		drone.setId(Long.parseLong(routesDescriptor.getTransportId()));
		ArrayList<Lunch> lunches = new ArrayList<Lunch>();
		for(Position position : routesDescriptor.getDestinationDeliveryCoordinates()) {
			Lunch lunch = new Lunch();
			lunch.setDescription("LUNCH");
			lunches.add(lunch);
		}
		drone.setDestinationDeliveryCoordinates(routesDescriptor.getDestinationDeliveryCoordinates());
		try {
			drone.setShipmentsCargo(lunches);
		} catch (NotEnoughRoomInTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return drone;
	}
	
}
