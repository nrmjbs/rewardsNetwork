package com.rewardsnetwork.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rewardsnetwork.builder.IWeatherDressUpResponseBuilder;
import com.rewardsnetwork.exception.ValidationException;
import com.rewardsnetwork.factory.IWeatherDressUpResponseBuilderFactory;
import com.rewardsnetwork.factory.IWeatherDressUpRuleValidatorFactory;
import com.rewardsnetwork.util.ICommandSplitter;
import com.rewardsnetwork.validator.IBasicCommandValidator;
import com.rewardsnetwork.validator.IWeatherDressUpRuleValidator;

public class GetDressedTest {

	@InjectMocks
	private GetDressed getDressed;

	@Mock
	private ICommandSplitter commandSplitter;

	@Mock
	private IBasicCommandValidator basicCommandValidator;

	@Mock
	private IWeatherDressUpRuleValidatorFactory weatherDressUpRuleValidatorFactory;

	@Mock
	private IWeatherDressUpResponseBuilderFactory weatherDressUpResponseBuilderFactory;

	private IWeatherDressUpRuleValidator weatherDressUpRuleValidator;

	private IWeatherDressUpResponseBuilder weatherDressUpResponseBuilder;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		// setup mocks
		weatherDressUpRuleValidator = Mockito.mock(IWeatherDressUpRuleValidator.class);

