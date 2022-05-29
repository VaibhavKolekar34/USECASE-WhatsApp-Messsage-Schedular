package com.vrk.UseCaseAPI.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrk.UseCaseAPI.entity.request;
import com.vrk.UseCaseAPI.entity.response;
import com.vrk.UseCaseAPI.service.AuthService;
import com.vrk.UseCaseAPI.service.Messageservice;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {
	
	@MockBean
    Messageservice messageservice;
	
    @MockBean
    AuthService authService;
    
    HttpServletRequest hr;

    
    @InjectMocks
    MessageController msgC;
   
    @Autowired
    private MockMvc mvc;
	

    public static ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void testMessageHandler() throws Exception {
    
    	request request = new request("", "919890403168", "2022-06-28 18:45:20");    
    	String jsonString = objectMapper.writeValueAsString(request);           
    	MvcResult result = mvc.perform(
    			post("/sendMsg").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString).accept(MediaType.APPLICATION_JSON_VALUE
    					)).andReturn();
     	 
 		String actualResponseString = result.getResponse().getContentAsString();    
		response r = objectMapper.readValue(actualResponseString, response.class); 
		
		assertThat(r.getCode()).isEqualTo(5002);
	    	
    }
    
    
   
	@BeforeEach
    public void setup()
    {
    	mvc=MockMvcBuilders.standaloneSetup(msgC).build();
    }

    @Test
    void testGetTest() throws Exception {
    	
    	mvc.perform(
    			MockMvcRequestBuilders.get("/check")
    		).andExpect(MockMvcResultMatchers.status().isOk())
    		.andExpect(MockMvcResultMatchers.content().string("Working fine"));
    	}
    
        
    
}
