package in.push.port.inbound;

import in.push.domain.enums.NumberType;

public interface NumberToRomanService {

    String convertNumberToRoman(String number, NumberType numberType);
}
