package lk.carrental.service.impl;

import lk.carrental.dto.CarDto;
import lk.carrental.dto.CarHomeDto;
import lk.carrental.entity.Car;
import lk.carrental.repo.CarRepo;
import lk.carrental.service.CarService;
import lk.carrental.service.LoginService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    private LoginService loginService;
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CarHomeDto getCars(boolean needSort) {
        List<Car> cars = carRepo.findAll();
        if (cars == null | cars.size() == 0) return null;
        if (needSort) {
            List<CarDto> carDtos = new ArrayList<>();
            for (Car car : cars) {
                CarDto map = modelMapper.map(car, CarDto.class);
                carDtos.add(map);
            }
            return new CarHomeDto(carRepo.getDistinctPassengers(), carRepo.getDistinctTransmission(), carRepo.getDistinctBrand(),
                    carRepo.getDistinctType(), carRepo.getDistinctPrice(), carRepo.getDistinctFuel(), carDtos);
        }
        List<CarDto> carDtos = new ArrayList<>();
        for (Car car : cars) {
            CarDto map = modelMapper.map(car, CarDto.class);
            carDtos.add(map);
        }
        return new CarHomeDto(null, null, null, null, null, null, carDtos);
    }

    @Override
    public boolean addCar(CarDto carDto) {
        carDto.setId(UUID.randomUUID().toString());
        Car map = modelMapper.map(carDto, Car.class);
        map.setTimestamp(LocalDateTime.now());
        carRepo.save(map);
        return true;
    }

    @Override
    public CarDto getCar(String id) {
        Car car = carRepo.getOne(id);
        if (car == null) return null;
        car.setTimestamp(null);
        return modelMapper.map(car, CarDto.class);
    }
}
