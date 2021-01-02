package lk.carrental.repo;

import lk.carrental.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepo extends JpaRepository<Driver, String> {
    List<Driver> getDriversByStatus(String status);
    Driver getDriverByUsernameAndPassword(String username, String password);
}
