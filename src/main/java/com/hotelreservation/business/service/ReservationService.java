package com.hotelreservation.business.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelreservation.business.domain.RoomReservation;
import com.hotelreservation.data.entity.Guest;
import com.hotelreservation.data.entity.Reservation;
import com.hotelreservation.data.entity.Room;
import com.hotelreservation.data.repository.GuestRepository;
import com.hotelreservation.data.repository.ReservationRepository;
import com.hotelreservation.data.repository.RoomRepository;

@Service
public class ReservationService {
	private final RoomRepository roomRepository;
	private final GuestRepository guestRepository;
	private final ReservationRepository reservationRepository;
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository,ReservationRepository reservationRepository) {
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
		this.reservationRepository = reservationRepository;
	}

	public List<RoomReservation> getRoomReservationsForDate(String dateString) {
		Date date = this.createDateFromDateString(dateString);
		// findAll() is a Spring Data function
		Iterable<Room> rooms = this.roomRepository.findAll();
		Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
		rooms.forEach(room -> {
			RoomReservation roomReservation = new RoomReservation();
			roomReservation.setRoomId(room.getId());
			roomReservation.setRoomName(room.getName());
			roomReservation.setRoomNumber(room.getNumber());
			roomReservationMap.put(room.getId(), roomReservation);
		});
		Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
		if (null != reservations) {
			reservations.forEach(reservation -> {
				Optional<Guest> guestResponse = this.guestRepository.findById(reservation.getGuestId());
				if (guestResponse.isPresent()) {
					Guest guest = guestResponse.get();
					RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
					roomReservation.setDate(date);
					roomReservation.setFirstName(guest.getFirstName());
					roomReservation.setLastName(guest.getLastName());
					roomReservation.setGuestId(guest.getId());
				}
			});
		}
		List<RoomReservation> roomReservations = new ArrayList<>();
		for (Long roomId : roomReservationMap.keySet()) {
			roomReservations.add(roomReservationMap.get(roomId));
		}
		return roomReservations;
	}
	
	private Date createDateFromDateString(String dateString) {
		Date date = null;
		if(null != dateString) {
			try {
				date= DATE_FORMAT.parse(dateString);
			}catch(ParseException pe) {
				date = new Date();
			}
		}else {
			date = new Date();
		}
		return date;
	}

}
