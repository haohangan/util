package shardTest.state;

import java.lang.reflect.Proxy;

public class LogProxyTester {

    public static void main(String[] args) {
        BusinessClass bc = new BusinessClass();

        ClassLoader loader = bc.getClass().getClassLoader();
        Class<?>[]  intfs = bc.getClass().getInterfaces();
        LogProxy lp = new LogProxy(bc);

        BCinterface enhancer = (BCinterface)Proxy.newProxyInstance(loader,intfs,lp);
        enhancer.query();
    }
}
