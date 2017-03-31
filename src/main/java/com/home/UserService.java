package com.home;


public interface UserService {


    void save(User user);

    User getUserByName(String name);
}
