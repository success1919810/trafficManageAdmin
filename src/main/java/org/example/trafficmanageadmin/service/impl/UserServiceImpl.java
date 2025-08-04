package org.example.trafficmanageadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.trafficmanageadmin.entity.User;
import org.example.trafficmanageadmin.mapper.LoginMapper;
import org.example.trafficmanageadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<LoginMapper, User> implements UserService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public User UserLogin(User user) {
        if(user.getPhone()==null){
            return loginMapper.getByUsernameAndPassword(user);
        }
        return loginMapper.getByPhoneAndPassword(user);
    }

    @Override
    public int searchByPhone(User user) {
        return loginMapper.countByPhone(user);
    }

    @Override
    public int updatePassword(User user) {
        return loginMapper.updatePassword(user);
    }

    @Override
    public int userInsert(User user) {

        return loginMapper.insert(user);
    }

    @Override
    public int getByPhone(String phone) {
        User user = loginMapper.selectOne(new QueryWrapper<User>().eq("phone", phone));
        return user != null ? 1 : 0;
    }

    @Override
    public User UserLoginByPhone(User user) {
        return loginMapper.getByPhoneAndPassword(user);
    }

}
