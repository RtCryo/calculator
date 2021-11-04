package org.example.controller;

import org.example.model.Role;
import org.example.service.ExpressionDaoService;
import org.example.service.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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

    @Value("${permissions.list.user}")
    private List<String> permissionsUser;

    @Value("${permissions.list.developer}")
    private List<String> permissionsDeveloper;

    @Value("${permissions.list.admin}")
    private List<String> permissionsAdmin;

    @Test
    public void getAdminUnauthenticated() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockCustomUser(roles = Role.USER)
    public void getAdminAuthenticatedAsRoleUser() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().is5xxServerError());
        this.mockMvc.perform(get("/admin/expressionsList")).andExpect(status().is5xxServerError());
        this.mockMvc.perform(get("/admin/expressionsToDelete")).andExpect(status().is5xxServerError());
    }

    @Test
    @WithMockCustomUser(roles = Role.DEVELOPER)
    public void getAdminAuthenticatedAsRoleDeveloper() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().isOk());
        this.mockMvc.perform(get("/admin/expressionsList")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/admin/expressionsToDelete")).andExpect(status().is5xxServerError());

    }

    @Test
    @WithMockCustomUser(roles = Role.ADMIN)
    public void getAdminAuthenticatedAsRoleAdmin() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().isOk());
        this.mockMvc.perform(get("/admin/expressionsList")).andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/admin/expressionsToDelete")).andExpect(status().is5xxServerError());
    }

    @Test
    @WithMockCustomUser(roles = Role.SUPER_ADMIN)
    public void getAdminAuthenticatedAsRoleSuperAdmin() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().isOk());
        this.mockMvc.perform(get("/admin/expressionsList")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(post("/admin/expressionsToDelete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"id\":\"18\",\"expressionList\":\"9+1\",\"result\":\"10\"}]"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @TestConfiguration
    static class Config{

        @MockBean
        @Qualifier(value = "myUserDetailsService")
        private MyUserDetailsService myUserDetailsService;

        @MockBean
        private ExpressionDaoService expressionDaoService;

    }

}
