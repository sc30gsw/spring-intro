package com.example.demo.domain.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class MeetingRoomCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer roomId;

	private String roomName;

	private Integer capacity;

}
