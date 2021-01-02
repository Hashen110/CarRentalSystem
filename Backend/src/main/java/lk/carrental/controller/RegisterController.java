package lk.carrental.controller;

import lk.carrental.dto.HomeDto;
import lk.carrental.dto.UserDto;
import lk.carrental.service.LoginService;
import lk.carrental.service.RegisterService;
import lk.carrental.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/register")
@CrossOrigin
public class RegisterController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;

    @GetMapping
    private ResponseEntity getMethod() {
        if (loginService.getLoggedUser() != null) {
            return new ResponseEntity(new StandardResponse(403, "Forbidden", "User can't access register page while logged in"), HttpStatus.OK);
        }
        return new ResponseEntity(new StandardResponse(200, "OK", new HomeDto(0, 0, 0, 0, loginService.getLoggedUser())), HttpStatus.OK);
    }

    @PostMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity postMethod(@RequestBody UserDto userDto) {
        if (loginService.getLoggedUser() != null) {
            return new ResponseEntity(new StandardResponse(403, "Forbidden", "User is currently logged in. pelease logout to register a new user"), HttpStatus.OK);
        }
        return new ResponseEntity(new StandardResponse(200, "OK", registerService.registerUser(userDto)), HttpStatus.OK);
    }

    @PostMapping(value = "/admin/user", consumes = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity postMethod1(@RequestBody UserDto userDto) {
        if (loginService.getLoggedUser() == null | !loginService.getLoggedUser().isAdmin()) {
            return new ResponseEntity(new StandardResponse(403, "Forbidden", "No Admin Logged in"), HttpStatus.OK);
        }
        return new ResponseEntity(new StandardResponse(200, "OK", registerService.registerUser(userDto, false)), HttpStatus.OK);
    }
}
