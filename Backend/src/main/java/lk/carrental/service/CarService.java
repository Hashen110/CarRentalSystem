package lk.carrental.service;

import lk.carrental.dto.CarDto;
import lk.carrental.dto.CarHomeDto;

import java.util.List;

public interface CarService {
    CarHomeDto getCars(boolean needSort);
    boolean addCar(CarDto carDto);
    CarDto getCar(String id);
}
