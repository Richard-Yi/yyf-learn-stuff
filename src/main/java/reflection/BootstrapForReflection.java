package reflection;

import java.lang.reflect.*;

/**
 * @author Richard_yyf
 * @version 1.0 2019/10/6
 */
public class BootstrapForReflection {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        System.out.println("======== print fields ========");
        printFields();
        System.out.println("======== print declared fields ========");
        printDeclaredFields();
        System.out.println("======== print methods ========");
        printMethods();
        System.out.println("======== print declared methods ========");
        printDeclaredMethods();
        System.out.println("======== invoke private methods ========");
        invokePrivateMethods();
        System.out.println("======== modify private fields ========");
        modifyPrivateFields();
        System.out.println("======== modify static private fields ========");
        modifyStaticPrivateFields();
        System.out.println("======== modify final private fields ========");
        modifyFinalPrivateFields();
    }

    private static void modifyFinalPrivateFields() throws NoSuchFieldException, IllegalAccessException {
        FinalParamClass finalParamClass = new FinalParamClass();
        Class clz = finalParamClass.getClass();

        Field finalField = clz.getDeclaredField("FINAL_VALUE");

        if (finalField != null) {
            finalField.setAccessible(true);

            System.out.println("Before modify: FINAL_VALUE = " + finalField.get(finalParamClass));

            System.out.println("Before modify: getFinalValue() return value: " + finalParamClass.getFinalValue());

            finalField.set(finalParamClass, "Modified");

            System.out.println("After modify: FINAL_VALUE = " + finalField.get(finalParamClass));

            System.out.println("After modify: getFinalValue() return value: " + finalParamClass.getFinalValue());
        }
    }

    private static void modifyStaticPrivateFields() throws NoSuchFieldException, IllegalAccessException {
        Class clz = TestClass.class;
        Field staticPrivateField = clz.getDeclaredField("STATIC_MSG");

        // 操作私有变量
        if (staticPrivateField != null) {
            // 获取私有变量访问权
            staticPrivateField.setAccessible(true);

            // 修改私有变量
            System.out.println("before modify: STATIC_MSG = " + TestClass.getStaticMsg());

            // 使用set(Object, value) 修改变量的值
            staticPrivateField.set(null, "modified");
            System.out.println("after modify: STATIC_MSG = " + TestClass.getStaticMsg());
        }
    }

    private static void modifyPrivateFields() throws NoSuchFieldException, IllegalAccessException {
        TestClass testClass = new TestClass();
        Class clz = testClass.getClass();
        Field privateField = clz.getDeclaredField("MSG");

        // 操作私有变量
        if (privateField != null) {
            // 获取私有变量访问权
            privateField.setAccessible(true);

            // 修改私有变量
            System.out.println("before modify: MSG = " + testClass.getMsg());

            // 使用set(Object, value) 修改变量的值
            privateField.set(testClass, "modified");
            System.out.println("after modify: MSG = " + testClass.getMsg());
        }
    }

    /**
     * 通过反射获取类的所有变量
     */
    public static void printFields() {
        // 1. 获取并输出类的名称
        Class clz = SonClass.class;
        System.out.println("类的名称" + clz.getName());

        // 获取Public 访问权限变量
        Field[] fields = clz.getFields();
        for (Field field : fields) {
            // 获取访问权限并输出
            int modifiers = field.getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");
            // 输出变量的类型及变量名
            System.out.println(field.getType().getName() + " " + field.getName());
        }
    }

    /**
     * 通过反射获取类的所有变量
     */
    public static void printDeclaredFields() {
        // 1. 获取并输出类的名称
        Class clz = SonClass.class;
        System.out.println("类的名称" + clz.getName());

        // 获取所有声明的变量，不管访问权限
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            // 获取访问权限并输出
            int modifiers = field.getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");
            // 输出变量的类型及变量名
            System.out.println(field.getType().getName() + " " + field.getName());
        }
    }

    /**
     * 通过反射获取类的所有方法
     */
    public static void printMethods() {
        // 1. 获取并输出类的名称
        Class clz = SonClass.class;
        System.out.println("类的名称" + clz.getName());

        // 2. 获取本类的public方法，包括继承来的
        Method[] methods = clz.getMethods();

        printMethod(methods);
    }

    /**
     * 通过反射获取类的所有方法
     */
    public static void printDeclaredMethods() {
        // 1. 获取并输出类的名称
        Class clz = SonClass.class;
        System.out.println("类的名称" + clz.getName());

        // 2. 获取本类的public方法，包括继承来的
        Method[] methods = clz.getDeclaredMethods();

        printMethod(methods);
    }

    public static void invokePrivateMethods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //1. 获取 Class 类实例
        TestClass testClass = new TestClass();
        Class clz = testClass.getClass();
//        Class clz = TestClass.class;
        // 获取私有方法
        // 第一个参数 为 要获取的私有方法的名称
        // 第二个参数[Class<?>... parameterTypes] 为 要获取的方法的参数的类型，没有则为null
        Method privateMethod = clz.getDeclaredMethod("privateMethod", String.class, int.class);

        // 开始操作方法
        if (privateMethod != null) {
            // 获取私有方法的访问权
            privateMethod.setAccessible(true);

            // 使用 invoke 反射调用
            // 如果是static方法，则不用传入对象，传null即可
            privateMethod.invoke(testClass, "java reflect ", 123);
        }
    }

    private static void printMethod(Method[] methods) {
        for (Method method : methods) {
            // 获取并输出方法的访问权限
            int modifiers = method.getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");
            // 获取并输出方法的返回值类型
            Class returnType = method.getReturnType();
            System.out.print(returnType.getName() + " " + method.getName() + "(");
            // 获取并输出方法的所有参数
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.print(parameter.getType().getName() + " " + parameter.getName() + ",");
            }
            // 获取并输出方法抛出的异常
            Class[] exceptionTypes = method.getExceptionTypes();
            if (exceptionTypes.length == 0) {
                System.out.println(" )");
            } else {
                for (Class exceptionType : exceptionTypes) {
                    System.out.println(" ) throws " + exceptionType.getName());
                }
            }
        }
    }
}
