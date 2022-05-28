package com.vrk.UseCaseAPI.dao;


import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vrk.UseCaseAPI.entity.Client;
import com.vrk.UseCaseAPI.entity.request;
import com.vrk.UseCaseAPI.excption.SQLErrorException;

@SpringBootTest
public class MessageDaoTest {
	
	@Autowired
    MessageDao messageDao;

    Logger logger = LoggerFactory.getLogger(MessageDaoTest.class);
   
    @Test
    void testSave() throws SQLErrorException {
    	
    	Client exceptresult = new Client(1,"SBI","sbisbisbi");
        request r= new request("Hi2 message to enduser", "919890403168", "2022-05-26 09:30:00");
        int actualResult = messageDao.save(r,exceptresult);
        assertEquals(1,actualResult);

    }
    
    private void assertEquals(int i, int actualResult) {}

    @Test
    void testUpdateMessageStatus() {
    	int actualResult =  messageDao.updateMessageStatus(false,true,"whatsapp_id from gupshup api",10);
        assertEquals(1,actualResult);
    }
    
    
    @AfterEach
    void tearDown()
    {
    	System.out.println("tearing Down");
    }
    
    @BeforeEach
    void setUp()
    {
    	System.out.println("Setting Up");

    }
    
   
}
