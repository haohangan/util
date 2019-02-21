package shardTest.state;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;

public class LogProxy implements InvocationHandler {

    private Object target;

    public LogProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        Logger.getGlobal().info("step log enter");
        result = method.invoke(target,args);
        Logger.getGlobal().info("step log out");
        return result;
    }

}
