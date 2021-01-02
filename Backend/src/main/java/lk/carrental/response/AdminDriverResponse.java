package lk.carrental.response;

import lk.carrental.dto.DriverDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDriverResponse {
    private long totalDrivers;
    private long availableDrivers;
    private long workingDrivers;
    private List<DriverDto> drivers;
}
