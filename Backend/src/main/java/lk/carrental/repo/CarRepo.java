package lk.carrental.repo;

import lk.carrental.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepo extends JpaRepository<Car, String> {
    @Query(value = "SELECT DISTINCT passengers FROM Car")
    List<Integer> getDistinctPassengers();

    @Query(value = "SELECT DISTINCT transmission FROM Car")
    List<String> getDistinctTransmission();

    @Query(value = "SELECT DISTINCT brand FROM Car")
    List<String> getDistinctBrand();

    @Query(value = "SELECT DISTINCT type FROM Car")
    List<String> getDistinctType();

    @Query(value = "SELECT DISTINCT dailyRate FROM Car")
    List<Double> getDistinctPrice();

    @Query(value = "SELECT DISTINCT fuel FROM Car")
    List<String> getDistinctFuel();

    List<Car> getCarsByStatus(String status);
}
