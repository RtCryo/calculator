package org.example.controller;

import org.example.model.Role;
import org.example.service.ExpressionDaoService;
import org.example.service.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminController.class)
@ContextConfiguration(classes = {TestAdminController.Config.class,
        WithMockCustomUserSecurityContextFactory.class})
public class TestAdminController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExpressionDaoService expressionDaoService;

    @Test
    public void adminControllerUnauthenticated() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockCustomUser(roles = Role.USER)
    public void adminControllerAuthenticatedAsRoleUser() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().is5xxServerError());
        this.mockMvc.perform(get("/admin/expressionsList")).andExpect(status().is5xxServerError());
        this.mockMvc.perform(post("/admin/expressionsToDelete")).andExpect(status().is5xxServerError());
        Mockito.verify(expressionDaoService, Mockito.times(0)).listToView(0);
    }

    @Test
    @WithMockCustomUser(roles = Role.DEVELOPER)
    public void adminControllerAuthenticatedAsRoleDeveloper() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().isOk());
        this.mockMvc.perform(get("/admin/expressionsList")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(post("/admin/expressionsToDelete")).andExpect(status().is5xxServerError());
        Mockito.verify(expressionDaoService).listToView(0);
    }

    @Test
    @WithMockCustomUser(roles = Role.ADMIN)
    public void adminControllerAuthenticatedAsRoleAdmin() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().isOk());
        this.mockMvc.perform(get("/admin/expressionsList")).andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(post("/admin/expressionsToDelete")).andExpect(status().is5xxServerError());
        Mockito.verify(expressionDaoService).listToView(0);
    }

    @Test
    @WithMockCustomUser(roles = Role.SUPER_ADMIN)
    public void adminControllerAuthenticatedAsRoleSuperAdmin() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().isOk());
        this.mockMvc.perform(get("/admin/expressionsList")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(post("/admin/expressionsToDelete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"id\":\"18\",\"expressionList\":\"9+1\",\"result\":\"10\"}]"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        Mockito.verify(expressionDaoService, Mockito.times(2)).listToView(0);
    }

    @TestConfiguration
    public static class Config{

        @MockBean
        @Qualifier(value = "myUserDetailsService")
        public MyUserDetailsService myUserDetailsService;

        @MockBean
        public ExpressionDaoService expressionDaoService;

    }

}
