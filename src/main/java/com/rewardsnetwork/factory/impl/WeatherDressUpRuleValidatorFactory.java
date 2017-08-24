package com.rewardsnetwork.factory.impl;

import com.rewardsnetwork.enums.WeatherType;
import com.rewardsnetwork.exception.NotSupportedException;
import com.rewardsnetwork.factory.IWeatherDressUpRuleValidatorFactory;
import com.rewardsnetwork.validator.IWeatherDressUpRuleValidator;
import com.rewardsnetwork.validator.impl.ColdWeatherDressUpRuleValidator;
import com.rewardsnetwork.validator.impl.HotWeatherDressUpRuleValidator;

//Factory and Singleton Pattern
public class WeatherDressUpRuleValidatorFactory implements IWeatherDressUpRuleValidatorFactory {

	@Override
	public IWeatherDressUpRuleValidator getDressUpRuleValidatorByType(String weatherType) {

		try {

			switch (WeatherType.valueOf(weatherType)) {

			case HOT:

				return new HotWeatherDressUpRuleValidator();

			case COLD:

				return new ColdWeatherDressUpRuleValidator();

			default:

				throw new NotSupportedException("Unknown weather type.");

			}

		} catch (IllegalArgumentException | NullPointerException ex) {

			throw new NotSupportedException("Unknown weather type.");

		}

	}

}
