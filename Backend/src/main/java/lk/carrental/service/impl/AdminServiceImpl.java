package lk.carrental.service.impl;

import lk.carrental.dto.BookingDto;
import lk.carrental.dto.CarDto;
import lk.carrental.dto.DriverDto;
import lk.carrental.dto.UserDto;
import lk.carrental.entity.Booking;
import lk.carrental.entity.Car;
import lk.carrental.entity.Driver;
import lk.carrental.entity.User;
import lk.carrental.repo.BookingRepo;
import lk.carrental.repo.CarRepo;
import lk.carrental.repo.DriverRepo;
import lk.carrental.repo.UserRepo;
import lk.carrental.response.*;
import lk.carrental.service.AdminService;
import lk.carrental.service.LoginService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LoginService loginService;

    @Override
    public AdminDashboardResponse getDashboardData() {
        if (loginService.getLoggedUser() == null) throw new RuntimeException("Unauthorized - No admin logged in");
        if (!loginService.getLoggedUser().isAdmin())
            throw new RuntimeException("Forbidden - User doesn't have admin privileges");
        List<Driver> available = driverRepo.getDriversByStatus("available");
        List<Driver> available1 = driverRepo.getDriversByStatus("Available");
        List<Car> available2 = carRepo.getCarsByStatus("available");
        List<Car> available3 = carRepo.getCarsByStatus("Available");
        List<Car> all = carRepo.findAll();
        long carsNeedMaintenance = 0;
        if (all != null || all.size() != 0) {
            for (Car car : all) {
                if (car.getBookings() != null || car.getBookings().size() != 0) {
                    long i = 0;
                    for (Booking booking : car.getBookings()) {
                        if (booking.getReturnEntity() != null) i += booking.getReturnEntity().getDistance();
                    }
                    if (i >= 5000) carsNeedMaintenance++;
                }
            }
        }
        return new AdminDashboardResponse(userRepo.count(), bookingRepo.count(), (available2.size() + available3.size()), bookingRepo.getBookingsByStatus("Approve").size(), (available.size() + available1.size()), carRepo.count(), carsNeedMaintenance, 0, 0, 0, 0, 0);
    }

    @Override
    public AdminUserResponse getUserData() {
        if (loginService.getLoggedUser() == null) throw new RuntimeException("Unauthorized - No admin logged in");
        if (!loginService.getLoggedUser().isAdmin())
            throw new RuntimeException("Forbidden - User doesn't have admin privileges");
        List<User> users = userRepo.findAll();
        long adminUsers = 0;
        for (User user : users) {
            if (user.isAdmin()) adminUsers++;
        }
        List<User> pendingUsers = userRepo.getUsersByStatus("pending");
        return new AdminUserResponse(users.size(), pendingUsers.size(), adminUsers, modelMapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType()), modelMapper.map(pendingUsers, new TypeToken<List<UserDto>>() {
        }.getType()));
    }

    @Override
    public boolean userApproved(String id) {
        if (loginService.getLoggedUser() == null) throw new RuntimeException("Unauthorized - No admin logged in");
        if (!loginService.getLoggedUser().isAdmin())
            throw new RuntimeException("Forbidden - User doesn't have admin privileges");
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setStatus("approved");
            return true;
        } else {
            throw new RuntimeException("Invalid Id! User not found");
        }
    }

    @Override
    public boolean userReject(String id, String message) {
        if (loginService.getLoggedUser() == null) throw new RuntimeException("Unauthorized - No admin logged in");
        if (!loginService.getLoggedUser().isAdmin())
            throw new RuntimeException("Forbidden - User doesn't have admin privileges");
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setStatus("rejected");
            user.setMessage(message);
            return true;
        } else {
            throw new RuntimeException("Invalid Id! User not found");
        }
    }

    @Override
    public boolean userDelete(String id) {
        if (loginService.getLoggedUser() == null) throw new RuntimeException("Unauthorized - No admin logged in");
        if (!loginService.getLoggedUser().isAdmin())
            throw new RuntimeException("Forbidden - User doesn't have admin privileges");
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            if (byId.get().isAdmin()) throw new RuntimeException("Forbidden - User can't delete admin user");
            userRepo.delete(byId.get());
            return true;
        } else {
            throw new RuntimeException("Invalid Id! User not found");
        }
    }

    @Override
    public AdminCarResponse getCarData() {
        if (loginService.getLoggedUser() == null) throw new RuntimeException("Unauthorized - No admin logged in");
        if (!loginService.getLoggedUser().isAdmin())
            throw new RuntimeException("Forbidden - User doesn't have admin privileges");
        List<Car> all = carRepo.findAll();
        if (all.size() == 0 | all == null) return new AdminCarResponse(0, 0, 0, 0, 0, 0, null);
        List<CarDto> cars = new ArrayList<>();
        long generalCars = 0;
        long premiumCars = 0;
        long luxuryCars = 0;
        long carsNeedMaintenance = 0;
        for (Car car : all) {
            cars.add(modelMapper.map(car, CarDto.class));
            if (car.getType().equals("general") | car.getType().equals("General")) generalCars++;
            if (car.getType().equals("premium") | car.getType().equals("Premium")) premiumCars++;
            if (car.getType().equals("luxury") | car.getType().equals("Luxury")) luxuryCars++;
            if (car.getBookings() != null || car.getBookings().size() != 0) {
                long i = 0;
                for (Booking booking : car.getBookings()) {
                    if (booking.getReturnEntity() != null) i += booking.getReturnEntity().getDistance();
                }
                if (i >= 5000) carsNeedMaintenance++;
            }
        }
        return new AdminCarResponse(all.size(), generalCars, premiumCars, luxuryCars, carsNeedMaintenance, 0, cars);

    }

    @Override
    public AdminDriverResponse getDriverData() {
        if (loginService.getLoggedUser() == null) throw new RuntimeException("Unauthorized - No admin logged in");
        if (!loginService.getLoggedUser().isAdmin())
            throw new RuntimeException("Forbidden - User doesn't have admin privileges");
        List<Driver> all = driverRepo.findAll();
        if (all.size() == 0 | all == null) return new AdminDriverResponse(0, 0, 0, null);
        long availableDrivers = 0;
        long workingDrivers = 0;
        List<DriverDto> drivers = new ArrayList<>();
        for (Driver driver : all) {
            drivers.add(modelMapper.map(driver, DriverDto.class));
            if (driver.getStatus().equals("available") | driver.getStatus().equals("Available")) availableDrivers++;
            if (driver.getStatus().equals("working") | driver.getStatus().equals("Working")) workingDrivers++;
        }
        return new AdminDriverResponse(drivers.size(), availableDrivers, workingDrivers, drivers);
    }

    @Override
    @Transactional(readOnly = true)
    public AdminBookingResponse getBookingData() {
        if (loginService.getLoggedUser() == null) throw new RuntimeException("Unauthorized - No admin logged in");
        if (!loginService.getLoggedUser().isAdmin())
            throw new RuntimeException("Forbidden - User doesn't have admin privileges");
        List<Booking> all = bookingRepo.findAll();
        if (all == null | all.size() == 0) return new AdminBookingResponse(0, 0, 0, 0, 0, 0, null, null);
        List<BookingDto> map = modelMapper.map(all, new TypeToken<List<BookingDto>>() {
        }.getType());
        long todayBookings = 0;
        long yesterdayBookings = 0;
        long weekBookings = 0;
        long monthBookings = 0;
        long bookingRequests = 0;
        List<BookingDto> pendingBookings = new ArrayList<>();
        for (BookingDto bookingDto : map) {
            bookingDto.setUserId(userRepo.findById(bookingDto.getUserId()).get().getUsername());
            bookingDto.getCars().get(0).setBookings(null);
            bookingDto.getCars().get(0).setRepairs(null);
            bookingDto.getCars().get(0).setId(null);
            bookingDto.getCars().get(0).setStatus(null);
            bookingDto.getCars().get(0).setTimestamp(null);
            if (bookingDto.getDriver() != null) {
                bookingDto.getDriver().setBookings(null);
                bookingDto.getDriver().setId(null);
                bookingDto.getDriver().setPassword(null);
                bookingDto.getDriver().setTimestamp(null);
                bookingDto.getDriver().setStatus(null);
            }
            if (bookingDto.getReturnEntity() != null) bookingDto.getReturnEntity().setBooking(null);
            if (bookingDto.getPayment() != null) bookingDto.getPayment().setBooking(null);
            if (bookingDto.getStatus().equals("Pending") | bookingDto.getStatus().equals("pending")) {
                bookingRequests++;
                pendingBookings.add(bookingDto);
            }
            LocalDate bookingDate = LocalDate.of(bookingDto.getTimestamp().getYear(), bookingDto.getTimestamp().getMonth(), bookingDto.getTimestamp().getDayOfMonth());
            if (bookingDate.toString().equals(LocalDate.now().toString())) todayBookings++;
            if (bookingDate.toString().equals(LocalDate.now().minusDays(1).toString())) yesterdayBookings++;
            if (bookingDate.getYear() == LocalDate.now().getYear() & bookingDate.getMonth() == LocalDate.now().getMonth())
                monthBookings++;
            if (bookingDate.getYear() == LocalDate.now().getYear()) {
                if (bookingDate.getDayOfMonth() + 7 >= LocalDate.now().getDayOfMonth()) weekBookings++;
            }
        }
        return new AdminBookingResponse(all.size(), todayBookings, yesterdayBookings, weekBookings, monthBookings, bookingRequests, pendingBookings, map);
    }

    @Override
    public boolean changeBookingStatus(String id, String status) {
        if (loginService.getLoggedUser() == null) throw new RuntimeException("Unauthorized - No admin logged in");
        if (!loginService.getLoggedUser().isAdmin())
            throw new RuntimeException("Forbidden - User doesn't have admin privileges");
        if (id == null | id.equals("")) throw new RuntimeException("Invalid Id");
        if (status == null | status.equals("")) throw new RuntimeException("Invalid Status");
        Optional<Booking> byId = bookingRepo.findById(id);
        if (byId.isPresent()) {
            byId.get().setStatus(capitalizeEachWord(status));
            return true;
        } else {
            throw new RuntimeException("Invalid Booking - Booking Not Found");
        }
    }

    private String capitalizeEachWord(String message) {
        char[] charArray = message.toCharArray();
        boolean foundSpace = true;
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isLetter(charArray[i])) {
                if (foundSpace) {
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            } else {
                foundSpace = true;
            }
        }
        return String.valueOf(charArray);
    }
}
