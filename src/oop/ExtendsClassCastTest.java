package oop;

/**
 * 测试 父类 子类转型
 * @author Richard_yyf
 * @version 1.0 2019/6/25
 */
public class ExtendsClassCastTest {

    public static void main(String[] args) {
        ChildClass childClass = genResp();
        System.out.println(childClass.getCode());
    }

    public static <T> T genResp() {
        SuperClass superClass = new SuperClass();
        superClass.setCode("1");
        // class cast here is not Allowed, 只能 向上转型
        // 向下转型 -- 多态 特性
        return (T) superClass;
    }
}

class SuperClass {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

class ChildClass extends SuperClass{

}
