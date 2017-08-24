package com.rewardsnetwork.factory;

import com.rewardsnetwork.validator.IWeatherDressUpRuleValidator;

public interface IWeatherDressUpRuleValidatorFactory {

	IWeatherDressUpRuleValidator getDressUpRuleValidatorByType(String weatherType);

}
