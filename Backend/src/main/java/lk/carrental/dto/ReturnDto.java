package lk.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnDto {
    private String bookingId;
    private int distance;
    private int damage;
    private String id;
    private LocalDateTime timestamp;
}
