package com.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.*;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private Jedis jedis;

    public void save(User user) {

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream) ) {

            objectOutputStream.writeObject(user);

            byte[] bytes = byteArrayOutputStream.toByteArray();

            jedis.hset("user_table".getBytes(), user.getName().getBytes(), bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getByName(String name) {

        byte[] hget = jedis.hget("user_table".getBytes(), name.getBytes());

        if (hget == null){
            return null;
        }

        User user = null;
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(hget);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {

            user = (User)objectInputStream.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return user;

    }

    public void deleteByName(String name) {

        jedis.hdel("user_table", name);

    }
}
