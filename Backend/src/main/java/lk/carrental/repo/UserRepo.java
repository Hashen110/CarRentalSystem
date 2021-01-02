package lk.carrental.repo;

import lk.carrental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, String> {
    User getUserByUsernameAndPassword(String username, String password);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserByNicNumber(String nicNumber);
    User getUserByDrivingLicenseNumber(String drivingLicenseNumber);
    List<User> getUsersByStatus(String status);
}
