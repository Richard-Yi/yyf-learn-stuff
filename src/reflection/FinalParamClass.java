package reflection;

/**
 * @author Richard_yyf
 * @version 1.0 2019/10/7
 */
public class FinalParamClass {

    // case 1
/*    private final String FINAL_VALUE = "FINAL";

    public String getFinalValue() {
        return FINAL_VALUE;
    }*/

    // case 2 构造函数
   /* private final String FINAL_VALUE;

    public FinalParamClass() {
        this.FINAL_VALUE = "FINAL";
    }

    public String getFinalValue() {
        return FINAL_VALUE;
    }*/
    // case 3 三目运算符
    private final String FINAL_VALUE = null == null ? "FINAL" : null;

    public String getFinalValue() {
        return FINAL_VALUE;
    }
}
