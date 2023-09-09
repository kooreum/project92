package project92.common.model;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {

        private String id;
        private String adminId;
        private String managerId;
        private String password;
        private String confirmPasswd;
        private String dePassword;
        private String company;
        private String name;
        private String email;
        private String mobile;
        private Integer level;
        private Integer status;
        private LocalDateTime rdate;
        private LocalDateTime ldate;

        public String getFormattedRDate() {
                if (rdate != null) {
                        return rdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                }
                return null;
        }

}
