package co.com.s4n.deliveries.common.util.validation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InputValidationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testValidate() {
		InputValidation inputValidation = new InputValidation();
		Result result = inputValidation.validate("AADDAAIAA");
		assertTrue(result.isValid());
	}

	@Test
	public final void testValidationfalse() {
		InputValidation inputValidation = new InputValidation();
		Result result = inputValidation.validate("AEIOU");
		assertFalse(result.isValid());
	}
}
