package co.com.s4n.deliveries.common.util.translator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.com.s4n.deliveries.model.location.Direction;
import co.com.s4n.deliveries.model.location.Position;

public class PathToCoordinatesTranslatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testTranslate() {
		//fail("Not yet implemented"); // TODO
		/*Given a launch delivery input calculate a point and address
		 * on a coordinated system.
		 * */ 
		
		String inputRoute = "AAAAIAA";
		Position relativePosition = new Position("ORIGIN", Direction.NORTH, 0, 0);
		Position destinationPosition = PathToCoordinatesTranslator.translate(inputRoute, relativePosition);
		System.out.println(destinationPosition.getDirection());
		assertTrue(destinationPosition.getDirection() == Direction.WEST);
		
	}

}
