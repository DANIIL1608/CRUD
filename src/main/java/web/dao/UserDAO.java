package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();
    void save(User user);
    User findById(int id);
    void delete(int id);
}
