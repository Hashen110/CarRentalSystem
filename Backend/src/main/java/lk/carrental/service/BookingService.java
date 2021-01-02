package lk.carrental.service;

import lk.carrental.dto.BookingDto;
import lk.carrental.dto.ReturnDto;

import java.util.List;

public interface BookingService {
    boolean addBooking(BookingDto bookingDto);

    List<BookingDto> getUserBookings(String id);

    boolean finishBooking(ReturnDto returnDto);
}
