package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    void deleteById(Long id);

    void edit(User user);

    List<User> findAll();

    User findByID(Long id);
}
