package com.apijavacourse.apijavacourse.controllers;

import com.apijavacourse.apijavacourse.dao.UserDao;
import com.apijavacourse.apijavacourse.models.User;
import com.apijavacourse.apijavacourse.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    UserDao userDao;

    @Autowired
    JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {

        User loggedUser = userDao.findUserByCredentials(user);

        if (loggedUser == null) {
            return "FAIL";
        }

        String tokenJwt = jwtUtil.create(String.valueOf(loggedUser.getId()), loggedUser.getEmail());
        return tokenJwt;

    }
}
