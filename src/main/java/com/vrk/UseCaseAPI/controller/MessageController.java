package com.vrk.UseCaseAPI.controller;


import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vrk.UseCaseAPI.entity.Client;
import com.vrk.UseCaseAPI.entity.request;
import com.vrk.UseCaseAPI.entity.response;
import com.vrk.UseCaseAPI.excption.SQLErrorException;
import com.vrk.UseCaseAPI.service.Messageservice;

@RestController
public class MessageController {
	
	Logger logger = LoggerFactory.getLogger(MessageController.class);
	@Autowired
    Messageservice messageservice;


	 @GetMapping("/check")
	    public String getTest() {
	        return "Working fine";
	    }
	
	@PostMapping("/sendMsg")
	public response MessageHandler(@RequestBody request requestBody, HttpServletRequest hr) {
		response r = null;
		String regx = "[9]{1}[1]{1}[6-9]{1}[0-9]{9}";
		LocalDateTime sd=LocalDateTime.parse(requestBody.getScheduledTime().replace(" ", "T"));
		
		 try {
	            Client client = (Client) hr.getAttribute("client");
	            System.out.println("client_details: " + client);
	            if(requestBody.getMessage().isEmpty() || requestBody.getPhonenumber().isEmpty() || requestBody.getScheduledTime().isEmpty() )
	            {
	            	r = new response(5002, "Validation Failed message/phonenumber/scheduledTime should not be empty");
	            	System.out.println("validation Failed message/phonenumber/scheduledTime should not be empty");
	            	return r;
	            }
	            else if(!requestBody.getPhonenumber().matches(regx))
	            {
	            	r = new response(5002, "Validation Failed phonenumber format is wrong");
	            	System.out.println("Validation Failed phonenumber format is wrong");
	            	return r;
	            }
	            else if(sd.isBefore(LocalDateTime.now()))
	            {
	            	r = new response(5002, "Validation Failed scheduled time should be greater than cuurent time");
	            	System.out.println("Validation Failed scheduled time should be greater than cuurent time");
	            	return r;
	            }
	            else
	            {
	            messageservice.saveMessage(requestBody, client);
	            r = new response(200, "OK, message scheduled");
	            }

	        } catch (SQLErrorException e) {
	            logger.warn("Sql Exception Occured");
	            r = new response(e.getErrorCode(), e.getErrorMessage());
	        } catch (Exception e) {
	            logger.warn("exception here " + e.getMessage());
	            r = new response(5003, "Something went wrong");
	        }
	        return r;

	    }
	

}
