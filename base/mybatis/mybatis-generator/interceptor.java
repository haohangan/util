package com.bees.policysupport.inteceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

/**
 * Created by guihao on 2020-08-17 10:14
 * 数据权限拦截器
 *
 **/
@Intercepts({@Signature(type = Executor.class,method = "query",args = {MappedStatement.class,Object.class, RowBounds.class,ResultHandler.class})})
@Slf4j
@Component
public class DataPermissionInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object obj = invocation.getTarget();
        Object object = invocation.proceed();
        log.info("11111111111111111111111111111111");
        return object;
    }

    @Override
    public Object plugin(Object target) {
        log.info(target.getClass().toString());
        if (target instanceof Executor) {
            return Plugin.wrap(target,this);
        } else {
            return target;
        }
    }

}
