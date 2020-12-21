package com.hc.mybatis.spring.dao;

import com.hc.mybatis.spring.entity.UserEntity;

import java.util.List;

/**
 * @author 李晓冰
 * @date 2020年12月21日
 */
public interface UserMapper {
    Integer count();

    List<UserEntity> listAll();
}
