package lk.carrental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private String id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String contact;
    private String address;
    @Column(unique = true)
    private String nicNumber;
    private File nicPhoto;
    @Column(unique = true)
    private String drivingLicenseNumber;
    private File drivingLicensePhoto;
    private String password;
    private boolean isAdmin = false;
    private boolean isDriver = false;
    private String status = "pending";
    private String message;
    @Column(unique = true)
    private LocalDateTime timestamp;
    @OneToMany(mappedBy = "user")
    private List<Booking> users = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Login> logins = new ArrayList<>();

    public User(String id, String username, String email, String contact, String address, String nicNumber, File nicPhoto, String drivingLicenseNumber, File drivingLicensePhoto, String password, boolean isAdmin, boolean isDriver, String status, String message, LocalDateTime timestamp) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.nicNumber = nicNumber;
        this.nicPhoto = nicPhoto;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.drivingLicensePhoto = drivingLicensePhoto;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isDriver = isDriver;
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
