package org.example.trafficmanageadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.trafficmanageadmin.entity.User;
import org.example.trafficmanageadmin.mapper.LoginMapper;
import org.example.trafficmanageadmin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, User> implements LoginService  {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public User UserLogin(User user) {
        if(user.getPhone()==null){
            User userBack = loginMapper.getByUsernameAndPassword(user);
            return userBack;
        }
            User userBack = loginMapper.getByPhoneAndPassword(user);
        return  userBack;
    }

    @Override
    public int searchByPhone(User user) {
        int count = loginMapper.countByPhone(user);
        return count;
    }

    @Override
    public int updatePassword(User user) {
        int count=loginMapper.updatePassword(user);
        return count;
    }

    @Override
    public int userInsert(User user) {

        int count = loginMapper.insert(user);
        return count;
    }

    @Override
    public int getByPhone(String phone) {
        User user = loginMapper.selectOne(new QueryWrapper<User>().eq("phone", phone));
        return user != null ? 1 : 0;
    }

    @Override
    public User UserLoginByPhone(User user) {
        User userBack =loginMapper.getByPhoneAndPassword(user);
        return userBack;
    }

}
