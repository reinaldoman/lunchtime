package co.com.s4n.deliveries.launcher.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.com.s4n.deliveries.exception.InvalidEntryException;
import co.com.s4n.deliveries.exception.NonCoveredDestinationException;
import co.com.s4n.deliveries.exception.NotEnoughRoomInTransportException;
import co.com.s4n.deliveries.model.transport.Drone;

public class DroneLauncherTaskTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testLaunchDrone() {
		Drone drone = new Drone();
		drone.setId(01);
		DroneLauncherTask droneLauncherTask = new DroneLauncherTask(drone);
		try {
			droneLauncherTask.launchDrone();
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
