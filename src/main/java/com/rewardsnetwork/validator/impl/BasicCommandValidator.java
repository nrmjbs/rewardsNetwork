package com.rewardsnetwork.validator.impl;

import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.rewardsnetwork.enums.DressUpOpType;
import com.rewardsnetwork.enums.WeatherType;
import com.rewardsnetwork.exception.ValidationException;
import com.rewardsnetwork.util.ICommandSplitter;
import com.rewardsnetwork.util.impl.CommandSplitter;
import com.rewardsnetwork.validator.IBasicCommandValidator;

// Stateless Singletons
public class BasicCommandValidator implements IBasicCommandValidator {

	private ICommandSplitter commandSplitter;

	public BasicCommandValidator() {

		commandSplitter = new CommandSplitter();

	}

	@Override
	public void validateDressUpCommandsForBasicSanity(final String commandText) {

		validateCommandTextForNonNullness(commandText);

		final String[] splittedCommands = commandSplitter.splitToArray(commandText);

		validateCommandArrayNotEmpty(splittedCommands);

		validateEnoughCommandsProvided(splittedCommands);

		validateNumericCommandsProvided(splittedCommands);

		validateFirstCommandIsHotOrColdWeatherType(splittedCommands);

		validateSecondCommandIsRemovePajamas(splittedCommands);

	}

	private void validateCommandTextForNonNullness(final String commandText) {

		if (StringUtils.isBlank(commandText)) {

			throw new ValidationException("Dress up commands cannot be null.");

		}

	}

	private void validateCommandArrayNotEmpty(String[] splittedCommands) {

		if (ArrayUtils.isEmpty(splittedCommands)) {

			throw new ValidationException("Splitted commands cannot be null.");

		}

	}

	private void validateEnoughCommandsProvided(final String[] splittedCommands) {

		if (splittedCommands.length < 2) {

			throw new ValidationException("Not enough commands provided.");

		}

	}

	private void validateFirstCommandIsHotOrColdWeatherType(final String[] splittedCommands) {

		String firstCommand = splittedCommands[0];

		if (!(WeatherType.HOT	.name()
								.equalsIgnoreCase(firstCommand) ||
				WeatherType.COLD.name()
								.equalsIgnoreCase(firstCommand))) {

			throw new ValidationException("Invalid command, please enter valid weather type.");

		}

	}

	private void validateSecondCommandIsRemovePajamas(final String[] splittedCommands) {

		if (!Objects.equals(DressUpOpType.REMOVING_PJS.getOpCode(), splittedCommands[1])) {

			throw new ValidationException("Invalid command, please enter valid command to remove pajamas.");

		}

	}

	private void validateNumericCommandsProvided(final String[] splittedCommands) {

		try {

			Arrays	.stream(splittedCommands)
					.skip(2)
					.mapToInt(command -> Integer.parseInt(command))
					.count();

		} catch (NumberFormatException nfe) {

			throw new ValidationException("Commands in numeric format not provided.");

		}

	}

	@SuppressWarnings("unused")
	private void validateLeavingHouseCommandProvided(final String[] splittedCommands) {

		if (!Arrays	.stream(splittedCommands)
					.anyMatch(command -> command.equals(DressUpOpType.LEAVING_HOUSE.getOpCode()))) {

			throw new ValidationException("Mandatory command Leaving House[7] not provided.");

		}

	}

}
