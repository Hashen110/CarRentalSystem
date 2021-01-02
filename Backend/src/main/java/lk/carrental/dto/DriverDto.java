package lk.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverDto {
    private String id;
    private String username;
    private String email;
    private String contact;
    private String nicNumber;
    private String drivingLicenseNumber;
    private String password;
    private String status;
}
