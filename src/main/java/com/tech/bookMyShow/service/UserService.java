package com.tech.bookMyShow.service;

import com.tech.bookMyShow.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(User user);

    User findByUserName(String userName);
}
