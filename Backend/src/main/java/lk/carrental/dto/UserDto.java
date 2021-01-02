package lk.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto implements DTO {
    private String id;
    private String username;
    private String email;
    private String contact;
    private String address;
    private String nicNumber;
    private File nicPhoto;
    private String drivingLicenseNumber;
    private File drivingLicensePhoto;
    private String password;
    private boolean isAdmin;
    private boolean isDriver;
    private String status;
    private String message;

    public UserDto(String username, boolean isAdmin, boolean isDriver, String status, String message) {
        this.username = username;
        this.isAdmin = isAdmin;
        this.isDriver = isDriver;
        this.status = status;
        this.message = message;
    }
}
