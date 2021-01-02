package lk.carrental.service.impl;

import lk.carrental.dto.UserDto;
import lk.carrental.entity.User;
import lk.carrental.repo.UserRepo;
import lk.carrental.service.LoginService;
import lk.carrental.service.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Optional;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LoginService loginService;

    @Override
    public UserDto updateProfile(UserDto userDto) {
        Optional<User> user = userRepo.findById(userDto.getId());
        if (user.isPresent()) {
            if (!userDto.getUsername().equals("")) {
                if (userDto.getUsername().length() < 6)
                    throw new RuntimeException("Username must have minimum 8 characters");
                user.get().setUsername(userDto.getUsername());
            }
            if (!userDto.getEmail().equals("")) {
                if (!validateEmail(userDto.getEmail())) throw new RuntimeException("Invalid Email");
                user.get().setEmail(userDto.getEmail());
            }
            if (!userDto.getContact().equals("")) {
                if (userDto.getContact().length() < 9)
                    throw new RuntimeException("Contact must have minimum 8 characters");
                user.get().setContact(userDto.getContact());
            }
            if (!userDto.getAddress().equals("")) {
                if (userDto.getAddress().length() < 4)
                    throw new RuntimeException("Address must have minimum 4 characters");
                user.get().setAddress(userDto.getAddress());
            }
            if (!userDto.getPassword().equals("")) {
                if (userDto.getPassword().length() < 6)
                    throw new RuntimeException("Password must have minimum 8 characters");
                user.get().setPassword(Base64.getEncoder().encodeToString(userDto.getPassword().getBytes()));
            }
            UserDto map = modelMapper.map(user.get(), UserDto.class);
            map.setPassword(null);
            loginService.refreshLoggedUser();
            return map;
        }
        throw new RuntimeException("Invalid User Id");
    }

    private boolean validateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
