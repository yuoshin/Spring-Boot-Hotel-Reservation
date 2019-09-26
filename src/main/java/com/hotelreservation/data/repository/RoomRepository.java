package com.hotelreservation.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotelreservation.data.entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{
	//findByNumber() is a Spring Data naming convention
	//This will build Select * from ROOM WHERE ROOM_NUMBER=number
	Room findByNumber(String number);
	
}
