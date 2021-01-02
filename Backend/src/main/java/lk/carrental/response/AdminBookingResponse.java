package lk.carrental.response;

import lk.carrental.dto.BookingDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminBookingResponse {
    private long totalBookings;
    private long todayBookings;
    private long yesterdayBookings;
    private long weekBookings;
    private long monthBookings;
    private long bookingRequests;
    private List<BookingDto> pendingBookings;
    private List<BookingDto> bookings;
}
