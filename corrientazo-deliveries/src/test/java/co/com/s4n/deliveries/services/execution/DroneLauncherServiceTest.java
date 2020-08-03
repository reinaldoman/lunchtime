package co.com.s4n.deliveries.services.execution;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.com.s4n.deliveries.model.transport.Drone;

public class DroneLauncherServiceTest {

	
	private DroneLauncherService droneLauncherService = new DroneLauncherService();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public final void testLaunchDrone() {
		Drone drone = new Drone();
		drone.setId(1);
		droneLauncherService.launchDrone(drone);
	}

	@Test
	public final void testLaunchAllAvailableDrones() {
		try {
			droneLauncherService.launchAllAvailableDrones();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
