package com.vrk.UseCaseAPI.dao;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.vrk.UseCaseAPI.entity.Client;
import com.vrk.UseCaseAPI.entity.msg;
import com.vrk.UseCaseAPI.entity.request;
import com.vrk.UseCaseAPI.excption.SQLErrorException;
import com.vrk.UseCaseAPI.rowmapper.MessageMapper;


@Component
public class MessageDao {
	Logger logger = LoggerFactory.getLogger(MessageDao.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int save(request requestBody,Client client) throws SQLErrorException  {
		int result = 0;
		String query = "insert into message_details(msg,scheduled_at,destination,client_id,created_at,scheduled_status) values (?,?,?,?,?,?)";
		try {
			result = jdbcTemplate.update(query, requestBody.getMessage(), requestBody.getScheduledTime(),
					requestBody.getPhonenumber(), client.getClient_id(), LocalDateTime.now(), true);
			return result;
		} catch (Exception e) {
			throw new SQLErrorException("error while doing sql operation");
		}

	}
	
	 public List<msg> getAllMessages() throws SQLErrorException {
		 
      String query = "select * from message_details where scheduled_status = true and scheduled_at <= now() and scheduled_at > created_at";

      List<msg> messages = Collections.emptyList();
      logger.info("Pooling messages from DB at" + LocalDateTime.now());
      try {
          messages = jdbcTemplate.query(query, new MessageMapper());
          return messages;
      } catch (Exception e) {
          logger.warn(e.getMessage());
          throw new SQLErrorException("Error while pooling messages from DB");
      }
  }

	
	 public int updateMessageStatus(Boolean scheduled_status,Boolean submitted_status, String whatsAppMessageId,Integer msg_id){

			String query ="UPDATE message_details set scheduled_status = ?, submitted_status=?,whatsapp_api_message_id=? where msg_id = ?";
			System.out.println("updating DB status for msg_id " + msg_id);
			int result = 0;
			try
			{
				result = jdbcTemplate.update(query,scheduled_status,submitted_status,whatsAppMessageId,msg_id);
				return result;
			}catch (Exception e){
				return 0;
			}
			
			
	
	 }
}