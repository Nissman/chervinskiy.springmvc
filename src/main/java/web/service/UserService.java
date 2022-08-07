package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(User user);

    boolean deleteById(Long id);

    void edit(User user);

    List<User> findAll();

    User findByID(Long id);

    User findByUsername(String username);
}
