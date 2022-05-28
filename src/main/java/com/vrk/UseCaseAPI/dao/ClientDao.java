package com.vrk.UseCaseAPI.dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vrk.UseCaseAPI.entity.Client;
import com.vrk.UseCaseAPI.excption.SQLErrorException;
import com.vrk.UseCaseAPI.rowmapper.ClientMapper;

@Repository
public class ClientDao {

	Logger logger = LoggerFactory.getLogger(ClientDao.class);
    
	 @Autowired
	 JdbcTemplate jdbcTemplate;
	 
	 public Client getClientUsingToken(String token) throws SQLErrorException {
	        String query = "select * from client_details where auth_token= ?";
	        Client client = null;
	        try {
	            client = jdbcTemplate.queryForObject(query, new ClientMapper(), token);
	            return client;
	        } catch (Exception e) {
	            logger.warn(e.getMessage());
	            return null;
	        }

	    }

}
