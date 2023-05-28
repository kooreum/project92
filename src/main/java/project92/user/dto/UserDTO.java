package project92.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String roleName;
    private String email;
    private String phone;
    private String isDeleted;
    private LocalDateTime deleteDate;
    private LocalDateTime creationDate;

    // 비밀번호 확인
    private String newPassword;

}
