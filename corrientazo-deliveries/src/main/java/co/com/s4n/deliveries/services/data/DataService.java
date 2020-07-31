package co.com.s4n.deliveries.services.data;

import java.io.IOException;
import java.util.ArrayList;

import co.com.s4n.deliveries.exception.InvalidEntryException;
import co.com.s4n.deliveries.exception.NotEnoughRoomInTransportException;
import co.com.s4n.deliveries.launcher.loader.DeliveryLoader;
import co.com.s4n.deliveries.model.content.DeliveryContent;
import co.com.s4n.deliveries.model.content.Lunch;
import co.com.s4n.deliveries.model.routes.RoutesDescriptor;
import co.com.s4n.deliveries.model.transport.Drone;

public class DataService {

	private DeliveryLoader deliveryLoader = new DeliveryLoader();
	
	public ArrayList<Drone> getAllDrones() throws IOException{
		return deliveryLoader.listAvailableDrones();
	}
	
	public Drone getDroneById(Long id) throws IOException {
		
		RoutesDescriptor routesDescriptor = null;
		try {
			routesDescriptor = deliveryLoader.loadData(id < 10 ? "0" + id.toString() : id.toString());//TODO improve this...
		} catch (InvalidEntryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//TODO: remove hard code and invoke dataService
		Drone drone = new Drone();
		drone.setId(Long.parseLong(routesDescriptor.getTransportId()));
		ArrayList<DeliveryContent> lunches = new ArrayList<DeliveryContent>();
		for(int i = 0; i < routesDescriptor.getDestinationDeliveryCoordinates().size(); i++) {
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
