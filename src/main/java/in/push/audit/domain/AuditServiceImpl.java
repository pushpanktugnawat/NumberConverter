package in.push.audit.domain;

import in.push.audit.Audit;
import in.push.domain.enums.NumberType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditServiceImpl {

    @Around("@annotation(audit)")
    public Object addMetric(ProceedingJoinPoint pjp, Audit audit) throws Throwable {
        ResponseEntity retVal = (ResponseEntity) pjp.proceed();
        String inputNumber = (String) pjp.getArgs()[0];
        NumberType type = (NumberType) pjp.getArgs()[1];
        System.out.println("input Number "+ inputNumber + "type "+ type + " output " + retVal.getBody());
        return retVal;
    }
}
