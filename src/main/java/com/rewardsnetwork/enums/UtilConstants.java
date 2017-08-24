package com.rewardsnetwork.enums;

public enum UtilConstants {

	EMPTY(""), 
	
	QUOMA_SPACE_DELIMETER(", ");

	String value;

	private UtilConstants(String value) {

		this.value = value;

	}

	public String getValue() {

		return this.value;

	}

}
