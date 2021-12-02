package org.example.controller;

import org.example.model.Role;
import org.example.model.Status;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {
    String username() default "rob";
    String name() default "Rob Winch";
    Status status() default Status.ACTIVE;
    Role roles() default Role.USER;
    boolean enabled() default true;
}
