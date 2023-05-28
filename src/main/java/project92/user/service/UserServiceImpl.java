package project92.user.service;

import project92.user.dto.UserDTO;
import project92.user.entity.Role;
import project92.user.entity.User;
import project92.user.repository.RoleRepository;
import project92.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int idCheck(String username) {
        boolean exists = userRepository.existsByUsername(username);
        return exists ? 1 : 0; // username이 존재하면 return 1
    }

    @Override
    @Transactional
    public void registerUser(UserDTO userDTO) {

        Role role = roleRepository.findByRoleName(Role.RoleName.valueOf(userDTO.getRoleName()));
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        User user = new User();
        // DTO에서 사용자 엔티티로 필드 매핑
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setRole(role);
        user.setIsDeleted(userDTO.getIsDeleted());
        user.setDeleteDate(userDTO.getDeleteDate());
        user.setCreationDate(userDTO.getCreationDate());

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUserView(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Role role = roleRepository.findByRoleName(Role.RoleName.valueOf(userDTO.getRoleName()));
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setRole(role);
        user.setIsDeleted(userDTO.getIsDeleted());
        user.setDeleteDate(userDTO.getDeleteDate());

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO, String password, String newPassword) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        log.info("현재 비밀번호길이: " + (password != null ? password.length() : "null"));
        log.info("해쉬 암호길이: " + (user.getPassword() != null ? user.getPassword().length() : "null"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }
        if(newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        } else {
            user.setIsDeleted("Y");
            user.setDeleteDate(LocalDateTime.now());
            userRepository.save(user);
        }
    }

}
