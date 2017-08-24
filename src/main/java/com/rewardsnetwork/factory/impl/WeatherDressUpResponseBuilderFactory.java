package com.rewardsnetwork.factory.impl;

import com.rewardsnetwork.builder.IWeatherDressUpResponseBuilder;
import com.rewardsnetwork.builder.impl.ColdWeatherDressUpResponseBuilder;
import com.rewardsnetwork.builder.impl.HotWeatherDressUpResponseBuilder;
import com.rewardsnetwork.enums.WeatherType;
import com.rewardsnetwork.exception.NotSupportedException;
import com.rewardsnetwork.factory.IWeatherDressUpResponseBuilderFactory;

// Factory and Singleton Pattern
public class WeatherDressUpResponseBuilderFactory implements IWeatherDressUpResponseBuilderFactory {

	@Override
	public IWeatherDressUpResponseBuilder getDressUpResponseBuilderByType(String weatherType) {

		try {

			switch (WeatherType.valueOf(weatherType)) {

			case HOT:

				return new HotWeatherDressUpResponseBuilder();

			case COLD:

				return new ColdWeatherDressUpResponseBuilder();

			default:

				throw new NotSupportedException("Unknown weather type.");

			}

		} catch (IllegalArgumentException | NullPointerException ex) {

			throw new NotSupportedException("Unknown weather type.");

		}

	}

}
