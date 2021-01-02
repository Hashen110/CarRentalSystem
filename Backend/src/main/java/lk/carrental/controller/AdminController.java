package lk.carrental.controller;

import lk.carrental.dto.StatusDto;
import lk.carrental.service.AdminService;
import lk.carrental.service.LoginService;
import lk.carrental.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    private ResponseEntity getDashboard() {
        ResponseEntity restrict = restrict();
        if (restrict != null) return restrict;
        return new ResponseEntity(new StandardResponse(200, "Success", adminService.getDashboardData()), HttpStatus.OK);
    }

    @GetMapping("/user")
    private ResponseEntity getUser() {
        ResponseEntity restrict = restrict();
        if (restrict != null) return restrict;
        return new ResponseEntity(new StandardResponse(200, "Success", adminService.getUserData()), HttpStatus.OK);
    }

    @GetMapping("/car")
    private ResponseEntity getCar() {
        ResponseEntity restrict = restrict();
        if (restrict != null) return restrict;
        return new ResponseEntity(new StandardResponse(200, "Success", adminService.getCarData()), HttpStatus.OK);
    }

    @GetMapping("/driver")
    private ResponseEntity getDriver() {
        ResponseEntity restrict = restrict();
        if (restrict != null) return restrict;
        return new ResponseEntity(new StandardResponse(200, "Success", adminService.getDriverData()), HttpStatus.OK);
    }

    @GetMapping("/booking")
    private ResponseEntity getBooking() {
        ResponseEntity restrict = restrict();
        if (restrict != null) return restrict;
        return new ResponseEntity(new StandardResponse(200, "Success", adminService.getBookingData()), HttpStatus.OK);
    }

    @PostMapping("/user/approved/{id}")
    private ResponseEntity approvedUser(@PathVariable String id) {
        ResponseEntity restrict = restrict();
        if (restrict != null) return restrict;
        return new ResponseEntity(new StandardResponse(200, "Success", adminService.userApproved(id)), HttpStatus.OK);
    }

    @PostMapping("/user/delete/{id}")
    private ResponseEntity deleteUser(@PathVariable String id) {
        ResponseEntity restrict = restrict();
        if (restrict != null) return restrict;
        return new ResponseEntity(new StandardResponse(200, "Success", adminService.userDelete(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/user/reject")
    private ResponseEntity rejectUser(@RequestParam String id, @RequestParam String message) {
        ResponseEntity restrict = restrict();
        if (restrict != null) return restrict;
        return new ResponseEntity(new StandardResponse(200, "Success", adminService.userReject(id, message)), HttpStatus.OK);
    }

    @PostMapping("/booking/status")
    private ResponseEntity changeBookingStatus(@RequestBody StatusDto statusDto) {
        ResponseEntity restrict = restrict();
        if (restrict != null) return restrict;
        return new ResponseEntity(new StandardResponse(200, "Success", adminService.changeBookingStatus(statusDto.getId(), statusDto.getStatus())), HttpStatus.OK);
    }

    private ResponseEntity restrict() {
        if (loginService.getLoggedUser() == null) {
            return new ResponseEntity(new StandardResponse(401, "Unauthorized", "User need to login"), HttpStatus.OK);
        }
        if (!loginService.getLoggedUser().isAdmin()) {
            return new ResponseEntity(new StandardResponse(403, "Forbidden", "User must have admin privileges"), HttpStatus.OK);
        }
        return null;
    }
}
