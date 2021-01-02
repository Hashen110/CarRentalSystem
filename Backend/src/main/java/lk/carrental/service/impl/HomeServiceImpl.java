package lk.carrental.service.impl;

import lk.carrental.dto.HomeDto;
import lk.carrental.dto.UserDto;
import lk.carrental.entity.Car;
import lk.carrental.entity.Driver;
import lk.carrental.entity.User;
import lk.carrental.repo.BookingRepo;
import lk.carrental.repo.CarRepo;
import lk.carrental.repo.DriverRepo;
import lk.carrental.repo.UserRepo;
import lk.carrental.service.HomeService;
import lk.carrental.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
@Transactional
public class HomeServiceImpl implements HomeService {
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LoginService loginService;

    @Override
    public HomeDto getBoxData() {
        if (loginService.getLoggedUser() == null)
            return new HomeDto(carRepo.count(), driverRepo.count(), bookingRepo.count(), userRepo.count(), null);
        UserDto userDto = new UserDto(loginService.getLoggedUser().getUsername(), loginService.getLoggedUser().isAdmin(),
                loginService.getLoggedUser().isDriver(), loginService.getLoggedUser().getStatus(), loginService.getLoggedUser().getMessage());
        userDto.setId(loginService.getLoggedUser().getId());
        return new HomeDto(carRepo.count(), driverRepo.count(), bookingRepo.count(), userRepo.count(), userDto);
    }
}
