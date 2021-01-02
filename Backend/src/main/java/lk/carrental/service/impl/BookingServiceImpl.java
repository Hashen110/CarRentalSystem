package lk.carrental.service.impl;

import lk.carrental.dto.BookingDto;
import lk.carrental.dto.PriceDto;
import lk.carrental.dto.ReturnDto;
import lk.carrental.entity.*;
import lk.carrental.repo.*;
import lk.carrental.service.BookingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ReturnRepo returnRepo;

    @Override
    public boolean addBooking(BookingDto bookingDto) {
        if (bookingDto.getUserId() == null || bookingDto.getUserId().equals(""))
            throw new RuntimeException("Invalid User Id");
        Optional<User> user = userRepo.findById(bookingDto.getUserId());
        if (!user.isPresent()) throw new RuntimeException("Invalid User Id - User Not Found");
        if (bookingDto.getCarId() == null | bookingDto.getCarId().equals("")) throw new RuntimeException("Invalid Car");
        Optional<Car> byId = carRepo.findById(bookingDto.getCarId());
        if (byId.isPresent()) {
            bookingDto.getCars().add(byId.get());
        } else {
            throw new RuntimeException("Invalid Car");
        }
        if (validateBooking(bookingDto)) {
            Booking booking = modelMapper.map(bookingDto, Booking.class);
            booking.setId(UUID.randomUUID().toString());
            booking.setTimestamp(LocalDateTime.now());
            if (bookingDto.isNeedDriver()) booking.setDriver(getRandomDriver());
            booking.setStatus("Pending");
            booking.setUser(user.get());
            bookingRepo.save(booking);
            for (Car car : booking.getCars()) {
                car.setStatus("Not Available");
                carRepo.save(car);
            }
            return true;
        }
        throw new RuntimeException("Uncaught Error!");
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingDto> getUserBookings(String id) {
        if (id == null | id.equals("")) throw new RuntimeException("Invalid Id");
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            List<Booking> bookingsByUser = bookingRepo.getBookingsByUser(byId.get());
            if (bookingsByUser == null | bookingsByUser.size() == 0) return null;
            List<BookingDto> map = modelMapper.map(bookingsByUser, new TypeToken<List<BookingDto>>() {
            }.getType());
            for (BookingDto bookingDto : map) {
                bookingDto.getCars().get(0).setBookings(null);
                bookingDto.getCars().get(0).setRepairs(null);
                bookingDto.getCars().get(0).setId(null);
                bookingDto.getCars().get(0).setStatus(null);
                bookingDto.getCars().get(0).setTimestamp(null);
                if(bookingDto.getDriver() != null){
                    bookingDto.getDriver().setBookings(null);
                    bookingDto.getDriver().setId(null);
                    bookingDto.getDriver().setPassword(null);
                    bookingDto.getDriver().setTimestamp(null);
                    bookingDto.getDriver().setStatus(null);
                }
                if (bookingDto.getReturnEntity() != null) bookingDto.getReturnEntity().setBooking(null);
                if (bookingDto.getPayment() != null) bookingDto.getPayment().setBooking(null);
            }
            return map;
        } else {
            throw new RuntimeException("Invalid Id - User not found");
        }
    }

    @Override
    public boolean finishBooking(ReturnDto returnDto) {
        if (returnDto.getBookingId() == null || returnDto.getBookingId().equals("")) throw new RuntimeException("Invalid Id");
        if (returnDto.getDistance() == 0) throw new RuntimeException("Invalid Distance");
        Optional<Booking> byId = bookingRepo.findById(returnDto.getBookingId());
        if (byId.isPresent()) {
            Return aReturn = new Return(UUID.randomUUID().toString(), returnDto.getDistance(), returnDto.getDamage(), LocalDateTime.now(), byId.get());
            returnRepo.save(aReturn);
            byId.get().setStatus("Completed");
            return true;
        } else {
            throw new RuntimeException("Invalid Id - Booking not found");
        }
    }

    private boolean validateBooking(BookingDto bookingDto) {
        if (bookingDto.getCars() == null | bookingDto.getCars().size() == 0) throw new RuntimeException("Invalid car");
        for (Car car : bookingDto.getCars()) {
            if (car.getStatus().equals("Not Available")) throw new RuntimeException("Car is not available");
            if (car.getStatus().equals("not Available")) throw new RuntimeException("Car is not available");
            if (car.getStatus().equals("Not available")) throw new RuntimeException("Car is not available");
            if (car.getStatus().equals("not available")) throw new RuntimeException("Car is not available");
            if (car.getStatus().equals("Under Maintenance")) throw new RuntimeException("Car is under maintenance");
            if (car.getStatus().equals("under Maintenance")) throw new RuntimeException("Car is under maintenance");
            if (car.getStatus().equals("under maintenance")) throw new RuntimeException("Car is under maintenance");
            if (car.getStatus().equals("Under maintenance")) throw new RuntimeException("Car is under maintenance");
        }
        if (bookingDto.getPickupLocation() == null | bookingDto.getPickupLocation().equals(""))
            throw new RuntimeException("Invalid pickup location");
        if (bookingDto.getDestination() == null | bookingDto.getPickupLocation().equals(""))
            throw new RuntimeException("Invalid destination");
        if (bookingDto.getPickupTime() == null | bookingDto.getPickupLocation().equals(""))
            throw new RuntimeException("Invalid pickup time");
        if (bookingDto.getDestinationTime() == null | bookingDto.getPickupLocation().equals(""))
            throw new RuntimeException("Invalid destination time");
        if (bookingDto.getPassengers() == 0) throw new RuntimeException("Invalid passengers");
        return true;
    }

    private Driver getRandomDriver() {
        List<Driver> all = driverRepo.findAll();
        Random random = new Random();
        return all.get(random.nextInt(all.size()));
    }
}
