import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Boolean flag;

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>(3);
        map.put("asd", "123");
        System.out.println(map);
        setExtra(map);
        System.out.println(map);
    }

    private static void setExtra(Map<String, String> map) {
        map.put("awsd", "435");
    }

    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= (1 << 30)) ? (1 << 30) : n + 1;
    }

}
