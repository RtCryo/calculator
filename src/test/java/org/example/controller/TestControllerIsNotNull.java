package org.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestControllerIsNotNull {

    @Autowired
    private LoginController loginController;

    @Test
    void contextLoads() {
        assertThat(loginController).isNotNull();
    }

}
