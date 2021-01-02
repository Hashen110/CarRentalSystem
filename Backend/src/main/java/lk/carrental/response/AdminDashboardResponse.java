package lk.carrental.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminDashboardResponse {
    private long registeredUsers;
    private long totalBookings;
    private long availableCars;
    private long activeBookings;
    private long availableDrivers;
    private long totalCars;
    private long carsNeedMaintenance;
    private long carsUnderMaintenance;
    private double dailyIncome;
    private double weeklyIncome;
    private double monthlyIncome;
    private double yearlyIncome;
}
