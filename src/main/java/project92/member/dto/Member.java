package project92.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Member {
    private Long id;
    private String login_id;
    private String password;
    private String name;
    private boolean delete_yn;
    private LocalDateTime created_date;
    private LocalDateTime modified_date;


}
