package com.vrk.UseCaseAPI.TimerTask;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vrk.UseCaseAPI.entity.msg;
import com.vrk.UseCaseAPI.excption.SQLErrorException;
import com.vrk.UseCaseAPI.service.Messageservice;


@Component
public class CustomTimerTask extends TimerTask{
	

	@Autowired
	Messageservice messageService;

	public static String encodeParam(String data) {
		String result = "";
		try {
			result = URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static byte[] getParamsByte(Map<String, Object> params) {
		byte[] result = null;
		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0) {
				postData.append('&');
			}
			postData.append(encodeParam(param.getKey()));
			postData.append('=');
			postData.append(encodeParam(String.valueOf(param.getValue())));
		}
		try {
			result = postData.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public void run() 
	{
		//System.out.println("Timer Task is running after 10 sec continuosly");
		
		List<msg> messageList = null;
		
		 try {
	            messageList = messageService.pollMessagesFromDatabase();
	        } catch (SQLErrorException e) {
	            System.out.println(e.getMessage());
	            return;
	        }

	        if (messageList.isEmpty()) {
	            System.out.println("Messagelist is Empty");
	            return;
	        }

	    for (msg ms : messageList) {
		System.out.println("The message of msg_id "+ ms.getMessage_id() +" will be send on "+ ms.getScheduled_at());}
		Gson gson = new Gson();
		URL url = null;
		HttpURLConnection con = null;

		for (msg ms : messageList) {
			try {
				url = new URL("https://api.gupshup.io/sm/api/v1/msg");
				con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("POST");
				con.setUseCaches(false);
				con.setDoOutput(true);
				con.setDoInput(true);
				con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				con.setRequestProperty("apikey", "noehsbgasezeoec3o76t8zoii70wof73");
				con.setRequestProperty("Accept", "application/json");

				OutputStream outputStream = con.getOutputStream();

				HashMap<String, String> message = new HashMap<String, String>();
				message.put("type", "text");
				message.put("text", ms.getMessage());

				String jsonString = gson.toJson(message);
				JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
				Map<String, Object> body = new HashMap<>();
				body.put("channel", "whatsapp");
				body.put("source", "917834811114");
				body.put("destination", ms.getDestination_phone_number());
				body.put("message", jsonObject);
				body.put("src.name", "WMapps");

				outputStream.write(getParamsByte(body));
				int gupshup_code =con.getResponseCode();
				if (gupshup_code == HttpURLConnection.HTTP_ACCEPTED) {
					ObjectMapper objectMapper = new ObjectMapper();
					Map<String, String> response = objectMapper.readValue(con.getInputStream(), Map.class);
					System.out.println("whatsapp_api_message_id :" + response.get("messageId"));
					//System.out.println(response.toString());
					int result = messageService.updateMessageStatus(false,true, response.get("messageId"),ms.getMessage_id());
					if(result <1){
						System.out.println("Error occured while updating in DB");
					}else System.out.println("Status of meesages is updated for msg_id "+ ms.getMessage_id());
				} else 
				{
					messageService.updateMessageStatus(false,false, null,ms.getMessage_id());
					System.out.println("Message Sending Failed for msg_id " + ms.getMessage_id());
				}
			} catch (Exception e) {
				System.out.println("Exception while sending messages from gupshup API");
				e.printStackTrace();
			}
		}
		
	}
		
	

}
