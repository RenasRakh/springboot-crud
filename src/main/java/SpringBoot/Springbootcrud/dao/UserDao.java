package SpringBoot.Springbootcrud.dao;

import SpringBoot.Springbootcrud.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUsers();
    void add(User user);
    void delete(User user);
    void edit(int id, User user);
    User getById(int id);
    User getUserByUserName(String username);
    void start();

}
