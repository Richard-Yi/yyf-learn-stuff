package concurrent.lock;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Richard_yyf
 * @version 1.0 2019/7/17
 */
public class ReentrantReadWriteLockExample {

    public static void main(String[] args) {

    }
    class Data {
        String name;
        String value;
    }

    /**
     * 可以作为缓存用处
     */
    class RWDictionary {
        /** 这个是一个预计会变得很大，并且会被并发访问的 TreeMap*/
        private final Map<String, Data> m = new TreeMap<>();
        private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        /** 读锁 */
        private final Lock r = rwl.readLock();
        /** 写锁 */
        private final Lock w = rwl.writeLock();

        public Data get(String key) {
            r.lock();
            try { return m.get(key); }
            finally { r.unlock(); }
        }
        public String[] allKeys() {
            r.lock();
            try { return (String[]) m.keySet().toArray(); }
            finally { r.unlock(); }
        }
        public Data put(String key, Data value) {
            w.lock();
            try { return m.put(key, value); }
            finally { w.unlock(); }
        }
        public void clear() {
            w.lock();
            try { m.clear(); }
            finally { w.unlock(); }
        }
    }
}

