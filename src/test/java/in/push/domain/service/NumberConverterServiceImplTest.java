package in.push.domain.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NumberConverterServiceImplTest {

    private NumberConverterServiceImpl underTest;

    @BeforeEach
    public void setUp() {
        underTest = new NumberConverterServiceImpl();
    }

    @Test
    void should_convert_number_to_roman(){
        Integer input = 123;
        String romanNumber = underTest.convertNumberToRoman(input);
        Assertions.assertEquals("CXXIII", romanNumber);
    }
}
