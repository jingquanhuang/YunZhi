package com.skylaker.yunzhi.service.impl;

import com.skylaker.yunzhi.config.GlobalConstant;
import com.skylaker.yunzhi.mappers.UserMapper;
import com.skylaker.yunzhi.pojo.RegisterInfo;
import com.skylaker.yunzhi.pojo.Role;
import com.skylaker.yunzhi.pojo.User;
import com.skylaker.yunzhi.service.UserService;
import com.skylaker.yunzhi.utils.BaseUtil;
import com.skylaker.yunzhi.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 用户处理逻辑类
 *
 * @author sky
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取所有用户
     *
     * @return 用户列表
     */
    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    /**
     * 根据用户名获取用户信息对象
     *
     * @param username 用户名
     * @return
     */
    @Override
    @Cacheable(value = "userCache", key = "#User.id")
    public User getUserByUserName(String username) {
        //TODO
        return null;
    }

    /**
     * 获取用户拥有的角色信息
     *
     * @param username 用户名
     * @return {set}     角色信息集合
     */
    @Override
    public Set<Role> getUserRoles(String username) {
        //TODO
        return null;
    }

    /**
     * 保存注册用户信息
     *
     * @param registerInfo  注册用户信息
     */
    @Override
    public void saveRegisterUser(RegisterInfo registerInfo) {
        User user = new User();
        user.setUsername(registerInfo.getUsername());
        user.setPassword(BaseUtil.getMD5(registerInfo.getPassword(), GlobalConstant.PASSWORD_ENCRYPT_COUNT));
        user.setPhone(registerInfo.getPhone());
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setLocked(false);

        userMapper.addUser(user);
    }
}
