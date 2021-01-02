package lk.carrental.controller;

import lk.carrental.dto.BookingDto;
import lk.carrental.dto.ReturnDto;
import lk.carrental.service.BookingService;
import lk.carrental.service.LoginService;
import lk.carrental.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/booking")
@CrossOrigin
public class BookingController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private BookingService bookingService;

    @PostMapping("/add")
    private ResponseEntity addBooking(@RequestBody BookingDto bookingDto) {
        if (loginService.getLoggedUser() == null) {
            return new ResponseEntity(new StandardResponse(401, "Unauthorized", "Please logged in to add new book"), HttpStatus.OK);
        }
        return new ResponseEntity(new StandardResponse(200, "Success", bookingService.addBooking(bookingDto)), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    private ResponseEntity getUserBookings(@PathVariable String id){
        if (loginService.getLoggedUser() == null) {
            return new ResponseEntity(new StandardResponse(401, "Unauthorized", "Please logged in to add new book"), HttpStatus.OK);
        }
        return new ResponseEntity(new StandardResponse(200, "Success", bookingService.getUserBookings(id)), HttpStatus.OK);
    }

    @PostMapping("/finish")
    private ResponseEntity finishBooking(@RequestBody ReturnDto returnDto) {
        if (loginService.getLoggedUser() == null) {
            return new ResponseEntity(new StandardResponse(401, "Unauthorized", "Please logged in to add new book"), HttpStatus.OK);
        }
        return new ResponseEntity(new StandardResponse(200, "Success", bookingService.finishBooking(returnDto)), HttpStatus.OK);
    }
}
