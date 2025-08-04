package org.example.trafficmanageadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.trafficmanageadmin.entity.User;

@Mapper
public interface LoginMapper extends BaseMapper<User> {
    @Select("select * from user where username=#{username} or password=#{password}")
    User getByUsernameAndPassword(User user);
    @Select("select count(*) from user where phone=#{phone}")
    int countByPhone(User user);
    @Update("update user set password = #{password},updated_at=#{updatedAt} where phone=#{phone}")
    int updatePassword(User user);
    @Select("select * from user where phone=#{phone} and password=#{password} ")
    User getByPhoneAndPassword(User user);
}
