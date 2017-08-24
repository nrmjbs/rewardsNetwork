package com.rewardsnetwork.builder.impl;

import com.rewardsnetwork.builder.IWeatherDressUpResponseBuilder;
import com.rewardsnetwork.enums.DressUpOpType;
import com.rewardsnetwork.enums.UtilConstants;

public class HotWeatherDressUpResponseBuilder implements IWeatherDressUpResponseBuilder {

	final StringBuilder dressUpResponseBuilder = new StringBuilder();

	@Override
	public void buildDressUpResponse(String command) {

		switch (command) {

		case "1":
			dressUpResponseBuilder	.append(UtilConstants.QUOMA_SPACE_DELIMETER.getValue())
									.append(DressUpOpType.SANDALS.getOpDescription());
			break;

		case "2":
			dressUpResponseBuilder	.append(UtilConstants.QUOMA_SPACE_DELIMETER.getValue())
									.append(DressUpOpType.SUNGLASSES.getOpDescription());
			break;

		case "4":
			dressUpResponseBuilder	.append(UtilConstants.QUOMA_SPACE_DELIMETER.getValue())
									.append(DressUpOpType.SHIRT.getOpDescription());
			break;

		case "6":
			dressUpResponseBuilder	.append(UtilConstants.QUOMA_SPACE_DELIMETER.getValue())
									.append(DressUpOpType.SHORTS.getOpDescription());
			break;

		case "7":
			dressUpResponseBuilder	.append(UtilConstants.QUOMA_SPACE_DELIMETER.getValue())
									.append(DressUpOpType.LEAVING_HOUSE.getOpDescription());
			break;

		case "8":
			dressUpResponseBuilder	.append(UtilConstants.QUOMA_SPACE_DELIMETER.getValue())
									.append(DressUpOpType.REMOVING_PJS.getOpDescription());
			break;

		default:
			dressUpResponseBuilder	.append(UtilConstants.QUOMA_SPACE_DELIMETER.getValue())
									.append(DressUpOpType.FAIL.getOpDescription());

		}

	}

	@Override
	public String getDressUpResponse() {

		return dressUpResponseBuilder.length() > 2
				? dressUpResponseBuilder.substring(2)
				: UtilConstants.EMPTY.getValue();

	}

}
