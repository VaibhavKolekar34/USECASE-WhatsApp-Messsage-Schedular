package com.vrk.UseCaseAPI.config;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrk.UseCaseAPI.Authentication.Authenticate;
import com.vrk.UseCaseAPI.entity.Client;
import com.vrk.UseCaseAPI.entity.response;
@Component
public class AuthInterceptor implements HandlerInterceptor{

	@Autowired
	Authenticate authenticate;
	@Override
	
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	            throws Exception {
	        String token = request.getHeader("token");
	        System.out.println("Token is: " + token);
	        Client client = authenticate.validateToken(token);
	        if (client == null) {
	        	System.out.println("Authentication failed");
	            response.setContentType("application/json");
	            PrintWriter out = response.getWriter();
	            response resp = new response(5001, "Authentication failed");
	            String responseString = new ObjectMapper().writeValueAsString(resp);
	            out.print(responseString);
	            return false;
	        }
	        request.setAttribute("client", client);
	        return true;
	    }
	

}
