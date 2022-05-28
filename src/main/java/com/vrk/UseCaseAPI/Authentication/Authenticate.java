package com.vrk.UseCaseAPI.Authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vrk.UseCaseAPI.dao.ClientDao;
import com.vrk.UseCaseAPI.entity.Client;
import com.vrk.UseCaseAPI.excption.SQLErrorException;

@Service
public class Authenticate {

    Logger logger = LoggerFactory.getLogger(Authenticate.class);
    @Autowired
    ClientDao clientDao;


    public Client validateToken(String token) throws SQLErrorException {
        Client client = clientDao.getClientUsingToken(token);
        return client;
    }
}
