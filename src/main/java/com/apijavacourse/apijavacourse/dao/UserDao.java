package com.apijavacourse.apijavacourse.dao;

import com.apijavacourse.apijavacourse.models.User;

import java.util.List;

public interface UserDao {

    public List<User> getUsers();

    void remove(Long id);

    void register(User user);

    User findUserByCredentials(User user);
}
