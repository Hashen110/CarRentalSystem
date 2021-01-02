package lk.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScheduleDto {
    private String id;
    private String customer;
    private String car;
    private String pickupLocation;
    private LocalDateTime pickupTime;
    private String destination;
    private LocalDateTime destinationTime;
    private String status;
}
