package com.expriment.service.serviceImpl;

import com.expriment.utils.audit.LoggerClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import sun.security.validator.ValidatorException;

@Aspect
@Component
public class ProjectAspect {
    public static final Logger logger = LogManager.getLogger(PersonalDetailsServiceImpl.class);

    @Before(value = "execution(* com.expriment.utils.audit.DAO.Impl.AuditLogDataDAOImpl.*(..)) and args(alId)")
    public void beforeAdvice(JoinPoint joinPoint, Integer alId) throws ValidatorException{
        LoggerClass.appLayerLogger.info("Before method:" + joinPoint.getSignature());
        final Object arg[] = joinPoint.getArgs();
        final Object object = arg[0];
        final Integer payload = (Integer) object;

        if (payload != null ) {
            String name = object.getClass().getSimpleName();
            LoggerClass.appLayerLogger.info("Before method -name is : " + name);
           /* String resp = "SUCCESS";
            resp = validationUtil.validateClass(object, object.getClass().getSimpleName());
            if (resp.equalsIgnoreCase("SUCCESS")) {
                LoggerClass.appLayerLogger.info("validation info : " + resp);
            } else {
                throw new ValidatorException(resp);
            }*/

        }
        LoggerClass.appLayerLogger.info("End of Before method -Calling method - " + alId);
    }
    @After(value = "execution(* com.expriment.utils.audit.DAO.Impl.AuditLogDataDAOImpl.*(..)) and args(alId)" )
    public void afterAdvice(JoinPoint joinPoint, Integer alId) {
        LoggerClass.appLayerLogger.info("After method:" + joinPoint.getSignature());
        LoggerClass.appLayerLogger.info("Calling after method done - " + alId);
    }

    @Around(value = "execution(* com.expriment.utils.audit.DAO.Impl.AuditLogDataDAOImpl.*(..)) and args(alId)" )
    public Object aroundAdvice(ProceedingJoinPoint joinPoint,  Integer alId) {
        LoggerClass.appLayerLogger.info("The method aroundAdvice() before invokation of the method " + joinPoint.getSignature().getName() + " method");
        Object result = null;
        try{
           result=  joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            LoggerClass.appLayerLogger.info("around method finally block {}",alId);
        }
        LoggerClass.appLayerLogger.info("The method aroundAdvice() after invokation of the method " + joinPoint.getSignature().getName() + " method");
        return result;
    }

}
