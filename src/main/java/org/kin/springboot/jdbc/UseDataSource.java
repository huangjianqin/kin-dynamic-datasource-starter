package org.kin.springboot.jdbc;

import java.lang.annotation.*;

/**
 * @author huangjianqin
 * @date 2022/9/17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface UseDataSource {
    /** 使用的数据源key */
    String value();
}
