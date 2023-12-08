package com.njj.blog.common.response.sensitive;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记属性需要处理敏感数据
 *
 * @author Ni Jiajun njj1108@outlook.com
 * @since 1.0.0 2023-12-07
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SensitiveField {

    /**
     * 处理敏感数据的方式，默认置空
     */
    SensitiveFieldHandleMethod value() default SensitiveFieldHandleMethod.SET_NULL;

}
