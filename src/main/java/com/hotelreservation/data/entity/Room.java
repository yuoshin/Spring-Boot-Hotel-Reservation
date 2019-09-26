package com.hotelreservation.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROOM")
public class Room {
	@Id
	@Column(name="ROOM_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;		//Same attributes as ROOM schema
	@Column(name="NAME")
	private String name;	//Same attributes as ROOM schema
	@Column(name="ROOM_NUMBER")
	private String number;	//Same attributes as ROOM schema
	@Column(name="BED_INFO")
	private String bedInfo;	//Same attributes as ROOM schema
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBedInfo() {
		return bedInfo;
	}
	public void setBedInfo(String bedInfo) {
		this.bedInfo = bedInfo;
	}
	
}
