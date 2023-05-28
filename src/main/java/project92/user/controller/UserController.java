package project92.user.controller;

import project92.user.dto.UserDTO;
import project92.user.entity.User;
import project92.user.repository.UserRepository;
import project92.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;



@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;



    @GetMapping("/deleteUser")
    public String showDeleteUserForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String deleteUsername = authentication.getName();

        log.info("username: {}", deleteUsername);
        User user = userRepository.findByUsername(deleteUsername);
        if (user != null) {
            model.addAttribute("user", user);
            return "users/users_delete";
        } else {
            log.info("아이디를 찾을 수 없습니다: {}", deleteUsername);
            throw new UsernameNotFoundException("아이디를 찾을 수 없습니다: " + deleteUsername);
        }
    }
    @PostMapping("/deleteUser")
    @ResponseBody
    public Map<String, String> deleteUser(@RequestBody UserDTO userDTO) {
        Map<String, String> response = new HashMap<>();
        try {
            userService.deleteUser(userDTO);
            response.put("status", "SUCCESS");
        } catch (RuntimeException e) {
            response.put("status", "ERROR");
            response.put("message", "비밀번호가 일치하지않습니다.");
        }
        return response;
    }



    @GetMapping("/updateUser")
    public String showUpdateUserForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        log.info("username: {}", currentUsername);
        User user = userRepository.findByUsername(currentUsername);
        if (user != null) {
            model.addAttribute("user", user);
            return "users/users_update";
        } else {
            log.info("아이디를 찾을 수 없습니다: {}", currentUsername);
            throw new UsernameNotFoundException("아이디를 찾을 수 없습니다: " + currentUsername);
        }
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public Map<String, String> updateUser(@RequestBody UserDTO userDTO) {
        Map<String, String> response = new HashMap<>();
        try {
            userService.updateUser(userDTO, userDTO.getPassword(), userDTO.getNewPassword());
            response.put("status", "SUCCESS");
        } catch (RuntimeException e) {
            response.put("status", "ERROR");
            response.put("message", "비밀번호가 일치하지않습니다.");
        }
        return response;
    }


    // 로그인
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error != null);
        return "users/login";
    }

    //아이디 중복체크
    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("username") String username) {
        log.info("username 확인 : {}", username);
        int cnt = userService.idCheck(username);
        return cnt;
    }
    /**
     * 회원가입
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "users/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDTO userDTO) {
        try {
            userService.registerUser(userDTO);
            return "redirect:/users/login";
        } catch (RuntimeException ex) {
            return "error";
        }
    }


}
