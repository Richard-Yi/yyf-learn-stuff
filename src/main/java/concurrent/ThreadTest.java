package concurrent;

import concurrent.lock.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Richard_yyf
 * @version 1.0 2019/9/22
 */
public class ThreadTest {

    private static final Map<String, String> beanDefinitionMap = new ConcurrentHashMap<>(256);

    private static volatile List<String> beanDefinitionNames = new ArrayList<>(256);

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        service.execute(() ->{
            synchronized (beanDefinitionMap) {
                beanDefinitionMap.put("test","test1");
                List<String> list = new ArrayList<>(1);
                list.add("1");
                beanDefinitionNames = list;
                System.out.println("countdown before");
                countDownLatch.countDown();
                System.out.println("countdown after");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread sleep finish");
                System.out.println(beanDefinitionMap.get("test"));
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("222222");
        System.out.println(beanDefinitionMap.get("test"));
        beanDefinitionMap.put("test","test3");
        System.out.println(beanDefinitionMap.get("test"));
        beanDefinitionNames.add("2");

        ThreadPoolUtil.tryReleasePool(service);
    }


}
