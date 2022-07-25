package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void delete(Long id);

    void edit(User user);

    List<User> getAllUser();

    User getById(Long id);
}
