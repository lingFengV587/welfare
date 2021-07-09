package com.mischievous.elf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: FieldName
 * @Description: 字段中文名
 * @author lifang
 * @version 1.0
 * @CreateDate 2020/1/14
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldName {
    String value();
}
