package lk.carrental.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Login {
    @Id
    private String id;
    private LocalDateTime timestamp;
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "driver", referencedColumnName = "id")
    private Driver driver;

    public Login() {
    }

    public Login(String id, LocalDateTime timestamp, User user, Driver driver) {
        this.id = id;
        this.timestamp = timestamp;
        this.user = user;
        this.driver = driver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", driver=" + driver +
                '}';
    }
}
