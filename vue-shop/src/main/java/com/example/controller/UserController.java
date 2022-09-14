package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.JwtUtils;
import com.example.utils.Result;
import com.example.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Autowired
    private UserDao userDao;
    /*
    * 登录
    * */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if("禁用".equals(user.getStatus())){
            return Result.forbidden("该账户已禁用");
        }
        if (userService.findByName(user)) {
            String token = JwtUtils.generateToken(user.getName());
            Map<Object, String> t = new HashMap<>();
            t.put("token", token);
            if ("admin".equals(user.getName())) {
                return Result.success(ResultCodeEnum.AMIS_LOGIN_SUCCESS,t,"admin");
            } else {
                return Result.success(ResultCodeEnum.AMIS_LOGIN_SUCCESS,t,"登陆成功");
            }
        }
        return Result.forbidden("用户名或密码错误");
    }
  /*
  * 退出
  *
  * */
    @PostMapping("/logout")
    public Result logout() {
        return Result.success();
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }
    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @PostMapping("/list")
    public Result<?> selectAll(@RequestBody User user) {
        IPage<User> page = new Page<>(user.getPage(), user.getPerPage());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(User::getId);
        userDao.selectPage(page, queryWrapper);
        return Result.success(page) ;
    }
    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果*/
    @PostMapping("/insert")
    public Result insert(@RequestBody User user) {
        String date = LocalDate.now().toString();
        user.setCtime(date);
        userService.save(user);
        return Result.success();
    }

    /** 账户状态
    * */
    @PostMapping("/c_status/{id}")
    public Result c_status(@PathVariable Integer id){
        User user = userService.getById(id);
        if ("启用".equals(user.getStatus())){
            user.setStatus("禁用");
        } else {
            user.setStatus("启用");
        }
        userService.updateById(user);
        return Result.success();
    }

/*    *
     * 修改数据
     *
     * @return 修改结果*/

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        if (ObjectUtils.isEmpty(user.getRole())){
            return Result.forbidden("请为用户设置权限");
        }
        if ("admin".equals(user.getRole())) {
            user.setRole("admin");
        } else {
            user.setRole("user");
        }
        userService.updateById(user);
        return Result.success();
    }
/*    *
     * 删除数据
     *
     * @return 删除结果*/

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.removeById(id);
        return Result.success();
    }

  /*  * 删除所有数据
    * */
    @DeleteMapping("/deleteByIds/{ids}")
    public Result delete_All(@PathVariable int[] ids){
        for (int id : ids) {
            userService.removeById(id);
        }
        return Result.success();
    }
    
}

