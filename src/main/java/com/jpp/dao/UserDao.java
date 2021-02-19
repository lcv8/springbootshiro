package com.jpp.dao;

import com.jpp.entity.Pers;
import com.jpp.entity.Role;
import com.jpp.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lcv8
 * @date 2021.02.19
 */
@Repository
public interface UserDao {
    void save(User user);

    User findByUsername(String username);

    User findRolesByUsername(String username);

    List<Pers> findPerByRoleId(String id);
}
