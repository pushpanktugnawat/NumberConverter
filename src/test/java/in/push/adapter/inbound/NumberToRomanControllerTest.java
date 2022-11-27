package in.push.adapter.inbound;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import in.push.domain.enums.NumberType;
import in.push.port.inbound.NumberToRomanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class NumberToRomanControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NumberToRomanService numberToRomanService;

    @BeforeEach
    void setup(){
        NumberToRomanController underTest = new NumberToRomanController(numberToRomanService);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    void getFrontendVariables() throws Exception {

        when(numberToRomanService.convertNumberToRoman(any(), any())).thenReturn("VI");

        mockMvc
            .perform(get("/api/v1/number-to-roman").
                queryParam("number", "6").
                queryParam("type", String.valueOf(NumberType.NUMBER)))
            .andExpect(status().isOk());
    }

}