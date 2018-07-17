package com.lyj.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;
@Intercepts({
        @Signature(type = StatementHandler.class,method ="parameterize",args=java.sql.Statement.class )
})
public class MySecondPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("来吧，我的第二个执行逻辑");
        Object proceed = invocation.proceed();
        return proceed;
    }

    @Override
    public Object plugin(Object o) {
        System.out.println("来吧，我的第二个插件，我要包装你了");
        Object wrap = Plugin.wrap(o, this);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
