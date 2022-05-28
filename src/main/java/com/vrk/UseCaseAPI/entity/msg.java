package com.vrk.UseCaseAPI.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class msg{

    private Integer msg_id;
	private String msg;
	private String destination;
	private String scheduled_at;
	private Integer client_id;
	private String created_at;
	private boolean scheduled_status;
	private boolean submitted_status;
	private String whatsapp_api_message_id;
	
	public msg() {
		super();
	}
	
	
	public msg(Integer message_id, String message, String destination_phone_number, String scheduled_at,
			Integer client_id, String created_at, boolean scheduled_status,
			boolean submitted_status, String whatsapp_api_message_id) {
		super();
		this.msg_id = message_id;
		this.msg = message;
		this.destination = destination_phone_number;
		this.scheduled_at = scheduled_at;
		this.client_id = client_id;
		this.created_at = created_at;
		this.scheduled_status=scheduled_status;
		this.submitted_status = submitted_status;
		this.whatsapp_api_message_id = whatsapp_api_message_id;
	}


	public Integer getMessage_id() {
		return msg_id;
	}


	public void setMessage_id(int i) {
		this.msg_id = i;
	}


	public String getMessage() {
		return msg;
	}


	public void setMessage(String message) {
		this.msg = message;
	}


	public String getDestination_phone_number() {
		return destination;
	}


	public void setDestination_phone_number(String destination_phone_number) {
		this.destination = destination_phone_number;
	}


	public String getScheduled_at() {
		return scheduled_at;
	}


	public void setScheduled_at(String scheduled_at) {
		this.scheduled_at = scheduled_at;
	}


	public Integer getClient_id() {
		return client_id;
	}


	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}


	public String getCreated_at() {
		return created_at;
	}


	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}



	public boolean isSubmitted_status() {
		return submitted_status;
	}


	public void setSubmitted_status(boolean submitted_status) {
		this.submitted_status = submitted_status;
	}


	public String getWhatsapp_api_message_id() {
		return whatsapp_api_message_id;
	}


	public void setWhatsapp_api_message_id(String whatsapp_api_message_id) {
		this.whatsapp_api_message_id = whatsapp_api_message_id;
	}
	
	public Client validateToken(String token) {
		return null;
	}


	public boolean isScheduled_status() {
		return scheduled_status;
	}


	public void setScheduled_status(boolean scheduled_status) {
		this.scheduled_status = scheduled_status;
	}
	
	
	@Override
	public String toString() {
		return "Message [message_id=" + msg_id + ", message=" + msg + ", destination_phone_number="
				+ destination+ ", scheduled_at=" + scheduled_at + ", client_id=" + client_id
				+ ", created_at=" + created_at + ",pending_status=" + scheduled_status +  ", submitted_status=" + submitted_status + ", whatsapp_api_message_id="
				+ whatsapp_api_message_id + "]";
	}
	
	
	
}
