package lk.carrental.service;

import lk.carrental.dto.DriverDto;
import lk.carrental.dto.ScheduleDto;

import java.util.List;

public interface DriverService {
    boolean addDriver(DriverDto driverDto);
    boolean deleteDriver(String id);
    List<ScheduleDto> getSchedule(String id);
}
