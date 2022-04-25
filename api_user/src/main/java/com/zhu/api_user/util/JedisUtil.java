package com.zhu.api_user.util;

import com.baomidou.mybatisplus.extension.service.IService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * 数据连接池的工具类
 * 目的 返回数据库连接Jedis
 * 读取配置文件 config.properties
 */
public class JedisUtil {
    private static JedisPool pool;
    static {
        //读取配置文件
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        String host = resourceBundle.getString("host");
        //获取端口号
        int port = Integer.parseInt(resourceBundle.getString("port"));

        //获取最大连接数
        int maxTotal = Integer.parseInt(resourceBundle.getString("maxTotal"));
        //获取闲时连接数
        int maxIdle = Integer.parseInt(resourceBundle.getString("maxIdle"));

        int timeout = Integer.parseInt(resourceBundle.getString("timeout"));
//        String password = resourceBundle.getString("password");

        //创建连接池配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);

        //创建连接池对象
        pool = new JedisPool(jedisPoolConfig, host, port, timeout, null);
    }
    //创建方法  连接池对象
    public static Jedis getJedis(){
        return pool.getResource();
    }

    //释放资源
    public static void close(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }

    private static void close(JedisPool pool){
        if (pool!=null){
            pool.close();
        }
    }

}
