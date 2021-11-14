package org.example.controller;

import org.example.model.Role;
import org.example.service.ExpressionDaoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminController.class)
@ContextConfiguration(classes = {
        TestConfig.class,
        WithMockCustomUserSecurityContextFactory.class})
class TestAdminController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExpressionDaoService expressionDaoService;

    @Test
    void adminControllerUnauthenticated() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockCustomUser(roles = Role.USER)
    void adminControllerAuthenticatedAsRoleUser() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().is5xxServerError());
        this.mockMvc.perform(delete("/admin/delete")).andExpect(status().is5xxServerError());
        Mockito.verify(expressionDaoService, Mockito.times(0)).listToView(0);
    }

    @Test
    @WithMockCustomUser(roles = Role.DEVELOPER)
    void adminControllerAuthenticatedAsRoleDeveloper() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(delete("/admin/delete")).andExpect(status().is5xxServerError());
        Mockito.verify(expressionDaoService).listToView(0);
    }

    @Test
    @WithMockCustomUser(roles = Role.ADMIN)
    void adminControllerAuthenticatedAsRoleAdmin() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(delete("/admin/delete")).andExpect(status().is5xxServerError());
        Mockito.verify(expressionDaoService).listToView(0);
    }

    @Test
    @WithMockCustomUser(roles = Role.SUPER_ADMIN)
    void adminControllerAuthenticatedAsRoleSuperAdmin() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(delete("/admin/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"id\":\"18\",\"expressionList\":\"9+1\",\"result\":\"10\"}]"))
                .andExpect(status().isOk());
        Mockito.verify(expressionDaoService, Mockito.times(2)).listToView(0);
    }

}
