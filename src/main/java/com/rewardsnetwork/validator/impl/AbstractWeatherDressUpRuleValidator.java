package com.rewardsnetwork.validator.impl;

import java.util.List;

import com.rewardsnetwork.enums.DressUpOpType;
import com.rewardsnetwork.exception.ValidationException;
import com.rewardsnetwork.validator.IWeatherDressUpRuleValidator;

public abstract class AbstractWeatherDressUpRuleValidator implements IWeatherDressUpRuleValidator {

	public AbstractWeatherDressUpRuleValidator() {
	}

	@Override
	public boolean isValid(String command, List<String> processedCommands) {

		validateCommandNotRepeated(command, processedCommands);

		doValidate(command, processedCommands);

		validateAllClothingsOnBeforeLeaving(command, processedCommands);

		// If all the conditions are satisfied, return true
		return true;

	}

	@Override
	public void validateCommandNotRepeated(String command, List<String> processedCommands) {

		// Only 1 piece of each type of clothing may be put on
		if (processedCommands.contains(command)) {

			throw new ValidationException(DressUpOpType.FAIL.name());

		}

	}

	@Override
	public void validateAllClothingsOnBeforeLeaving(String command, List<String> processedCommands) {

		// You cannot leave the house until all items of clothing are on (except
		// socks and a jacket when it�s hot)
		// If the command is for leaving the house, make sure that all the other
		// commands have been processed
		if (command.equals("7")) {

			// Check for other types of clothing irrespective of the weather
			// type
			if (!processedCommands.contains("1") ||
					!processedCommands.contains("2") ||
					!processedCommands.contains("4") ||
					!processedCommands.contains("6") ||
					!processedCommands.contains("8")) {

				throw new ValidationException(DressUpOpType.FAIL.name());

			}

		}

	}

	protected abstract void doValidate(String command, List<String> processedCommands);

}
