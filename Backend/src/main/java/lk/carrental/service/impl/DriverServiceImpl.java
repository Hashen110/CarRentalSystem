package lk.carrental.service.impl;

import lk.carrental.dto.DriverDto;
import lk.carrental.dto.ScheduleDto;
import lk.carrental.entity.Booking;
import lk.carrental.entity.Driver;
import lk.carrental.repo.BookingRepo;
import lk.carrental.repo.DriverRepo;
import lk.carrental.service.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public boolean addDriver(DriverDto driverDto) {
        driverDto.setStatus("available");
        Driver map = modelMapper.map(driverDto, Driver.class);
        map.setId(UUID.randomUUID().toString());
        map.setTimestamp(LocalDateTime.now());
        map.setPassword(Base64.getEncoder().encodeToString(map.getPassword().getBytes()));
        driverRepo.save(map);
        return true;
    }

    @Override
    public boolean deleteDriver(String id) {
        Optional<Driver> byId = driverRepo.findById(id);
        if (byId.isPresent()) {
            Driver driver = byId.get();
            if (driver.getStatus().equals("working") | driver.getStatus().equals("Working")) {
                throw new RuntimeException("Can't delete driver. Driver is currently working");
            }
            driverRepo.delete(driver);
            return true;
        }
        throw new RuntimeException("No Driver found - " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleDto> getSchedule(String id) {
        Optional<Driver> byId = driverRepo.findById(id);
        if (byId.isPresent()) {
            Driver driver = byId.get();
            List<Booking> bookings = bookingRepo.getBookingsByDriver(driver);
            if (bookings == null | bookings.size() == 0) return null;
            List<ScheduleDto> schedules = new ArrayList<>();
            for (Booking booking : bookings) {
                schedules.add(new ScheduleDto(booking.getId(), booking.getUser().getUsername(), booking.getCars().get(0).getBrand(),
                        booking.getPickupLocation(), booking.getPickupTime(), booking.getDestination(), booking.getDestinationTime(), booking.getStatus()));
            }
            return schedules;
        }
        throw new RuntimeException("No Driver found - " + id);
    }
}
