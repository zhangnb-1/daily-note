package com.ningboz.mylearnproject.buddy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE) // 仅在源码阶段保留
public @interface DynamicMethod {
    String methodName();
    Class<?> returnType() default void.class;
    Class<?>[] parameterTypes() default {};
}