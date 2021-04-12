package com.qianjing.collect.aspect;

import com.qianjing.collect.annotation.Check;
import com.qianjing.collect.annotation.NotNull;
import com.qianjing.collect.exception.InException;
import com.qianjing.collect.exception.OutException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
public class ParamsAspect {
    @Pointcut("execution(* com.qianjing.collect.controller.*.*(..))")
    private void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint jp) {
        int i = 0;
        for (Object arg : jp.getArgs()) {
            if (arg instanceof Model){
                continue;
            }
            checkNull(arg, jp.getSignature().toString() + " [" + (i++) +"] ");
        }
    }

    private void checkNull(Object arg, String signature) {
        if (ObjectUtils.isEmpty(arg)){
            throw new InException("参数为空：" + signature);
        }
        //判断arg是否需要深度检查
        if (arg.getClass().getAnnotation(Check.class) != null){
            deepCheck(arg, signature);
        }
    }

    private void deepCheck(Object arg, String signature) {
        Class<?> clazz = arg.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(NotNull.class) != null) {
                field.setAccessible(true);
                try {
                    checkNull(field.get(arg), signature + field.getName());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
