package lk.carrental.controller;

import lk.carrental.dto.UserDto;
import lk.carrental.service.ProfileService;
import lk.carrental.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@CrossOrigin
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/user/update")
    public ResponseEntity updateUserProfile(@RequestBody UserDto userDto){
        return new ResponseEntity(new StandardResponse(200, "Success", profileService.updateProfile(userDto)), HttpStatus.OK);
    }
}
