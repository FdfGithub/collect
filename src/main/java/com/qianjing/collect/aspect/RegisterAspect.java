package com.qianjing.collect.aspect;

import com.qianjing.collect.comm.Response;
import com.qianjing.collect.domain.User;
import com.qianjing.collect.dto.RegisterDto;
import com.qianjing.collect.exception.OutException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RegisterAspect {
    @Pointcut("execution(public com.qianjing.collect.comm.Response com.qianjing.collect.service.impl.UserServiceImpl.register(com.qianjing.collect.dto.RegisterDto))")
    private void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint jp) {
        Object[] args = jp.getArgs();
        if (args != null && args.length > 0 && args[0] instanceof RegisterDto) {
            RegisterDto dto = (RegisterDto) args[0];
            if (dto.getLocalCode() == null) {
                throw new OutException("验证码已经失效");
            }
            if (StringUtils.equals(dto.getLocalCode(), dto.getCode())) {
                return;
            }
        }
        throw new OutException("验证码错误");
    }
}
