package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-01 22:32:30
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}

