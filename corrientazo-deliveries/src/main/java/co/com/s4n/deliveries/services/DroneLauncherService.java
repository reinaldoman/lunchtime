package co.com.s4n.deliveries.services;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import co.com.s4n.deliveries.model.Drone;
import co.com.s4n.deliveries.util.Constants;
import co.com.s4n.deliveries.util.PropertiesUtil;
import co.com.s4n.deliveries.util.launcher.DroneLauncherTask;
import co.com.s4n.deliveries.util.loader.DeliveryLoader;


public class DroneLauncherService {

	private ExecutorService executor;
	private DeliveryLoader deliveryLoader = new DeliveryLoader();
	
	public DroneLauncherService() {
		try {
			executor = Executors.newFixedThreadPool(Integer.parseInt(PropertiesUtil.getPropertyValue(Constants.MAX_NUMBER_OF_DRONES)));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void launchDrone(Drone drone) {
		
		//TODO: logger
		System.out.println("Preparint to launch drone " + drone.getId());
		 executor.submit(() -> {
	            new DroneLauncherTask(drone);
	        });

	        executor.shutdown();
	        executor.shutdownNow();
	        try {
	            executor.awaitTermination(20l, TimeUnit.NANOSECONDS);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	}
	
	
	public void launchAllAvailableDrones() throws IOException {
		for(Drone drone : deliveryLoader.listAvailableDrones()) {
			launchDrone(drone);
		}
	}
}
