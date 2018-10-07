package com.zenjob.bookrecommendation.service.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ServiceMethodLoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceMethodLoggingAspect.class);

    public ServiceMethodLoggingAspect() {
    }

    //region Public methods
    @Around("execution(public * com.wibas.cinema.service..* (..))")
    public Object around(final ProceedingJoinPoint point) throws Throwable {
        final long start = System.currentTimeMillis();
        final Object result = point.proceed();
        LOGGER.debug(
                "#{}.{}({}): {} in {}ms",
                point.getSignature().getDeclaringType().getSimpleName(),
                point.getSignature().getName(),
                point.getArgs(),
                result,
                System.currentTimeMillis() - start
        );
        return result;
    }

    @AfterThrowing(value = "execution(public * com.trade.service..* (..))",
            throwing = "e")
    public void afterThrowing(final JoinPoint point, final Exception e) {
        LOGGER.error(
                "#{}.{}({}):",
                point.getSignature().getDeclaringType().getSimpleName(),
                point.getSignature().getName(),
                point.getArgs(),
                e
        );
    }
    //endregion
}
