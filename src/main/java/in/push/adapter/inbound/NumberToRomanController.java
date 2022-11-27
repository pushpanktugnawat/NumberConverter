package in.push.adapter.inbound;


import in.push.adapter.inbound.dto.NumberToRomanResponseData;
import in.push.audit.Audit;
import in.push.domain.enums.NumberType;
import in.push.port.inbound.NumberToRomanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NumberToRomanController {

    private final NumberToRomanService numberToRomanService;

    @GetMapping("/api/number-to-roman")
    @Audit
    public ResponseEntity<NumberToRomanResponseData> numberToRoman(@NonNull @RequestParam(value = "number") String number,
        @NonNull @RequestParam(value = "type")NumberType numberType){
        String romanNumber = numberToRomanService.convertNumberToRoman(number, numberType);
        return ResponseEntity.ok(new NumberToRomanResponseData(romanNumber, numberType));
    }

}
