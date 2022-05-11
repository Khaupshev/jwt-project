package org.example.jwt.controller;

import org.example.jwt.config.WebConfig;
import org.example.jwt.controller.interceptor.AdminTokenInterceptor;
import org.example.jwt.dto.UserDto;
import org.example.jwt.security.JwtTokenFilter;
import org.example.jwt.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenFilter jwtTokenFilter;

    @MockBean
    private AdminTokenInterceptor adminTokenInterceptor;

    @MockBean
    private WebConfig webConfig;

    @Test
    void testUsers() throws Exception {
        mock();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/admin/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private void mock() {
        Mockito.doNothing().when(webConfig).addInterceptors(Mockito.any());
        Mockito.when(jwtTokenFilter.doFilter(Mockito.any(), Mockito.any()))
                .thenReturn(true);
        Mockito.when(adminTokenInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(true);
        Mockito.when(userService.getAllUsers())
                .thenReturn(List.of(new UserDto()));
    }

}
