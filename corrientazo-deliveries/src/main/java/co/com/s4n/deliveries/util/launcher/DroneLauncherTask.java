package co.com.s4n.deliveries.util.launcher;

import java.io.IOException;
import java.util.ArrayList;

import co.com.s4n.deliveries.exception.InvalidEntryException;
import co.com.s4n.deliveries.exception.NonCoveredDestinationException;
import co.com.s4n.deliveries.exception.NotEnoughRoomInTransportException;
import co.com.s4n.deliveries.model.Drone;
import co.com.s4n.deliveries.model.Lunch;
import co.com.s4n.deliveries.model.Position;
import co.com.s4n.deliveries.services.DataService;
import co.com.s4n.deliveries.validation.DeliveryAreaCoverageValidation;
import co.com.s4n.deliveries.validation.ValidationResult;

public class DroneLauncherTask implements ITask{

	
	private Drone drone;
	
	private DataService dataService = new DataService();
	
	public DroneLauncherTask(Drone drone) {
		this.drone = drone;
	}
	
	public void launchDrone() throws InvalidEntryException, NotEnoughRoomInTransportException, NonCoveredDestinationException {
		
		try {
			this.drone = dataService.getDroneById(this.drone.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		validateDestinations();
		packLunches();
		this.drone.deliver();
	}
	
	private void packLunches() throws NotEnoughRoomInTransportException {
		ArrayList<Lunch> lunches = new ArrayList<Lunch>();
		for(int i = 0; i < this.drone.getDestinationDeliveryCoordinates().size(); i++) {
			Lunch lunch = new Lunch();
			lunch.setDescription("LUNCH");
			lunches.add(lunch);
		}
		this.drone.setShipmentsCargo(lunches);
	}
	
	private void validateDestinations() throws NonCoveredDestinationException {
		for(Position position : this.drone.getDestinationDeliveryCoordinates()) {
			DeliveryAreaCoverageValidation validation = new DeliveryAreaCoverageValidation();
			ValidationResult result = validation.validate(position);
			if(!result.isValid()) {
				throw new NonCoveredDestinationException(result.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		DroneLauncherTask launcher = new DroneLauncherTask(new Drone());
		try {
			launcher.launchDrone();
		} catch (InvalidEntryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughRoomInTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NonCoveredDestinationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			launchDrone();
		} catch (InvalidEntryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughRoomInTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NonCoveredDestinationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
	}