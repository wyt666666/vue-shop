package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.JwtUtils;
import com.example.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-09-01 22:32:31
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public boolean findByName(User user) {
        List<User> users = this.list();
        for (User user1 : users) {
            if (user1.getName().equals(user.getName())
                    && user1.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Result register(User user) {
        List<User> uList = this.list();
        for (User user1 : uList) {
            if (user.getName().equals(user1.getName())){
                return Result.forbidden("用户名重复");
            } else if (!user.getPassword().equals(user.getRe_password())){
                return Result.forbidden("两次密码不一致");
            }
        }
        this.save(user);
        String token = JwtUtils.generateToken(user.getName());
        return Result.success(token,"token");
    }

}

