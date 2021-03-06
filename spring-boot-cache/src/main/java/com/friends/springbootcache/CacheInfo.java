package com.friends.springbootcache;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@Component
public class CacheInfo {

    //将所有缓存封装到RModel对象中
    @Autowired
    ApplicationContext applicationContext;
    public RModel cache(){
        Map<String,Map<String,String>>   rCache= new HashMap<>();
        ConcurrentMapCacheManager bean = applicationContext.getBean(ConcurrentMapCacheManager.class);//获取内存管理器(cacheManager)
        Collection<String> cacheNames = bean.getCacheNames();//获取指定key内存
        cacheNames.stream().forEach(x->{
            rCache.put(x,new HashMap<>());
            ConcurrentMapCache cache    = (ConcurrentMapCache)bean.getCache(x);//获取指定cache
            ConcurrentMap<Object, Object> nativeCache = cache.getNativeCache();//获取cache存储map
            nativeCache.forEach((y,z)-> rCache.get(x).put(y.toString(),z.toString()));//获取map中的key和value
        });
        return new RModel(null,rCache);
    }

    @Data
    @AllArgsConstructor
    class RModel{
        private Object rDate;
        private Map<String,Map<String,String>> cacheDate;
    }
}
