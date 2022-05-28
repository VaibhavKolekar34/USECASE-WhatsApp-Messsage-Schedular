package com.vrk.UseCaseAPI.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.vrk.UseCaseAPI.service.AuthService;
import com.vrk.UseCaseAPI.service.Messageservice;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {
	
	@MockBean
    Messageservice messageservice;
	
    @MockBean
    AuthService authService;
    
    @InjectMocks
    MessageController msgC;
   
    @Autowired
    private MockMvc mvc;
	


    @Test
    void testMessageHandler() throws Exception {
    	
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
