package com.home;


public interface UserDao {

    void save(User user);

    User getByName(String name);

    void deleteByName(String name);

}
