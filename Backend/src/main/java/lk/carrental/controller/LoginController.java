package lk.carrental.controller;

import lk.carrental.dto.LoginDto;
import lk.carrental.service.LoginService;
import lk.carrental.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping
    public ResponseEntity getMethod() {
        int code = 200;
        if (loginService.getLoggedUser() != null) code = 300;
        return new ResponseEntity(new StandardResponse(code, "Success", loginService.getLoggedUser()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity postMethod(@RequestBody LoginDto loginDto) {
        if (loginService.getLoggedUser() != null){
            return new ResponseEntity(new StandardResponse(403, "Forbidden", "Please logout to login"), HttpStatus.OK);
        }
        boolean b = loginService.userLogin(loginDto.getUsername(), loginDto.getPassword(), loginDto.isDriver());
        if (b) {
            return new ResponseEntity(new StandardResponse(200, "Success", b), HttpStatus.OK);
        } else {
            return new ResponseEntity(new StandardResponse(400, "Error", b), HttpStatus.OK);
        }
    }

    @GetMapping("out")
    public ResponseEntity getMethod1(){
        if (loginService.getLoggedUser() == null){
            return new ResponseEntity(new StandardResponse(403, "Forbidden", "No users logged in"), HttpStatus.OK);
        }else {
            return new ResponseEntity(new StandardResponse(200, "Success", loginService.userLogout()), HttpStatus.OK);
        }
    }

    @GetMapping("user")
    public ResponseEntity getLoggedUser(){
        return new ResponseEntity(new StandardResponse(200, "Success", loginService.getLoggedUser()), HttpStatus.OK);
    }
}
