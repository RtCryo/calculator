package org.example.controller;

import org.example.dao.UsersRepository;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminController.class)
@ContextConfiguration(classes = {TestAdminController.Config.class})
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
    @WithAnonymousUser
    public void getAdminUnauthenticated() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().is3xxRedirection());
    }

    @Test
    public void getAdminAuthenticated() throws Exception {
        for(String permission : permissionsAdmin) {
            SecurityContextHolder.getContext().setAuthentication(new PreAuthenticatedAuthenticationToken(null, null, Arrays.asList(new SimpleGrantedAuthority(permission))));
            this.mockMvc.perform(get("/admin")).andExpect(status().isOk());
        }
    }

//    @Test
//    //@WithMockUser
//    public void getAdminAuthenticated() throws Exception {
//        SecurityContextHolder.getContext().setAuthentication(new PreAuthenticatedAuthenticationToken(null, null, Arrays.asList(new SimpleGrantedAuthority("admin:read"))));
//        this.mockMvc.perform(get("/admin")).andExpect(status().isOk());
//    }

    @Test
    @WithMockUser(authorities = {"user:read"})
    public void getAdminAuthenticatedAsUser() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().is5xxServerError());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"admin:read"})
    public void getAdminListAuthenticatedAsAdmin() throws Exception {
        this.mockMvc.perform(get("/admin/expressionsList")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "user", authorities = {"user:read"})
    public void getAdminListAuthenticatedAsUser() throws Exception {
        this.mockMvc.perform(get("/admin/expressionsList")).andExpect(status().is5xxServerError());
    }

    @TestConfiguration
    static class Config{

        @MockBean
        @Qualifier(value = "myUserDetailsService")
        private MyUserDetailsService myUserDetailsService;

        @MockBean
        private ExpressionDaoService expressionDaoService;

        @MockBean
        private UsersRepository usersRepository;

    }

}
