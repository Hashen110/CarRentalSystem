package lk.carrental.response;

import lk.carrental.dto.CarDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminCarResponse {
    private long totalCars;
    private long generalCars;
    private long premiumCars;
    private long luxuryCars;
    private long carsNeedMaintenance;
    private long carsUnderMaintenance;
    private List<CarDto> cars;
}
