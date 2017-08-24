package com.rewardsnetwork.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.rewardsnetwork.builder.IWeatherDressUpResponseBuilder;
import com.rewardsnetwork.enums.DressUpOpType;
import com.rewardsnetwork.enums.UtilConstants;
import com.rewardsnetwork.exception.ValidationException;
import com.rewardsnetwork.factory.IWeatherDressUpResponseBuilderFactory;
import com.rewardsnetwork.factory.IWeatherDressUpRuleValidatorFactory;
import com.rewardsnetwork.factory.impl.WeatherDressUpResponseBuilderFactory;
import com.rewardsnetwork.factory.impl.WeatherDressUpRuleValidatorFactory;
import com.rewardsnetwork.util.ICommandSplitter;
import com.rewardsnetwork.util.impl.CommandSplitter;
import com.rewardsnetwork.validator.IBasicCommandValidator;
import com.rewardsnetwork.validator.IWeatherDressUpRuleValidator;
import com.rewardsnetwork.validator.impl.BasicCommandValidator;

public class GetDressed implements IGetDressed {

	private ICommandSplitter commandSplitter;

	private IBasicCommandValidator basicCommandValidator;

	private IWeatherDressUpRuleValidatorFactory weatherDressUpRuleValidatorFactory;

	private IWeatherDressUpResponseBuilderFactory weatherDressUpResponseBuilderFactory;

	public GetDressed() {

		commandSplitter = new CommandSplitter();

		basicCommandValidator = new BasicCommandValidator();

		weatherDressUpRuleValidatorFactory = new WeatherDressUpRuleValidatorFactory();

		weatherDressUpResponseBuilderFactory = new WeatherDressUpResponseBuilderFactory();

	}

	@Override
	public String dressUp(String commandText) {

		try {

			basicCommandValidator.validateDressUpCommandsForBasicSanity(commandText);

		} catch (ValidationException ex) {

			return getFailedResponse(null);

		}

		final String[] splittedCommands = commandSplitter.splitToArray(commandText);

		// Get weather dress up rule validator by type of weather
		final IWeatherDressUpRuleValidator weatherDressUpRuleValidator = weatherDressUpRuleValidatorFactory.getDressUpRuleValidatorByType(
				splittedCommands[0]);

		final IWeatherDressUpResponseBuilder weatherDressUpResponseBuilder = weatherDressUpResponseBuilderFactory.getDressUpResponseBuilderByType(
				splittedCommands[0]);

		final List<String> processedCommands = new ArrayList<String>();

		try {

			Arrays	.stream(splittedCommands)
					.skip(1)
					.forEach(command -> {

						if (weatherDressUpRuleValidator.isValid(command, processedCommands)) {

							weatherDressUpResponseBuilder.buildDressUpResponse(command);

						}

						processedCommands.add(command);

					});

		} catch (ValidationException ex) {

			return getFailedResponse(weatherDressUpResponseBuilder.getDressUpResponse());

		}

		if (!processedCommands.contains(DressUpOpType.LEAVING_HOUSE.getOpCode())) {

			return getFailedResponse(weatherDressUpResponseBuilder.getDressUpResponse());

		}

		return weatherDressUpResponseBuilder.getDressUpResponse();

	}

	private String getFailedResponse(String dressUpResponse) {

		if (StringUtils.isNotBlank(dressUpResponse)) {

			return new StringBuilder(dressUpResponse)	.append(UtilConstants.QUOMA_SPACE_DELIMETER.getValue())
														.append(DressUpOpType.FAIL.getOpDescription())
														.toString();

		}

		return DressUpOpType.FAIL.getOpDescription();

	}

}
