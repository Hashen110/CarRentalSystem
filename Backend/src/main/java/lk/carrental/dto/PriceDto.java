package lk.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PriceDto {
    private String id;
    private int damage;
    private int distance;
    private LocalDateTime timestamp;
}
