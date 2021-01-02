package lk.carrental.controller;

import lk.carrental.dto.CarDto;
import lk.carrental.service.CarService;
import lk.carrental.service.LoginService;
import lk.carrental.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cars")
@CrossOrigin
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private LoginService loginService;

    @GetMapping
    public ResponseEntity getMethod() {
        return new ResponseEntity(new StandardResponse(200, "Success", carService.getCars(true)), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity postAddCar(@RequestBody() CarDto carDto) {
        return new ResponseEntity(new StandardResponse(200, "Success", carService.addCar(carDto)), HttpStatus.OK);
    }

    @GetMapping(path = "/car/{id}")
    public ResponseEntity getCar(@PathVariable String id) {
        return new ResponseEntity(new StandardResponse(200, "Success", carService.getCar(id)), HttpStatus.OK);
    }
}
