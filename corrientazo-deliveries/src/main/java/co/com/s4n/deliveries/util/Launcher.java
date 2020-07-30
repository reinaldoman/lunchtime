package co.com.s4n.deliveries.util;

import java.util.ArrayList;

import co.com.s4n.deliveries.exception.InvalidEntryException;
import co.com.s4n.deliveries.exception.NonCoveredDestinationException;
import co.com.s4n.deliveries.exception.NotEnoughRoomInTransportException;
import co.com.s4n.deliveries.model.Drone;
import co.com.s4n.deliveries.model.Lunch;
import co.com.s4n.deliveries.model.Position;
import co.com.s4n.deliveries.util.loader.DeliveryLoader;
import co.com.s4n.deliveries.util.loader.RoutesDescriptor;
import co.com.s4n.deliveries.validation.DeliveryAreaCoverageValidation;
import co.com.s4n.deliveries.validation.ValidationResult;

public class Launcher {

	
	public void launch() throws InvalidEntryException, NotEnoughRoomInTransportException, NonCoveredDestinationException {
		DeliveryLoader deliveryLoader = new DeliveryLoader();
		RoutesDescriptor routesDescriptor = deliveryLoader.loadData("01");//TODO: remove hard code and invoke dataService
		Drone drone = new Drone();
		drone.setId(routesDescriptor.getTransportId());
		ArrayList<Lunch> lunches = new ArrayList<Lunch>();
		ArrayList<Position> nonDeliveredPositions = new ArrayList<Position>();
		for(Position position : routesDescriptor.getDestinationDeliveryCoordinates()) {
			DeliveryAreaCoverageValidation validation = new DeliveryAreaCoverageValidation();
			ValidationResult result = validation.validate(position);
			if(!result.isValid()) {
				nonDeliveredPositions.add(position);
				throw new NonCoveredDestinationException(result.getMessage());
			}
			Lunch lunch = new Lunch();
			lunch.setDescription("LUNCH");
			lunches.add(lunch);
		}
		cleanupWrongCoordinates(routesDescriptor.getDestinationDeliveryCoordinates(), nonDeliveredPositions);
		drone.setDestinationDeliveryCoordinates(routesDescriptor.getDestinationDeliveryCoordinates());
		drone.setShipmentsCargo(lunches);
		drone.deliverShipments();
	}

	private void cleanupWrongCoordinates(ArrayList<Position> destinationDeliveryCoordinates,
			ArrayList<Position> nonDeliveredPositions) {
		// TODO Auto-generated method stub
		for(Position p : nonDeliveredPositions) {
			destinationDeliveryCoordinates.remove(p);
		}
		
	}
	
	
}
