package lk.carrental.service.impl;

import lk.carrental.dto.UserDto;
import lk.carrental.entity.User;
import lk.carrental.repo.UserRepo;
import lk.carrental.service.LoginService;
import lk.carrental.service.RegisterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LoginService loginService;

    @Override
    public boolean registerUser(UserDto userDto) {
        if (validateUser(userDto)){
            userDto.setId(UUID.randomUUID().toString());
            userDto.setStatus("pending");
//            userDto.setPassword(Base64.getEncoder().encodeToString(userDto.getPassword().getBytes()));
//            System.out.println("Inside service"+userDto.getPassword());
            if (checkDuplicate(userDto)) {
//                User map = modelMapper.map(userDto, User.class);
//                map.setTimestamp(LocalDateTime.now());
//                System.err.println("Register Service -- registerUser(UserDto) -- " + map);
//                userRepo.save(map);
                User user = userRepo.save(modelMapper.map(userDto, User.class));
                user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
                user.setTimestamp(LocalDateTime.now());
                if (user.getStatus() == null) return true;
                return loginService.userLogin(userDto);
            }
        }
        throw new RuntimeException("Uncaught validation error!");
    }

    private boolean validateUser(UserDto userDto){
        boolean bool = false;
        if (userDto.getUsername() == null) throw new RuntimeException("Username cannot be empty");
        if (userDto.getEmail() == null) throw new RuntimeException("Email cannot be empty");
        if (userDto.getContact() == null) throw new RuntimeException("Contact cannot be empty");
        if (userDto.getAddress() == null) throw new RuntimeException("Address cannot be empty");
        if (userDto.getNicNumber() == null) throw new RuntimeException("NIC Number cannot be empty");
        if (userDto.getNicPhoto() == null) throw new RuntimeException("NIC Photo cannot be empty");
        if (userDto.getDrivingLicenseNumber() == null)
            throw new RuntimeException("Driving License Number cannot be empty");
        if (userDto.getDrivingLicensePhoto() == null)
            throw new RuntimeException("Driving License Photo cannot be empty");
        if (userDto.getPassword() == null) throw new RuntimeException("Password cannot be empty");
        if (userDto.getUsername().length() < 6) throw new RuntimeException("Username must have minimum 8 characters");
        if (userDto.getPassword().length() < 6) throw new RuntimeException("Password must have minimum 8 characters");
        if (userDto.getContact().length() < 9) throw new RuntimeException("Contact must have minimum 9 characters");
        if (userDto.getAddress().length() < 4) throw new RuntimeException("Address must have minimum 4 characters");
        if (!validateEmail(userDto.getEmail())) throw new RuntimeException("Invalid Email");
        bool = true;
        return bool;
    }

    @Override
    public boolean registerUser(UserDto userDto, boolean isDriver) {
        if (validateUser(userDto)){
            userDto.setId(UUID.randomUUID().toString());
            userDto.setDriver(isDriver);
//            userDto.setPassword(Base64.getEncoder().encodeToString(userDto.getPassword().getBytes()));
//            System.out.println("Inside service"+userDto.getPassword());
            if (checkDuplicate(userDto)) {
//                User map = modelMapper.map(userDto, User.class);
//                map.setTimestamp(LocalDateTime.now());
//                System.err.println("Register Service -- registerUser(UserDto, boolean) -- " + map);
//                userRepo.save(map);
//                map.setPassword(userDto.getPassword());
//                map.setTimestamp(LocalDateTime.now());
                User user = userRepo.save(modelMapper.map(userDto, User.class));
                user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
                user.setTimestamp(LocalDateTime.now());
                return true;
            }
        }
        throw new RuntimeException("Uncaught validation error!");
    }

    private boolean checkDuplicate(UserDto userDto) {
        User user = userRepo.getUserByDrivingLicenseNumber(userDto.getDrivingLicenseNumber());
        if (user != null){
            throw new RuntimeException("constraint failed! - Driving License Number already exists");
        }
        User user1 = userRepo.getUserByEmail(userDto.getEmail());
        if (user1 != null){
            throw new RuntimeException("constraint failed! - Email already exists");
        }
        User user2 = userRepo.getUserByNicNumber(userDto.getNicNumber());
        if (user2 != null){
            throw new RuntimeException("constraint failed! - NIC Number already exists");
        }
        User user3 = userRepo.getUserByUsername(userDto.getUsername());
        if (user3 != null){
            throw new RuntimeException("constraint failed! - Username already exists");
        }
        return true;
    }

    private boolean validateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
