package lk.carrental.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Driver {
    @Id
    private String id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String contact;
    @Column(unique = true)
    private String nicNumber;
    @Column(unique = true)
    private String drivingLicenseNumber;
    private String password;
    private String status;
    @Column(unique = true)
    private LocalDateTime timestamp;
    @OneToMany(mappedBy = "driver")
    private List<Booking> bookings = new ArrayList<>();

    public Driver() {
    }

    public Driver(String id, String username, String email, String contact, String nicNumber, String drivingLicenseNumber, String password, String status, LocalDateTime timestamp) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.contact = contact;
        this.nicNumber = nicNumber;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.password = password;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Driver(String id, String username, String email, String contact, String nicNumber, String drivingLicenseNumber, String password, String status, LocalDateTime timestamp, List<Booking> bookings) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.contact = contact;
        this.nicNumber = nicNumber;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.password = password;
        this.status = status;
        this.timestamp = timestamp;
        this.bookings = bookings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNicNumber() {
        return nicNumber;
    }

    public void setNicNumber(String nicNumber) {
        this.nicNumber = nicNumber;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", nicNumber='" + nicNumber + '\'' +
                ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
