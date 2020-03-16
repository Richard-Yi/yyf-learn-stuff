package jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @author Richard_yyf
 * @version 1.0 2019/10/28
 */
public class Main {

    public static void main(String[] args) {
        SleepService target = new SleepServiceImpl();

        SleepTimeCountHandler handler = new SleepTimeCountHandler(target);

        SleepService proxyService = (SleepService) Proxy.newProxyInstance(target.getClass().getClassLoader()
                , target.getClass().getInterfaces(), handler);

        proxyService.sleep();
    }
}
