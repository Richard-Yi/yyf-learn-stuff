package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:+UseSerialGC -Xms200M -Xmx200M -Xmn32m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * -XX:+UseG1GC
 * @author Richard_yyf
 * @version 1.0 2020/1/6
 */
public class SimulateFGC {

    public static void main(String[] args) {
        //模拟full gc场景
        //场景1 使用System.gc
        List<Object> l = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            l.add(new byte[1024 * 1024]);
            if (i % 10 == 0) {
                System.gc();
            }
        }
    }
}
