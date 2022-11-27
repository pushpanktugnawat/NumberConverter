package in.push.domain.service;


import in.push.domain.enums.NumberType;
import in.push.port.inbound.NumberToRomanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NumberToRomanServiceImplTest {

    private NumberToRomanServiceImpl underTest;

    @BeforeEach
    void setup(){
        underTest = new NumberToRomanServiceImpl();
    }

    @Test
    void should_convert_number_to_roman(){
        String output = underTest.convertNumberToRoman("101", NumberType.NUMBER);
        Assertions.assertEquals("CI", output);
    }

    @Test
    void should_convert_binary_to_roman(){
        String output = underTest.convertNumberToRoman("101", NumberType.BINARY);
        Assertions.assertEquals("V", output);
    }

    @Test
    void should_convert_hex_to_roman(){
        String output = underTest.convertNumberToRoman("101", NumberType.HEX);
        Assertions.assertEquals("CCLVII", output);
    }

    @Test
    void shouldnot_convert_number_to_roman(){
        String input = "4001";
        Assertions.assertThrows(IllegalArgumentException.class, () -> underTest.convertNumberToRoman(input,
            NumberType.NUMBER));
    }
}
