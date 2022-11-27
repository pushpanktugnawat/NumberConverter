package in.push.adapter.inbound.dto;

import in.push.domain.enums.NumberType;

public record NumberToRomanResponseData(String romanNumber, NumberType numberType) {

}
