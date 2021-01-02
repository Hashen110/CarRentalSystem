package lk.carrental.entity;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Car {
    @Id
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
    private String status;
    private LocalDateTime timestamp;
    @ManyToMany(mappedBy = "cars", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    public Car() {
    }

    public Car(String id, String brand, String type, File frontImage, File backImage, File slideImage, File interiorImage, int passengers, String transmission, String fuel, double dailyRate, double monthlyRate, double freeMileage, double priceExtraKm, String registrationNumber, String color, double lossDamage, String status, LocalDateTime timestamp) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.slideImage = slideImage;
        this.interiorImage = interiorImage;
        this.passengers = passengers;
        this.transmission = transmission;
        this.fuel = fuel;
        this.dailyRate = dailyRate;
        this.monthlyRate = monthlyRate;
        this.freeMileage = freeMileage;
        this.priceExtraKm = priceExtraKm;
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.lossDamage = lossDamage;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Car(String id, String brand, String type, File frontImage, File backImage, File slideImage, File interiorImage, int passengers, String transmission, String fuel, double dailyRate, double monthlyRate, double freeMileage, double priceExtraKm, String registrationNumber, String color, double lossDamage, String status, LocalDateTime timestamp, List<Booking> bookings) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.slideImage = slideImage;
        this.interiorImage = interiorImage;
        this.passengers = passengers;
        this.transmission = transmission;
        this.fuel = fuel;
        this.dailyRate = dailyRate;
        this.monthlyRate = monthlyRate;
        this.freeMileage = freeMileage;
        this.priceExtraKm = priceExtraKm;
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.lossDamage = lossDamage;
        this.status = status;
        this.timestamp = timestamp;
        this.bookings = bookings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public File getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(File frontImage) {
        this.frontImage = frontImage;
    }

    public File getBackImage() {
        return backImage;
    }

    public void setBackImage(File backImage) {
        this.backImage = backImage;
    }

    public File getSlideImage() {
        return slideImage;
    }

    public void setSlideImage(File slideImage) {
        this.slideImage = slideImage;
    }

    public File getInteriorImage() {
        return interiorImage;
    }

    public void setInteriorImage(File interiorImage) {
        this.interiorImage = interiorImage;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public double getFreeMileage() {
        return freeMileage;
    }

    public void setFreeMileage(double freeMileage) {
        this.freeMileage = freeMileage;
    }

    public double getPriceExtraKm() {
        return priceExtraKm;
    }

    public void setPriceExtraKm(double priceExtraKm) {
        this.priceExtraKm = priceExtraKm;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getLossDamage() {
        return lossDamage;
    }

    public void setLossDamage(double lossDamage) {
        this.lossDamage = lossDamage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", frontImage=" + frontImage +
                ", backImage=" + backImage +
                ", slideImage=" + slideImage +
                ", interiorImage=" + interiorImage +
                ", passengers=" + passengers +
                ", transmission='" + transmission + '\'' +
                ", fuel='" + fuel + '\'' +
                ", dailyRate=" + dailyRate +
                ", monthlyRate=" + monthlyRate +
                ", freeMileage=" + freeMileage +
                ", priceExtraKm=" + priceExtraKm +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", color='" + color + '\'' +
                ", lossDamage=" + lossDamage +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
