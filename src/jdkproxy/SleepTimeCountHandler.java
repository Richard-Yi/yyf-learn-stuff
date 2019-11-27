package jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Richard_yyf
 * @version 1.0 2019/10/28
 */
public class SleepTimeCountHandler implements InvocationHandler {

    private Object target;

    public SleepTimeCountHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = method.invoke(target, args);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        return obj;
    }
}
