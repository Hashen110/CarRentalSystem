package lk.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HomeDto implements DTO {
    private long cars;
    private long drivers;
    private long bookings;
    private long users;
    private UserDto loggedUser;
}
