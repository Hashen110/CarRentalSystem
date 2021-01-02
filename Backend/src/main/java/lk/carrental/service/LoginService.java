package lk.carrental.service;

import lk.carrental.dto.UserDto;

public interface LoginService {
    UserDto getLoggedUser();
    void refreshLoggedUser();
    boolean userLogin(UserDto userDto);
    boolean userLogin(String username, String password, boolean isDriver);
    boolean userLogout();
}
