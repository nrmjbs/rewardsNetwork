package com.rewardsnetwork.validator.impl;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rewardsnetwork.exception.ValidationException;
import com.rewardsnetwork.util.ICommandSplitter;

public class BasicCommandValidatorTest {

	@InjectMocks
	private BasicCommandValidator basicCommandValidator;

	@Mock
	private ICommandSplitter commandSplitter;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public final void testValidateDressUpCommandsForBasicSanity_ForNonNullness() {

		when(commandSplitter.splitToArray(anyString())).thenReturn(new String[] { "HOT", "8" });

		try {

			basicCommandValidator.validateDressUpCommandsForBasicSanity("HOT 8");

		} catch (ValidationException ve) {

			fail("Shouldn't get here");

		}

	}

	@Test
	public final void testValidateDressUpCommandsForBasicSanity_ForNullness() {

		try {

			basicCommandValidator.validateDressUpCommandsForBasicSanity(null);

			fail("Shouldn't get here");

		} catch (ValidationException ve) {

			// expected
		}

	}

	@Test
	public final void testValidateEnoughCommandsProvided_ForEmptyCommandSet() {

		when(commandSplitter.splitToArray(anyString())).thenReturn(new String[] {});

		try {

			basicCommandValidator.validateDressUpCommandsForBasicSanity("   ");

			fail("Shouldn't get here");

		} catch (ValidationException ve) {

			// expected
		}

	}

	@Test
	public final void testValidateEnoughCommandsProvided_ForLessThanRequiredCommands() {

		when(commandSplitter.splitToArray(anyString())).thenReturn(new String[] { "HOT" });

		try {

			basicCommandValidator.validateDressUpCommandsForBasicSanity("HOT");

			fail("Shouldn't get here");

		} catch (ValidationException ve) {

			// expected
		}

	}

	@Test
	public final void test_validateFirstCommandIsHotOrColdWeatherType_COLD() {

		when(commandSplitter.splitToArray(anyString())).thenReturn(new String[] { "COLD", "8" });

		try {

			basicCommandValidator.validateDressUpCommandsForBasicSanity("COLD 8");

		} catch (ValidationException ve) {

			fail("Shouldn't get here");

		}

	}

	@Test
	public final void test_validateFirstCommandIsHotOrColdWeatherType_UNKNOWN() {

		when(commandSplitter.splitToArray(anyString())).thenReturn(new String[] { "UNKNOWN", "8" });

		try {

			basicCommandValidator.validateDressUpCommandsForBasicSanity("UNKNOWN 8");

			fail("Shouldn't get here");

		} catch (ValidationException ve) {

			// expected
		}

	}
	
	@Test
	public final void test_validateSecondCommandIsRemovePajamas_ForCommandNotRemovePajamas() {

		when(commandSplitter.splitToArray(anyString())).thenReturn(new String[] { "HOT", "6"});

		try {

			basicCommandValidator.validateDressUpCommandsForBasicSanity("HOT 6");

			fail("Shouldn't get here");

		} catch (ValidationException ve) {

			// expected
		}

	}
	
	@Test
	public final void test_validateNumericCommandsProvided_ForNonNumericCommands() {

		when(commandSplitter.splitToArray(anyString())).thenReturn(new String[] { "HOT", "8", "a", "4"});

		try {

			basicCommandValidator.validateDressUpCommandsForBasicSanity("HOT 8, a, 4");

			fail("Shouldn't get here");

		} catch (ValidationException ve) {

			// expected
		}

	}
	

	@After
	public void tearDown() throws Exception {
	}

}
