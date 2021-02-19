package com.jpp.service;

import com.jpp.entity.Pers;
import com.jpp.entity.Role;
import com.jpp.entity.User;

import java.util.List;

/**
 * @author lcv8
 * @data 2021.02.19
 */
public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User findRolesByUsername(String username);
    List<Pers> findPerByRoleId(String id);
}
