package in.push.audit.domain.service;

import in.push.adapter.inbound.dto.NumberToRomanResponseData;
import in.push.audit.aspect.Audit;
import in.push.domain.enums.NumberType;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditServiceImpl {

    @Around("@annotation(audit)")
    public Object addAudit(ProceedingJoinPoint pjp, Audit audit) throws Throwable {
        ResponseEntity retVal = (ResponseEntity) pjp.proceed();
        String inputNumber = (String) pjp.getArgs()[0];
        NumberType type = (NumberType) pjp.getArgs()[1];
        insertRecord(inputNumber, type, (NumberToRomanResponseData) retVal.getBody());
        return retVal;
    }

    private void insertRecord(String inputNumber, NumberType type, NumberToRomanResponseData body) throws IOException {
        try (Writer output = new BufferedWriter(new FileWriter("src/main/resources/audit_logs/Audit.csv", true))) {
            output.append(LocalDateTime.now() + "," + type + "," + inputNumber + "," + body.romanNumber() + "\n");
        }
    }
}
