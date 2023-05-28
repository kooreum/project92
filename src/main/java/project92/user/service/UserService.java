package project92.user.service;

import project92.user.dto.UserDTO;

public interface UserService {

    int idCheck(String username);
    void registerUser(UserDTO userDTO);
    void updateUserView(UserDTO userDTO);
    void updateUser(UserDTO userDTO, String password, String newPassword);
    void deleteUser(UserDTO userDTO);

}
