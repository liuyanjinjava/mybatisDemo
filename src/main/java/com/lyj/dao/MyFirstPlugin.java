package com.lyj.dao;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;
/**
 * 完成了插件的签名；
 * 告诉mybatis，当前插件用来拦截哪个对象的哪个方法，
 */
@Intercepts({
        @Signature(type = StatementHandler.class,method ="parameterize",args=java.sql.Statement.class )
})
public class MyFirstPlugin implements Interceptor {

    /**
     * Inteceptor:拦截，拦截目标对象的目标方法的执行；
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //拿到要拦截的目标方法；
        System.out.println("MyFirstPlugin.....intercept"+"拿到要拦截的目标方法"+invocation.getMethod());
        //动态的改变一下sql运行的参数：以前查询的是一号员工，实际从数据库查3号员工；
        Object target = invocation.getTarget();
        System.out.println("当前拦截的对象："+target);
        //拿到：StatementHandler==>ParameterHandler===>parameterObject的值
        //拿到target的源数据
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql语句用的参数是"+value);
        //修改完sql语句要用的参数之后，让他的目标方法执行；
         metaObject.setValue("parameterHandler.parameterObject",4);
        //执行目标方法
        Object proceed=invocation.proceed();

        //返回执行后的返回值

        return proceed;
    }
    /**
     *包装目标对象，为目标对象创建一个代理对象；
     * 代理对象里也可以添加一些执行逻辑单元；
     */
    @Override
    public Object plugin(Object o) {
        //我们可以借助Plugin的wrap方法使用当前Interceptor包装我们目标对象，
        System.out.println("MyFirstPlugin....将要包装的对象"+o);
        Object wrap = Plugin.wrap(o, this);
        //返回当前目标创建的代理对象；
        return wrap;
    }
    /**
     *setProperties，将当前插件配置的信息拿到；
     */

    @Override
    public void setProperties(Properties properties) {
        System.out.println("进来了么");
        System.out.println("插件配置的信息-----"+properties);
    }
}

