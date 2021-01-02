package lk.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDto {
    private String id;
    private String brand;
    private String type;
    private File frontImage;
    private File backImage;
    private File slideImage;
    private File interiorImage;
    private int passengers;
    private String transmission;
    private String fuel;
    private double dailyRate;
    private double monthlyRate;
    private double freeMileage;
    private double priceExtraKm;
    private String registrationNumber;
    private String color;
    private double lossDamage;
    private String status = "Available";
}
