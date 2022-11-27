package in.push.domain.service;

import in.push.domain.enums.NumberType;
import in.push.port.inbound.NumberToRomanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NumberToRomanServiceImpl implements NumberToRomanService {

    private final static String[] thousands = { "", "M", "MM", "MMM" };
    private final static String[] hundreds = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
    private final static String[] tens = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
    private final static String[] units = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };

    private static String convertNumberToRoman(@NonNull int input) {
        if(input > 4000) {
            throw new IllegalArgumentException("Not Possible to convert");
        }
        int numberOfThousands = input / 1000;
        int numberOfHundreds = (input / 100) % 10;
        int numberOfTens = (input / 10) % 10;
        int numberOfUnits = input % 10;
        return thousands[numberOfThousands] + hundreds[numberOfHundreds]
            + tens[numberOfTens] + units[numberOfUnits];
    }

    @Override
    public String convertNumberToRoman(String number, NumberType numberType) {
        log.debug("convertNumberToRoman {} , {}", number, numberType);
        switch (numberType){
            case HEX -> {
                int inputNumber = Integer.parseInt(number, 16);
                return convertNumberToRoman(inputNumber);
            }
            case BINARY -> {
                int inputNumber = Integer.parseInt(number, 2);
                return convertNumberToRoman(inputNumber);
            }
            default -> {
                int inputNumber = Integer.parseInt(number);
                return  convertNumberToRoman(inputNumber);
            }
        }
    }
}
