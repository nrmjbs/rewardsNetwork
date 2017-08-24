package com.rewardsnetwork.enums;

public enum DressUpOpType {

	FAIL("10", "fail"),

	BOOTS("1", "boots"),

	SANDALS("1", "sandals"),

	HAT("2", "hat"),

	SUNGLASSES("2", "sunglasses"),

	SOCKS("3", "socks"),

	SHIRT("4", "shirt"),

	JACKET("5", "jacket"),

	PANTS("6", "pants"),

	SHORTS("6", "shorts"),

	LEAVING_HOUSE("7", "leaving house"),

	REMOVING_PJS("8", "Removing PJs");

	private String opCode;

	private String opDescription;

	private DressUpOpType(String opCode, String opDescription) {

		this.opCode = opCode;

		this.opDescription = opDescription;

	}

	public String getOpCode() {

		return this.opCode;

	}

	public String getOpDescription() {

		return this.opDescription;

	}

}
