package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2022-09-01 22:32:30
 */
@SuppressWarnings("serial")
@Data
public class User extends Model<User> {
    @TableId(type = IdType.AUTO)
    private Long id;
    //用户名
    private String name;
    //密码，加密存储
    private String password;
    //用户角色
    private String role;
    //再次输入密码
    @TableField(exist = false)
    private String re_password;
    //创建时间
    private String ctime;
    //账户状态
    private String status;
    //手机号码
    private String iphone;
    @TableField(exist = false)
    private int page;
    @TableField(exist = false)
    private int perPage;

}

