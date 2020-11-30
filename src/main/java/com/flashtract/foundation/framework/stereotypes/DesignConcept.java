package com.flashtract.foundation.framework.stereotypes;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * Spring annotation for common pattern classes
 *
 * @author blanclabs
 * @version 1.0
 * @since 2018-12-07
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface DesignConcept {
}
