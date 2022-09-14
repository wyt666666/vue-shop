package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.User;
import com.example.utils.Result;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-09-01 22:32:30
 */
public interface UserService extends IService<User> {
    public boolean findByName(User user);

    public Result register(User user);

}

