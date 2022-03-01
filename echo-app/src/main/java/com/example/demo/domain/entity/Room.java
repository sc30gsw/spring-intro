package com.example.demo.domain.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "room")
@Data
public class Room implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name = "room_id")
	private Integer roomId;
	
	@Column(name = "room_name")
	private String roomName;
	
	@Column(name = "capasity")
	private Integer capasity;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private List<Equipment> equipments;

}
