package project92.user.controller;

import project92.user.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/findPassword")
    @ResponseBody
    public String sendTemporaryPassword(@RequestParam("username") String username,
                                        @RequestParam("email") String email) {
        log.info("임시 암호 보내기: {}, email: {}", username, email);
        try {
            emailService.sendTemporaryPassword(username, email);
            return "success";
        } catch (Exception e) {
            log.error("임시 암호를 보내는 중 오류 발생: {}", e.getMessage());
            return e.getMessage();
        }
    }

    @GetMapping("/findPassword")
    public String userPasswordFind() {
        log.info("비밀번호 찾기 사용자 페이지 표시");
        return "users/find_password";
    }

}
