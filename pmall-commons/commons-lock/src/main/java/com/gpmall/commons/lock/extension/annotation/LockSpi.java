package com.pmall.commons.lock.extension.annotation;

import java.lang.annotation.*;

/**
 * LOCK spi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LockSpi {

    /**
     * default extension name
     */
    String value() default "";
}
