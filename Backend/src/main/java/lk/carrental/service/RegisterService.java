package lk.carrental.service;

import lk.carrental.dto.UserDto;

public interface RegisterService {
    boolean registerUser(UserDto userDto);
    boolean registerUser(UserDto userDto, boolean isDriver);
}
