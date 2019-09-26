package com.hotelreservation.web.application;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotelreservation.business.domain.RoomReservation;
import com.hotelreservation.business.service.ReservationService;

@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {
	@Autowired
	private ReservationService reservationService;

	@RequestMapping(method = RequestMethod.GET)
	public String getReservations(@RequestParam(value = "date", required = false) String dateString, Model model) {
		List<RoomReservation> roomReservationList = this.reservationService.getRoomReservationsForDate(dateString);
		model.addAttribute("roomReservations", roomReservationList);

		// "reservations" below will be translated by the ThymeLeaf engine into the name
		// of a template(reservations.html)
		return "reservations"; //
	}

}