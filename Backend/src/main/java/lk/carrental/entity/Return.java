package lk.carrental.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "_Return")
public class Return {
    @Id
    private String id;
    private int distance;
    private int damage;
    private LocalDateTime timestamp;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking", referencedColumnName = "id")
    private Booking booking;

    public Return() {
    }

    public Return(String id, int distance, int damage, LocalDateTime timestamp, Booking booking) {
        this.id = id;
        this.distance = distance;
        this.damage = damage;
        this.timestamp = timestamp;
        this.booking = booking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "Return{" +
                "id='" + id + '\'' +
                ", distance=" + distance +
                ", damage=" + damage +
                ", timestamp=" + timestamp +
                '}';
    }
}
