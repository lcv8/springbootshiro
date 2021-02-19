package com.jpp.service.impl;

import com.jpp.dao.UserDao;
import com.jpp.entity.Pers;
import com.jpp.entity.Role;
import com.jpp.entity.User;
import com.jpp.service.UserService;
import com.jpp.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lcv8
 * @date 2021.02.19
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        //生成随机盐
        String salt = SaltUtils.getSalt(4);
        //保存随机盐到数据库
        user.setSalt(salt);
        //md5加密+散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findRolesByUsername(String username) {

        return userDao.findRolesByUsername(username);
    }

    @Override
    public List<Pers> findPerByRoleId(String id) {
        return userDao.findPerByRoleId(id);
    }
}
