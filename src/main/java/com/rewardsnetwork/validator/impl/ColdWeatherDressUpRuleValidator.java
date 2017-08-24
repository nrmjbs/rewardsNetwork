package com.rewardsnetwork.validator.impl;

import java.util.List;

import com.rewardsnetwork.enums.DressUpOpType;
import com.rewardsnetwork.exception.ValidationException;
import com.rewardsnetwork.validator.IColdWeatherDressUpRuleValidator;

// Stateless Singletons
public class ColdWeatherDressUpRuleValidator
		extends AbstractWeatherDressUpRuleValidator
		implements IColdWeatherDressUpRuleValidator {

	public ColdWeatherDressUpRuleValidator() {
	}

	@Override
	public void doValidate(String command, List<String> processedCommands) {

		validateSocksPantsOnBeforeShoes(command, processedCommands);

		validateShirtOnBeforeHeadwearOrJacket(command, processedCommands);

	}

	@Override
	public void validateShirtOnBeforeHeadwearOrJacket(String command, List<String> processedCommands) {

		// Check shirt on before head-wear
		if (command.equals(DressUpOpType.HAT.getOpCode()) || command.equals(DressUpOpType.JACKET.getOpCode())) {

			if (!processedCommands.contains("4")) {

				throw new ValidationException(DressUpOpType.FAIL.name());

			}

		}

	}

	@Override
	public void validateSocksPantsOnBeforeShoes(String command, List<String> processedCommands) {

		if (command.equals("1")) {

			if (!processedCommands.contains("6")) {

				throw new ValidationException(DressUpOpType.FAIL.name());

			}

		}

	}

	@Override
	public void validateAllClothingsOnBeforeLeaving(String command, List<String> processedCommands) {

		// You cannot leave the house until all items of clothing are on
		if (command.equals("7")) {

			// jacket or boots not on
			if (!processedCommands.contains("3") || !processedCommands.contains("5")) {

				throw new ValidationException(DressUpOpType.FAIL.name());

			}

			super.validateAllClothingsOnBeforeLeaving(command, processedCommands);

		}

	}

}
