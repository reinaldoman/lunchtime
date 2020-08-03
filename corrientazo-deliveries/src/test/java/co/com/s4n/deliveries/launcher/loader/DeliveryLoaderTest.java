package co.com.s4n.deliveries.launcher.loader;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.com.s4n.deliveries.exception.InvalidEntryException;
import co.com.s4n.deliveries.model.routes.RoutesDescriptor;
import co.com.s4n.deliveries.model.transport.Drone;

public class DeliveryLoaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	private DeliveryLoader deliveryLoader = new DeliveryLoader();
	
	@Test
	public final void testListAvailableDrones() {
		ArrayList<Drone> availableDrones = null;
		try {
			availableDrones = deliveryLoader.listAvailableDrones();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(availableDrones.size() == 1);
	}

	@Test
	public final void testLoadData() {
		RoutesDescriptor descriptor = null;
		try {
			descriptor = deliveryLoader.loadData("01");
		} catch (InvalidEntryException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue("AAAAIAA".hashCode() == descriptor.getDestinationDeliveryCoordinates().get(0).getOriginalPath().hashCode());
	}

}
