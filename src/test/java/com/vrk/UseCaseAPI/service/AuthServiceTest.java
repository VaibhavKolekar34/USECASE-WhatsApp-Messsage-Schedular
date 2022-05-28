package com.vrk.UseCaseAPI.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.vrk.UseCaseAPI.dao.ClientDao;
import com.vrk.UseCaseAPI.entity.Client;
import com.vrk.UseCaseAPI.excption.SQLErrorException;

@SpringBootTest
public class AuthServiceTest {
	
	@Autowired
    AuthService authService;


    @MockBean
    ClientDao clientDao;


    @Test
    void validateToken() throws SQLErrorException {
        Client testClient = new Client(1, "sbi", "sbisbisbi");
        when(clientDao.getClientUsingToken("sbisbisbi")).thenReturn(testClient);
        assertThat(authService.validateToken("sbisbisbi")).isEqualTo(testClient);
    }


    @Test
    void validateTokenAsInvalid() throws SQLErrorException {
        when(clientDao.getClientUsingToken("Invalid Token")).thenReturn(null);
        assertThat(authService.validateToken("Invalid Token")).isNull();
    }
}
