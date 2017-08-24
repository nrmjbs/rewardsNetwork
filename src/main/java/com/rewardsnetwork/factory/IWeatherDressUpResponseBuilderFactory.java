package com.rewardsnetwork.factory;

import com.rewardsnetwork.builder.IWeatherDressUpResponseBuilder;

public interface IWeatherDressUpResponseBuilderFactory {

	IWeatherDressUpResponseBuilder getDressUpResponseBuilderByType(String weatherType);

}
