package com.rewardsnetwork.util.impl;

import com.rewardsnetwork.util.ICommandSplitter;

// Stateless Singletons
public class CommandSplitter implements ICommandSplitter {

	private static final String DELIMETER = ",(\\s)*|(\\s)+";

	public CommandSplitter() {
	}

	@Override
	public String[] splitToArray(String commandText) {

		return commandText	.trim()
							.split(DELIMETER);

	}

}
