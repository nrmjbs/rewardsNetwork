package com.rewardsnetwork.validator;

import java.util.List;

public interface IWeatherDressUpRuleValidator {

	boolean isValid(String command, List<String> processedCommands);

	void validateCommandNotRepeated(String command, List<String> processedCommands);

	void validateAllClothingsOnBeforeLeaving(String command, List<String> processedCommands);

}
