package com.vrk.UseCaseAPI.rowmapper;


import org.springframework.jdbc.core.RowMapper;

import com.vrk.UseCaseAPI.entity.msg;

import java.sql.ResultSet;
import java.sql.SQLException;
public class MessageMapper implements RowMapper<msg> {
    @Override
    public msg mapRow(ResultSet rs, int rowNum) throws SQLException {
        msg message = new msg();
        message.setMessage_id(rs.getInt("msg_id"));
        message.setMessage(rs.getString("msg"));
        message.setScheduled_at(rs.getString("scheduled_at"));
        message.setDestination_phone_number(rs.getString("destination"));
        message.setClient_id(rs.getInt("client_id"));
        message.setCreated_at(rs.getString("created_at"));
        message.setScheduled_status(rs.getBoolean("scheduled_status"));
        message.setSubmitted_status(rs.getBoolean("submitted_status"));
        message.setWhatsapp_api_message_id(rs.getString("whatsapp_api_message_id"));
        return message;
    }
}
