package com.zhu.api_video.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.utils.MapUtils;
import net.dreamlu.mica.core.utils.StringUtil;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JedisCRUD {

    /**
     *
     * @param keyName Hash key
     * @param fieldKey Hash map key
     * @param column sql column
     * @param queryStrategy custom query strategy
     * @param clazz return class
     * @param <T>
     * @return
     */
    public static <T> List<T> doSomethingHash (String keyName, Object fieldKey,
                                               String column, QueryStrategy queryStrategy, Class<T> clazz){
        Jedis jedis = JedisUtil.getJedis();
        //判断field类型，确保类型一致
        String field;
        if(fieldKey instanceof String)
            field = (String) fieldKey;
        else
            field = String.valueOf(fieldKey);
        String hgetStr = jedis.hget(keyName, field);
        if(StringUtil.isEmpty(hgetStr)){
            List<T> list = queryStrategy.doSomething(column, Integer.parseInt(field));

            jedis.hset(keyName, field, JSON.toJSONString(list));
            jedis.expire(keyName, 10);
            jedis.close();
            return list;
        }
        return JSONObject.parseArray(hgetStr, clazz);
    }

    public static <T> T doSomethingHashOne (String keyName, Object fieldKey,
                                            String column, QueryStrategy queryStrategy, Class<T> clazz){
        Jedis jedis = JedisUtil.getJedis();
        //判断field类型，确保类型一致
        String field;
        if(fieldKey instanceof String)
            field = (String) fieldKey;
        else
            field = String.valueOf(fieldKey);
        String hgetStr = jedis.hget(keyName, field);
        if(StringUtil.isEmpty(hgetStr)){
            List<T> list = queryStrategy.doSomething(column, fieldKey);

            jedis.hset(keyName, field, JSON.toJSONString(list.get(0)));
            jedis.expire(keyName, 10);
            jedis.close();
            return list.get(0);
        }
        return JSONObject.parseObject(hgetStr, clazz);
    }

    public static <T> List<T> getAllHash(String keyName, QueryStrategy queryStrategy, Class<T> clazz){
        Jedis jedis = JedisUtil.getJedis();
        Map<String, String> stringMap = jedis.hgetAll(keyName);
        if(MapUtils.isEmpty(stringMap)){
            List<T> list = queryStrategy.doSomething(null, null);
            Map<String, String> collect = list.stream().collect(Collectors.toMap(t -> {
                        try {
                            Method getId = clazz.getMethod("getId");
                            Object invoke = getId.invoke(t);
                            return String.valueOf(invoke);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    },
                    t -> {
                        return JSON.toJSONString(t);
                    }));
            jedis.hmset(keyName, collect);
            jedis.expire(keyName, 10);
            jedis.close();
            return list;
        }
        jedis.close();
        return stringMap.values().stream().map(s -> {
            return JSON.parseObject(s, clazz);
        }).collect(Collectors.toList());
    }

    public static <T> Integer insertOrUpdate(String keyName, T t, QueryStrategy queryStrategy){
        Integer id = (Integer) queryStrategy.doSomething(null, t).get(0);

        if(id > 0){
            Jedis jedis = JedisUtil.getJedis();
            String str = jedis.hget(keyName, String.valueOf(id));
            if(!StringUtil.isEmpty(str))
                jedis.hdel(keyName, String.valueOf(id));
            jedis.close();
        }

        return id;
    }
}
