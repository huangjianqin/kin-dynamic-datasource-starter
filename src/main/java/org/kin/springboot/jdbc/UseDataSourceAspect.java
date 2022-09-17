package org.kin.springboot.jdbc;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Objects;

/**
 * 切换数据源切面
 * @author huangjianqin
 * @date 2022/9/17
 */
@Aspect
public class UseDataSourceAspect {
    @Pointcut("@annotation(org.kin.springboot.jdbc.UseDataSource)")
    public void useDataSourcePointCut() {

    }

    @Around("useDataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //取指定的数据源key
        String dataSourceKey = null;
        UseDataSource useDataSourceAnno = getUseDataSourceAnno(joinPoint);
        if (Objects.nonNull(useDataSourceAnno)) {
            dataSourceKey = useDataSourceAnno.value();
        }

        DynamicDataSourceContextHolder.setDataSourceKey(dataSourceKey);

        try {
            return joinPoint.proceed();
        } finally {
            DynamicDataSourceContextHolder.removeDataSourceKey();
        }
    }

    /**
     * 从切面获取{@link UseDataSource}注解信息
     */
    private UseDataSource getUseDataSourceAnno(ProceedingJoinPoint joinPoint) {
        Class<?> claxx = joinPoint.getTarget().getClass();
        //class指定的数据源
        UseDataSource target = claxx.getAnnotation(UseDataSource.class);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if (Objects.nonNull(methodSignature)) {
            //如果方法指定了数据源, 则覆盖class域指定的数据源
            target = methodSignature.getMethod().getAnnotation(UseDataSource.class);
        }

        return target;
    }
}
