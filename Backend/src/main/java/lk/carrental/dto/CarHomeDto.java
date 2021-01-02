package lk.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarHomeDto {
    private List<Integer> passengers;
    private List<String> transmissions;
    private List<String> brands;
    private List<String> types;
    private List<Double> prices;
    private List<String> fuels;
    private List<CarDto> cars;
}
