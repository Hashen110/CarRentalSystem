package lk.carrental.repo;

import lk.carrental.entity.Booking;
import lk.carrental.entity.Driver;
import lk.carrental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, String> {
    List<Booking> getBookingsByUser(User user);

    List<Booking> getBookingsByDriver(Driver driver);

    List<Booking> getBookingsByStatus(String status);
}
