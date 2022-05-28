package com.vrk.UseCaseAPI.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vrk.UseCaseAPI.entity.Client;
import com.vrk.UseCaseAPI.excption.SQLErrorException;

@SpringBootTest
public class ClientDaoTest {
	@Autowired
    ClientDao clientDao;


    Logger logger = LoggerFactory.getLogger(ClientDaoTest.class);

	
    @Test
    void testGetClientUsingToken() throws SQLErrorException {

        String token="sbisbisbi";
        Client expectedClient = new Client(1, "SBI", "sbisbisbi");
		Client actualResult = clientDao.getClientUsingToken(token);
		assertThat(actualResult.toString()).isEqualTo(expectedClient.toString()); 
		  
    }
    
    @Test
    void getClientUsingTokenInvalid() {
        Client actualResult = null;
        try {
            actualResult = clientDao.getClientUsingToken("Authentication failed");
            System.out.println("actualresult " + actualResult);
        } catch (SQLErrorException e) {
            logger.info(e.getMessage());
            assertThat(actualResult).isEqualTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
		 

}
