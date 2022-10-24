package com.apijavacourse.apijavacourse.controllers;

import com.apijavacourse.apijavacourse.dao.UserDao;
import com.apijavacourse.apijavacourse.models.User;
import com.apijavacourse.apijavacourse.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> listUsers(@RequestHeader(value = "Authorization") String token) {

        if(!checkToken(token)) return null;

        return userDao.getUsers();

    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public User getUser(@RequestHeader(value = "Authorization") String token,
                        @PathVariable Long id) {

        if(!checkToken(token)) return null;

        User user = new User();
        user.setId(id);
        user.setName("Dave");
        user.setLastname("Mimic");
        user.setEmail("dave.mimic@test.com");
        user.setPhone("3112342334");
        return user;

    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(10, 1024, 1, user.getPassword().toCharArray());

        user.setPassword(hash);
        userDao.register(user);
    }

    @RequestMapping(value = "api/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String token,
                           @PathVariable Long id) {
        if(!checkToken(token)) return;

        userDao.remove(id);
    }

    private boolean checkToken(String token) {

        String userId = jwtUtil.getKey(token);
        return userId != null;
    }

}