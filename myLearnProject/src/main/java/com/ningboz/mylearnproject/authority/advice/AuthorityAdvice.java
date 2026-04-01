package com.ningboz.mylearnproject.authority.advice;

import com.ningboz.mylearnproject.authority.annotion.AuthorityCheck;
import com.ningboz.mylearnproject.authority.entity.ProjectAuthorityEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: AuthorityAdvice
 * @author: Znb
 * @date: 2025/10/13
 * @description:
 */
@Aspect
@Component
public class AuthorityAdvice {
    @Around("@annotation(authorityCheck)")
    public Object checkAuthority(ProceedingJoinPoint joinPoint, AuthorityCheck authorityCheck) throws Throwable {
        ProjectAuthorityEnum authority = authorityCheck.authority();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String projectId = request.getHeader("projectId");
        // 权限验证逻辑
        return joinPoint.proceed();
    }
}
