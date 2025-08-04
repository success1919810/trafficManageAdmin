package org.example.trafficmanageadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.trafficmanageadmin.entity.User;

public interface UserService extends IService<User> {
    User UserLogin(User user);

    int searchByPhone(User user);

    int updatePassword(User user);

    int userInsert(User user);

    int getByPhone(String phone);

    User UserLoginByPhone(User user);

}
