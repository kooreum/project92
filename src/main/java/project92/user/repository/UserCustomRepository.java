package project92.user.repository;

import project92.user.entity.User;

import java.util.List;

public interface UserCustomRepository {

    List<User> findByEmail(String email);

    User findByUsername(String username);

    boolean existsByUsername(String username);

}
