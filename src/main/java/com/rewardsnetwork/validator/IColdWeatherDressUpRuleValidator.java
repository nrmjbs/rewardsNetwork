package com.rewardsnetwork.validator;

import java.util.List;

public interface IColdWeatherDressUpRuleValidator extends IWeatherDressUpRuleValidator {

	void validateSocksPantsOnBeforeShoes(String command, List<String> processedCommands);

	void validateShirtOnBeforeHeadwearOrJacket(String command, List<String> processedCommands);

}
