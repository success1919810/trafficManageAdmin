package org.example.trafficmanageadmin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    // 设置缓存
    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }
    // 设置缓存并指定过期时间
    public void setTimeout(String key, Object value, long timeout, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,value,timeout,timeUnit);
    }
    // 获取缓存
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
    // 删除缓存
    public void delete(String key){
        redisTemplate.delete(key);
    }
    // 判断缓存是否存在
    public Boolean exists(String key){
        return redisTemplate.hasKey(key);
    }
    // 设置哈希表字段和值
    public void hSet(String key,String hashKey,Object value){
        redisTemplate.opsForHash().put(key,hashKey,value);
    }
    // 获取哈希表字段的值
    public Object hGet(String key,String hashKey){
        return redisTemplate.opsForHash().get(key,hashKey);
    }
    // 获取哈希表所有字段和值
    public Map<Object,Object> hGetAll(String key){
        return redisTemplate.opsForHash().entries(key);
    }
    // 删除哈希表字段
    public void hDelete(String key,String hashKey){
        redisTemplate.opsForHash().delete(key,hashKey);
    }
    // 向列表右侧添加元素
    public void lPushRight(String key,Object value){
        redisTemplate.opsForList().rightPush(key,value);
    }
    // 获取列表指定范围的元素
    public List<Object> lGetRange(String key, long start, long end){
        return redisTemplate.opsForList().range(key,start,end);
    }
    // 向集合中添加元素
    public void sAdd(String key,Object value){
        redisTemplate.opsForSet().add(key,value);
    }
    // 获取集合所有元素
    public Set<Object> sGetAll(String key){
        return redisTemplate.opsForSet().members(key);
    }
    // 对键进行自增操作
    public void incr(String key){
        redisTemplate.opsForValue().increment(key);
    }
    // 对键进行自减操作
    public void decr(String key){
        redisTemplate.opsForValue().decrement(key);
    }
}
