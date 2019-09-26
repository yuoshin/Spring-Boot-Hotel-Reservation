package com.hotelreservation.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hotelreservation.data.entity.Guest;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

}