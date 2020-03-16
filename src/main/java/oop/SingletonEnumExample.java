package oop;

/**
 * @author Richard_yyf
 * @version 1.0 2019/6/23
 */
public enum SingletonEnumExample {

    /** test singleton */
    SINGLETON;

    public String getString(Object o) {
        return o.toString();
    }
}
