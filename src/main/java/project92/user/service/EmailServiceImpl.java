package project92.user.service;

import project92.user.entity.User;
import project92.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSenderImpl mailSender;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void sendEmailProtocol(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("emailformycode@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    @Override
    public void sendTemporaryPassword(String username, String email) {
        List<User> users = userRepository.findByEmail(email);
        boolean userFound = false;

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getIsDeleted().equals("Y")) {
                    throw new IllegalArgumentException("존재하지 않는 회원입니다.");
                }
                userFound = true;
                String temporaryPassword = generateRandomPassword();
                String hashedTemporaryPassword = passwordEncoder.encode(temporaryPassword);
                log.info("임시 암호: {}", temporaryPassword);
                log.info("임시 해쉬암호: {}", hashedTemporaryPassword);
                user.setPassword(hashedTemporaryPassword);
                userRepository.save(user);
                sendEmailProtocol(user.getEmail(), "임시비밀번호", "임시비밀번호: " + temporaryPassword);
                break;
            }
        }
        if (!userFound) {
            throw new UsernameNotFoundException("아이디와 이메일이 일치하지 않습니다");
        }
    }


    @Override
    public String generateRandomPassword() {
        int passwordLength = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        StringBuilder passwordBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < passwordLength; i++) {
            passwordBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return passwordBuilder.toString();
    }

}
