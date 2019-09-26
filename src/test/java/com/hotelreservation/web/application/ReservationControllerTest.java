package com.hotelreservation.web.application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hotelreservation.business.domain.RoomReservation;
import com.hotelreservation.business.service.ReservationService;
import com.hotelreservation.web.application.ReservationController;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)	//This lets Spring get involved in testing our application
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {
	
	@MockBean
	private ReservationService reservationService;
	@Autowired
	private MockMvc mockMvc;
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void getReservations() throws Exception{
		Date date = DATE_FORMAT.parse("2019-01-01");
		List<RoomReservation> mockReservationList = new ArrayList<>();
		RoomReservation mockRoomReservation = new RoomReservation();
		mockRoomReservation.setLastName("Test");
		mockRoomReservation.setFirstName("JUnit");
		mockRoomReservation.setDate(date);
		mockRoomReservation.setGuestId(1);
		mockRoomReservation.setRoomNumber("J1");
		mockRoomReservation.setRoomId(100);
		mockRoomReservation.setRoomName("JUnit Room");
		mockReservationList.add(mockRoomReservation);
		
		given(reservationService.getRoomReservationsForDate("2019-01-01")).willReturn(mockReservationList);
		this.mockMvc.perform(get("/reservations?date=2019-01-01")).andExpect(status().isOk()).andExpect(content().string(containsString("Test, JUnit")));
		
	}

}
