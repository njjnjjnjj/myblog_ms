package com.njj.blog.common.response.sensitive;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用含敏感数据的类
 *
 * @author Ni Jiajun njj1108@outlook.com
 * @since 1.0.0 2023-12-07
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SensitiveData {

}
