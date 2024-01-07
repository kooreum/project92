package project92.common.model;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
public class Member {

        private String id;
        private String password;
        private String confirmPasswd;
        private String name;
        private String email;
        private String mobile;
        private Levels Level;
        private Integer status;
        private LocalDateTime rdate; //최근로그인시간
        private LocalDateTime ldate; //마지막로그아웃시간

        public String getFormattedRDate() {
                if (rdate != null) {
                        return rdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                }
                return null;
        }

}
