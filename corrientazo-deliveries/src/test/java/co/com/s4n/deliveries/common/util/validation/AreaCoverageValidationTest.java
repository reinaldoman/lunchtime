package co.com.s4n.deliveries.common.util.validation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.com.s4n.deliveries.model.location.Direction;
import co.com.s4n.deliveries.model.location.Position;

public class AreaCoverageValidationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testValidate() {
		//Validate if position is inside a coverage area
		Position position = new Position("AAAADI", Direction.EAST, 1, 5);
		AreaCoverageValidation areaCoverageValidation = new AreaCoverageValidation();
		Result result = areaCoverageValidation.validate(position);
		assertTrue(result.isValid());
	}

}
