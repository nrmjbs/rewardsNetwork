package com.rewardsnetwork.validator;

import java.util.List;

public interface IHotWeatherDressUpRuleValidator extends IWeatherDressUpRuleValidator {

	void validateNoSocksOrJacket(String command);

	void validateShortsOnBeforeSandals(String command, List<String> processedCommands);

	void validateShirtOnBeforeHeadwear(String command, List<String> processedCommands);

}
