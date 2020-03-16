package jdkproxy;

/**
 * @author Richard_yyf
 * @version 1.0 2019/10/28
 */
public class SleepServiceImpl implements SleepService {

    @Override
    public void sleep() {
        System.out.println("模拟睡觉阶段");
        try {
            Thread.sleep(40);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
