package lk.carrental.service.impl;

import lk.carrental.dto.UserDto;
import lk.carrental.entity.Driver;
import lk.carrental.entity.Login;
import lk.carrental.entity.User;
import lk.carrental.repo.DriverRepo;
import lk.carrental.repo.LoginRepo;
import lk.carrental.repo.UserRepo;
import lk.carrental.service.LoginService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private LoginRepo loginRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    private User user;

    @Override
    public UserDto getLoggedUser() {
        if (user == null) return null;
//        return modelMapper.map(user, UserDto.class);
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getContact(), user.getAddress(),
                user.getNicNumber(), user.getNicPhoto(), user.getDrivingLicenseNumber(), user.getDrivingLicensePhoto(),
                user.getPassword(), user.isAdmin(), user.isDriver(), user.getStatus(), user.getMessage());
    }

    @Override
    public void refreshLoggedUser() {
        if (user != null) {
            Optional<User> byId = userRepo.findById(user.getId());
            user = byId.orElse(null);
        }
    }

    @Override
    public boolean userLogin(UserDto userDto) {
        if (this.user != null) throw new RuntimeException("User already logged in");
        if (userDto.getId() == null) throw new RuntimeException("User Id not found");
        user = userRepo.getOne(userDto.getId());
        loginRepo.save(new Login(UUID.randomUUID().toString(), LocalDateTime.now(), user, null));
        return true;
    }

    @Override
    public boolean userLogin(String username, String password, boolean isDriver) {
        if (this.user != null) throw new RuntimeException("User already logged in");
        if (username == null) throw new RuntimeException("Username is empty");
        if (password == null) throw new RuntimeException("Password is empty");
        if (isDriver){
            Driver driver = driverRepo.getDriverByUsernameAndPassword(username, Base64.getEncoder().encodeToString(password.getBytes()));
            if (driver == null) throw new RuntimeException("Username Password invalid");
            User map = modelMapper.map(driver, User.class);
            map.setDriver(true);
            user = map;
            loginRepo.save(new Login(UUID.randomUUID().toString(), LocalDateTime.now(), null, driver));
            return true;
        }else{
            user = userRepo.getUserByUsernameAndPassword(username, Base64.getEncoder().encodeToString(password.getBytes()));
//        user = userRepo.getUserByUsernameAndPassword(username, password);
            if (user == null) throw new RuntimeException("Username Password invalid");
            loginRepo.save(new Login(UUID.randomUUID().toString(), LocalDateTime.now(), user, null));
            return true;
        }
    }

    @Override
    public boolean userLogout() {
        if (this.user == null) throw new RuntimeException("No users logged in");
        this.user = null;
        return true;
    }
}
