package lk.carrental.controller;

import lk.carrental.dto.DriverDto;
import lk.carrental.service.DriverService;
import lk.carrental.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/driver")
@CrossOrigin
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("/add")
    public ResponseEntity addDriver(@RequestBody DriverDto driverDto) {
        return new ResponseEntity(new StandardResponse(200, "Added", driverService.addDriver(driverDto)), HttpStatus.OK);
    }

    @PostMapping(path = "/delete/{id}")
    public ResponseEntity deleteDriver(@PathVariable String id) {
        return new ResponseEntity(new StandardResponse(200, "Added", driverService.deleteDriver(id)), HttpStatus.OK);
    }

    @GetMapping(path = "schedule/{id}")
    public ResponseEntity getSchedule(@PathVariable String id){
        return new ResponseEntity(new StandardResponse(200, "Success", driverService.getSchedule(id)), HttpStatus.OK);
    }
}
