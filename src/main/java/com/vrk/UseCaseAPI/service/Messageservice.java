package com.vrk.UseCaseAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vrk.UseCaseAPI.dao.MessageDao;
import com.vrk.UseCaseAPI.entity.Client;
import com.vrk.UseCaseAPI.entity.msg;
import com.vrk.UseCaseAPI.entity.request;
import com.vrk.UseCaseAPI.excption.SQLErrorException;

@Service
public class Messageservice {

	@Autowired
	MessageDao eDAO;

	public Messageservice(MessageDao eDAO) {
        this.eDAO = eDAO;
    }

    public int saveMessage(request requestBody, Client client) throws SQLErrorException {
        return eDAO.save(requestBody, client);
    }

    public int updateMessageStatus(Boolean scheduled_status, Boolean submited_status, String whatsAppMessageId,Integer message_id) throws SQLErrorException {

        return eDAO.updateMessageStatus(scheduled_status, submited_status, whatsAppMessageId, message_id);
    }


    public List<msg> pollMessagesFromDatabase() throws SQLErrorException {
        return eDAO.getAllMessages();
    }


}
