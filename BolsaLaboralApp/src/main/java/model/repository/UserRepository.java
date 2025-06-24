package model.repository;
import model.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserById(int id);
    List<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    List<User> findAllUsers();
    void deleteUser(int id);
    User updateUser(User user);
    boolean authenticateUser(String email, String password);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    User saveUser(User user);
}
