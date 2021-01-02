package lk.carrental.repo;

import lk.carrental.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<Login, String> {
}
