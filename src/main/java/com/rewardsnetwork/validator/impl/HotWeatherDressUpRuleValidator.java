package com.rewardsnetwork.validator.impl;

import java.util.List;

import com.rewardsnetwork.enums.DressUpOpType;
import com.rewardsnetwork.exception.ValidationException;
import com.rewardsnetwork.validator.IHotWeatherDressUpRuleValidator;

// Stateless Singletons
public class HotWeatherDressUpRuleValidator extends AbstractWeatherDressUpRuleValidator implements
		IHotWeatherDressUpRuleValidator {

	public HotWeatherDressUpRuleValidator() {
	}

	@Override
	public void doValidate(String command, List<String> processedCommands) {

		validateNoSocksOrJacket(command);

		validateShortsOnBeforeSandals(command, processedCommands);

		validateShirtOnBeforeHeadwear(command, processedCommands);

	}

	@Override
	public void validateShirtOnBeforeHeadwear(String command, List<String> processedCommands) {

		// Check shirt on before head-wear
		if (command.equals("2")) {

			if (!processedCommands.contains("4")) {

				throw new ValidationException(DressUpOpType.FAIL.name());

			}

		}

	}

	@Override
	public void validateShortsOnBeforeSandals(String command, List<String> processedCommands) {

		// Shorts must be on before sandals
		if (command.equals("1")) {

			if (!processedCommands.contains("6")) {

				throw new ValidationException(DressUpOpType.FAIL.name());

			}

		}

	}

	@Override
	public void validateNoSocksOrJacket(String command) {

		// No socks or jacket when hot
		if (command.equals("3") || command.equals("5")) {

			throw new ValidationException(DressUpOpType.FAIL.name());

		}

	}

}
