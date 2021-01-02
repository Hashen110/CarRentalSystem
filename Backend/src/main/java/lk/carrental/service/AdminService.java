package lk.carrental.service;

import lk.carrental.response.*;

public interface AdminService {
    AdminDashboardResponse getDashboardData();

    AdminUserResponse getUserData();

    boolean userApproved(String id);

    boolean userReject(String id, String message);

    boolean userDelete(String id);

    AdminCarResponse getCarData();

    AdminDriverResponse getDriverData();

    AdminBookingResponse getBookingData();

    boolean changeBookingStatus(String id, String status);
}
