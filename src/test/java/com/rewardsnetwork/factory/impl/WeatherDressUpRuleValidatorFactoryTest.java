package com.rewardsnetwork.factory.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rewardsnetwork.enums.WeatherType;
import com.rewardsnetwork.exception.NotSupportedException;
import com.rewardsnetwork.factory.IWeatherDressUpRuleValidatorFactory;
import com.rewardsnetwork.validator.IWeatherDressUpRuleValidator;
import com.rewardsnetwork.validator.impl.ColdWeatherDressUpRuleValidator;
import com.rewardsnetwork.validator.impl.HotWeatherDressUpRuleValidator;

public class WeatherDressUpRuleValidatorFactoryTest {

	private final IWeatherDressUpRuleValidatorFactory weatherDressUpRuleValidatorFactory;

	public WeatherDressUpRuleValidatorFactoryTest() {

		weatherDressUpRuleValidatorFactory = new WeatherDressUpRuleValidatorFactory();

	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void test_getDressUpResponseBuilderByType_HOT() {

		final IWeatherDressUpRuleValidator weatherDressUpRuleValidator = weatherDressUpRuleValidatorFactory.getDressUpRuleValidatorByType(
				WeatherType.HOT.name());

		assertTrue(HotWeatherDressUpRuleValidator.class.isInstance(weatherDressUpRuleValidator));

	}

	@Test
	public final void test_getDressUpResponseBuilderByType_COLD() {

		final IWeatherDressUpRuleValidator weatherDressUpRuleValidator = weatherDressUpRuleValidatorFactory.getDressUpRuleValidatorByType(
				WeatherType.COLD.name());

		assertTrue(ColdWeatherDressUpRuleValidator.class.isInstance(weatherDressUpRuleValidator));

	}

	@Test
	public final void test_getDressUpResponseBuilderByType_UNKNOWN() {

		try {

			weatherDressUpRuleValidatorFactory.getDressUpRuleValidatorByType("UNKNOWN");

			fail("Shouldn't get here");

		} catch (NotSupportedException nse) {

		}

	}

	@Test
	public final void test_getDressUpResponseBuilderByType_NULL() {

		try {

			weatherDressUpRuleValidatorFactory.getDressUpRuleValidatorByType(null);

			fail("Shouldn't get here");

		} catch (NotSupportedException nse) {

		}

	}

	@After
	public void tearDown() throws Exception {
	}

}
