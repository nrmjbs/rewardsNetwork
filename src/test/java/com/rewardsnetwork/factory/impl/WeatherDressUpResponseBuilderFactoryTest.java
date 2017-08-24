package com.rewardsnetwork.factory.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rewardsnetwork.builder.IWeatherDressUpResponseBuilder;
import com.rewardsnetwork.builder.impl.ColdWeatherDressUpResponseBuilder;
import com.rewardsnetwork.builder.impl.HotWeatherDressUpResponseBuilder;
import com.rewardsnetwork.enums.WeatherType;
import com.rewardsnetwork.exception.NotSupportedException;
import com.rewardsnetwork.factory.IWeatherDressUpResponseBuilderFactory;

public class WeatherDressUpResponseBuilderFactoryTest {

	private final IWeatherDressUpResponseBuilderFactory weatherDressUpResponseBuilderFactory;

	public WeatherDressUpResponseBuilderFactoryTest() {

		weatherDressUpResponseBuilderFactory = new WeatherDressUpResponseBuilderFactory();

	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void test_getDressUpResponseBuilderByType_HOT() {

		final IWeatherDressUpResponseBuilder dressUpResponseBuilderByType = weatherDressUpResponseBuilderFactory.getDressUpResponseBuilderByType(
				WeatherType.HOT.name());

		assertTrue(HotWeatherDressUpResponseBuilder.class.isInstance(dressUpResponseBuilderByType));

	}

	@Test
	public final void test_getDressUpResponseBuilderByType_COLD() {

		final IWeatherDressUpResponseBuilder dressUpResponseBuilderByType = weatherDressUpResponseBuilderFactory.getDressUpResponseBuilderByType(
				WeatherType.COLD.name());

		assertTrue(ColdWeatherDressUpResponseBuilder.class.isInstance(dressUpResponseBuilderByType));

	}

	@Test
	public final void test_getDressUpResponseBuilderByType_UNKNOWN() {

		try {

			weatherDressUpResponseBuilderFactory.getDressUpResponseBuilderByType("UNKNOWN");

			fail("Shouldn't get here");

		} catch (NotSupportedException nse) {

		}

	}

	@Test
	public final void test_getDressUpResponseBuilderByType_NULL() {

		try {

			weatherDressUpResponseBuilderFactory.getDressUpResponseBuilderByType(null);

			fail("Shouldn't get here");

		} catch (NotSupportedException nse) {

		}

	}

	@After
	public void tearDown() throws Exception {
	}

}
