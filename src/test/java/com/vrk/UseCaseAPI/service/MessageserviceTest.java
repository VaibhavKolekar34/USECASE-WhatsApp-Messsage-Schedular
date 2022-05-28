package com.vrk.UseCaseAPI.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.vrk.UseCaseAPI.dao.MessageDao;
import com.vrk.UseCaseAPI.entity.Client;
import com.vrk.UseCaseAPI.entity.msg;
import com.vrk.UseCaseAPI.entity.request;
import com.vrk.UseCaseAPI.excption.SQLErrorException;

@SpringBootTest
public class MessageserviceTest {
	
	@MockBean
    MessageDao messageDao;

    @Autowired
    Messageservice messageService;

    Logger logger = LoggerFactory.getLogger(MessageserviceTest.class);
    
    @Test
    void testPollMessagesFromDatabase() throws SQLErrorException {
    	List<msg> messageList = Collections.emptyList();
        when(messageDao.getAllMessages()).thenReturn(messageList);
        List<msg> actualList = messageService.pollMessagesFromDatabase();
        assertEquals(messageList.size(), actualList.size());
    }

    @Test
    void testSaveMessage() throws SQLErrorException {
    	Client dummyclient = new Client(1, "SBI", "sbisbisbi");
        request rq= new request("test message", "919890403168", "2022-05-26 11:40:00");
        when(messageDao.save(rq, dummyclient)).thenReturn(1);
        int actualResult = messageService.saveMessage(rq, dummyclient);
        assertThat(actualResult).isEqualTo(1);
    }

    @Test
    void testUpdateMessageStatus() throws SQLErrorException {
    	 when(messageDao.updateMessageStatus(any(), any(), any(), any())).thenReturn(1);
         int actualResult = messageService.updateMessageStatus(any(), any(), any(),  any());
         assertEquals(1, actualResult);
    }
    
    private void assertEquals(int i, int actualResult) {
	}
}
