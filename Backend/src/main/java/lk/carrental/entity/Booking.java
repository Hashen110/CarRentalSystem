package lk.carrental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking {
    @Id
    private String id;
    private String pickupLocation;
    private String destination;
    private LocalDateTime pickupTime;
    private LocalDateTime destinationTime;
    private int passengers;
    private String status;
    private LocalDateTime timestamp;
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "driver", referencedColumnName = "id")
    private Driver driver;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Car_Booking", joinColumns = {@JoinColumn(name = "booking", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "car", referencedColumnName = "id")})
    private List<Car> cars = new ArrayList<>();
    @OneToOne(mappedBy = "booking")
    private Return returnEntity;

    public Booking(String id, String pickupLocation, String destination, LocalDateTime pickupTime, LocalDateTime destinationTime, int passengers, String status, LocalDateTime timestamp, User user, Driver driver, List<Car> cars) {
        this.id = id;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.pickupTime = pickupTime;
        this.destinationTime = destinationTime;
        this.passengers = passengers;
        this.status = status;
        this.timestamp = timestamp;
        this.user = user;
        this.driver = driver;
        this.cars = cars;
    }
}
