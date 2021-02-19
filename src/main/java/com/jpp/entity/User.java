package com.jpp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author lcv8
 * @date 2021.02.19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class User {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    //获取角色集合
    private List<Role> roles;
}
