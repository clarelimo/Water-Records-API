package dao;

import models.User;

import java.util.List;

public interface UsersDao {
    //create
    void add(User bedRoom);

    //read
    List<User> getAll();
    User findById(int id);

    //delete
    void deleteById(int id);
    void clearAll();
}
