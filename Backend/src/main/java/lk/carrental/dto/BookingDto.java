package lk.carrental.dto;

import lk.carrental.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDto {
    private String id;
    private String pickupLocation;
    private String destination;
    private LocalDateTime pickupTime;
    private LocalDateTime destinationTime;
    private String carId;
    private int passengers;
    private String status;
    private boolean isNeedDriver;
    private String userId;
    private Driver driver;
    private List<Car> cars = new ArrayList<>();
    private Return returnEntity;
    private LocalDateTime timestamp;

    public BookingDto(String id, String pickupLocation, String destination, LocalDateTime pickupTime, LocalDateTime destinationTime, String carId, int passengers, String status, boolean isNeedDriver, String userId, Driver driver, List<Car> cars, LocalDateTime timestamp) {
        this.id = id;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.pickupTime = pickupTime;
        this.destinationTime = destinationTime;
        this.carId = carId;
        this.passengers = passengers;
        this.status = status;
        this.isNeedDriver = isNeedDriver;
        this.userId = userId;
        this.driver = driver;
        this.cars = cars;
        this.timestamp = timestamp;
    }
}
