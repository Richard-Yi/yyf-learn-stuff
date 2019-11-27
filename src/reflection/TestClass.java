package reflection;

/**
 * @author Richard_yyf
 * @version 1.0 2019/10/7
 */
public class TestClass {

    private String MSG = "Original";

    private static String STATIC_MSG = "Original";

    private static void privateMethod(String head, int tail) {
        System.out.println(head + tail);
    }

    public String getMsg() {
        return MSG;
    }

    public static String getStaticMsg() {
        return STATIC_MSG;
    }
}
