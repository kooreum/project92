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
public class Notice {
    private Integer no;
    private String adminId;
    private String managerId;
    private String id;
    private Integer type;
    private String msg;

    private LocalDateTime rdate;

    public String getFormattedRDate() {
        if (rdate != null) {
            return rdate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }
        return null;
    }
}

