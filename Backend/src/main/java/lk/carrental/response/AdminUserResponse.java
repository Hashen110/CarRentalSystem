package lk.carrental.response;

import lk.carrental.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminUserResponse {
    private long totalUsers;
    private long totalPendingUsers;
    private long totalAdminUsers;
    private List<UserDto> users;
    private List<UserDto> userRequests;
}