		weatherDressUpResponseBuilder = Mockito.mock(
				IWeatherDressUpResponseBuilder.class);

	}

	@Test
	public final void test_dressUp_failForBasicSanityValidationFailure() {

		doThrow(new ValidationException("failed"))	.when(basicCommandValidator)
													.validateDressUpCommandsForBasicSanity(anyString());

		String dressUpResponse = getDressed.dressUp("HOT 6");

		assertTrue(StringUtils.isNotBlank(dressUpResponse));

		assertEquals("fail", dressUpResponse);

	}

	@Test
	public final void test_dressUp_failForInvalidCommandSetForHotWeather() {

		// set expectations
		doNothing()	.when(basicCommandValidator)
					.validateDressUpCommandsForBasicSanity(anyString());

		when(commandSplitter.splitToArray(anyString())).thenReturn(new String[] { "HOT", "8", "6", "6" });

		when(weatherDressUpRuleValidatorFactory.getDressUpRuleValidatorByType(anyString())).thenReturn(
				weatherDressUpRuleValidator);

		when(weatherDressUpResponseBuilderFactory.getDressUpResponseBuilderByType(anyString())).thenReturn(
				weatherDressUpResponseBuilder);

		doThrow(ValidationException.class)	.when(weatherDressUpRuleValidator)
											.isValid(anyString(), anyListOf(String.class));

		doReturn("Removing PJs, shorts").when(weatherDressUpResponseBuilder)
										.getDressUpResponse();

		// call class under test
		String dressUpResponse = getDressed.dressUp("HOT 8, 6, 6");

		// verify results against expectations
		assertTrue(StringUtils.isNotBlank(dressUpResponse));

		assertEquals("Removing PJs, shorts, fail", dressUpResponse);

	}

	@Test
	public final void test_dressUp_failForLeavingHouseNotInCommandSetForHotWeather() {

		// set expectations
		doNothing()	.when(basicCommandValidator)
					.validateDressUpCommandsForBasicSanity(anyString());

		when(commandSplitter.splitToArray(anyString())).thenReturn(
				new String[] { "HOT", "8", "6", "4", "2", "1" });

		when(weatherDressUpRuleValidatorFactory.getDressUpRuleValidatorByType(anyString())).thenReturn(
				weatherDressUpRuleValidator);

		when(weatherDressUpResponseBuilderFactory.getDressUpResponseBuilderByType(anyString())).thenReturn(
				weatherDressUpResponseBuilder);

		when(weatherDressUpRuleValidator.isValid(anyString(), anyListOf(String.class))).thenReturn(true);

		doNothing()	.when(weatherDressUpResponseBuilder)
					.buildDressUpResponse(anyString());

		doReturn("Removing PJs, shorts, shirt, sunglasses, sandals").when(weatherDressUpResponseBuilder)
																	.getDressUpResponse();

		// call class under test
		String dressUpResponse = getDressed.dressUp("HOT 8, 6, 4, 2, 1");

		// verify results against expectations
		assertTrue(StringUtils.isNotBlank(dressUpResponse));

		assertEquals("Removing PJs, shorts, shirt, sunglasses, sandals, fail", dressUpResponse);

	}

	@Test
	public final void test_dressUp_passForValidCommandSetForHotWeather() {

		// set expectations
		doNothing()	.when(basicCommandValidator)
					.validateDressUpCommandsForBasicSanity(anyString());

		when(commandSplitter.splitToArray(anyString())).thenReturn(
				new String[] { "HOT", "8", "6", "4", "2", "1", "7" });

		when(weatherDressUpRuleValidatorFactory.getDressUpRuleValidatorByType(anyString())).thenReturn(
				weatherDressUpRuleValidator);

		when(weatherDressUpResponseBuilderFactory.getDressUpResponseBuilderByType(anyString())).thenReturn(
				weatherDressUpResponseBuilder);

		when(weatherDressUpRuleValidator.isValid(anyString(), anyListOf(String.class))).thenReturn(true);

		doNothing()	.when(weatherDressUpResponseBuilder)
					.buildDressUpResponse(anyString());

		doReturn("Removing PJs, shorts, shirt, sunglasses, sandals, leaving house")	.when(weatherDressUpResponseBuilder)
																					.getDressUpResponse();

		// call class under test
		String dressUpResponse = getDressed.dressUp("HOT 8, 6, 4, 2, 1, 7");

		// verify results against expectations
		assertTrue(StringUtils.isNotBlank(dressUpResponse));

		assertEquals("Removing PJs, shorts, shirt, sunglasses, sandals, leaving house", dressUpResponse);

	}

	// for cold
	@Test
	public final void test_dressUp_failForInvalidCommandSetForColdWeather() {

		// set expectations
		doNothing()	.when(basicCommandValidator)
					.validateDressUpCommandsForBasicSanity(anyString());

		when(commandSplitter.splitToArray(anyString())).thenReturn(
				new String[] { "COLD", "8", "6", "3", "4", "2", "5", "7" });

		when(weatherDressUpRuleValidatorFactory.getDressUpRuleValidatorByType(anyString())).thenReturn(
				weatherDressUpRuleValidator);

		when(weatherDressUpResponseBuilderFactory.getDressUpResponseBuilderByType(anyString())).thenReturn(
				weatherDressUpResponseBuilder);

		doThrow(ValidationException.class)	.when(weatherDressUpRuleValidator)
											.isValid(anyString(), anyListOf(String.class));

		doReturn("Removing PJs, pants, socks, shirt, hat, jacket")	.when(weatherDressUpResponseBuilder)
																	.getDressUpResponse();

		// call class under test
		String dressUpResponse = getDressed.dressUp("COLD 8, 6, 3, 4, 2, 5, 7");

		// verify results against expectations
		assertTrue(StringUtils.isNotBlank(dressUpResponse));

		assertEquals("Removing PJs, pants, socks, shirt, hat, jacket, fail", dressUpResponse);

	}

	@Test
	public final void test_dressUp_failForLeavingHouseNotInCommandSetForColdWeather() {

		// set expectations
		doNothing()	.when(basicCommandValidator)
					.validateDressUpCommandsForBasicSanity(anyString());

		when(commandSplitter.splitToArray(anyString())).thenReturn(
				new String[] { "COLD", "8", "6", "3", "4", "2", "5", "1" });

		when(weatherDressUpRuleValidatorFactory.getDressUpRuleValidatorByType(anyString())).thenReturn(
				weatherDressUpRuleValidator);

		when(weatherDressUpResponseBuilderFactory.getDressUpResponseBuilderByType(anyString())).thenReturn(
				weatherDressUpResponseBuilder);

		when(weatherDressUpRuleValidator.isValid(anyString(), anyListOf(String.class))).thenReturn(true);

		doNothing()	.when(weatherDressUpResponseBuilder)
					.buildDressUpResponse(anyString());

		doReturn("Removing PJs, pants, socks, shirt, hat, jacket, boots")	.when(weatherDressUpResponseBuilder)
																			.getDressUpResponse();

		// call class under test
		String dressUpResponse = getDressed.dressUp("COLD 8, 6, 3, 4, 2, 5, 1");

		// verify results against expectations
		assertTrue(StringUtils.isNotBlank(dressUpResponse));

		assertEquals("Removing PJs, pants, socks, shirt, hat, jacket, boots, fail", dressUpResponse);

	}

	@Test
	public final void test_dressUp_passForValidCommandSetForColdWeather() {

		// set expectations
		doNothing()	.when(basicCommandValidator)
					.validateDressUpCommandsForBasicSanity(anyString());

		when(commandSplitter.splitToArray(anyString())).thenReturn(
				new String[] { "COLD", "8", "6", "3", "4", "2", "5", "1", "7" });

		when(weatherDressUpRuleValidatorFactory.getDressUpRuleValidatorByType(anyString())).thenReturn(
				weatherDressUpRuleValidator);

		when(weatherDressUpResponseBuilderFactory.getDressUpResponseBuilderByType(anyString())).thenReturn(
				weatherDressUpResponseBuilder);

		when(weatherDressUpRuleValidator.isValid(anyString(), anyListOf(String.class))).thenReturn(true);

		doNothing()	.when(weatherDressUpResponseBuilder)
					.buildDressUpResponse(anyString());

		doReturn("Removing PJs, pants, socks, shirt, hat, jacket, boots, leaving house").when(
				weatherDressUpResponseBuilder)
																						.getDressUpResponse();

		// call class under test
		String dressUpResponse = getDressed.dressUp("COLD 8, 6, 3, 4, 2, 5, 1, 7");

		// verify results against expectations
		assertTrue(StringUtils.isNotBlank(dressUpResponse));

		assertEquals("Removing PJs, pants, socks, shirt, hat, jacket, boots, leaving house", dressUpResponse);

	}

	@After
	public void tearDown() throws Exception {
	}

}
