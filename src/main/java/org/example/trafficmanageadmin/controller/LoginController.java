package org.example.trafficmanageadmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.trafficmanageadmin.entity.Result;
import org.example.trafficmanageadmin.entity.User;
import org.example.trafficmanageadmin.entity.vo.LoginRequest;
import org.example.trafficmanageadmin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping
    public Result userLogin(@RequestBody LoginRequest loginRequest){
        log.info("用户登录了:{}",loginRequest);
        User user = new User();
        user.setPassword(loginRequest.getPassword());
        User userBack = null;
        if(isPhone(loginRequest)){
            log.info("手机号登录");
            user.setPhone(loginRequest.getAccount());
            userBack = loginService.UserLoginByPhone(user);
        } else {
            log.info("用户名登录");
            user.setUsername(loginRequest.getAccount());
            userBack = loginService.UserLogin(user);
        }

        log.info("返回的用户信息为:{}",userBack);

        if(userBack == null){
            return Result.error("用户不存在，尝试注册");
        }

        // 检查用户名（仅在非手机号登录时）
        if(!isPhone(loginRequest) && !userBack.getUsername().equals(user.getUsername())){
            return Result.error("用户名错误，请尝试正确的用户名");
        }

        // 检查密码
        if (!userBack.getPassword().equals(user.getPassword())) {
            return Result.error("密码错误，重试");
        }

        return Result.success();
    }
    @PostMapping("/forget")
    public Result userForgetPassword(@RequestBody User user){
        log.info("发送过来的手机号为:{}",user.getPhone());
        int count = loginService.searchByPhone(user);
        if(count == 0){
            return Result.error("该手机号从未注册，请先注册");
        }
        return Result.success();
    }
    @PostMapping("/setNew")
    public Result userSetNewPassword(@RequestBody User user){
        log.info("发送过来的新密码是:{}",user.getPassword());
        log.info("发送过来的手机号是:{}",user.getPhone());
        user.setUpdatedAt(LocalDateTime.now());
        int count=loginService.updatePassword(user);
        if(count==0){
            return Result.error("出现未知错误");
        }
        return Result.success();
    }
    @PostMapping("/register")
    public Result userRegister(@RequestBody User user){
        log.info("用户开始注册:{}",user);
        int exist = loginService.getByPhone(user.getPhone());
        if(exist==1){
            return Result.error("该手机号已经注册，请尝试找回密码");
        }
        int count = loginService.userInsert(user);
        if(count ==0){
            return Result.error("出现未知错误，不能进行注册");
        }
        return Result.success();
    }

    public boolean isPhone(LoginRequest loginRequest){
        return loginRequest.getAccount().matches("^1[3-9]\\d{9}$");
    }
}
