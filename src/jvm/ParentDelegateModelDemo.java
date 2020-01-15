package jvm;

/**
 * @author Richard_yyf
 * @version 1.0 2019/10/15
 */
public class ParentDelegateModelDemo {

    class Parent {
        String pName;

        private Parent(String pName) {
            this.pName = pName;
        }
    }

    class Child extends Parent {

        String name;

        private Child(String pName) {
            super(pName);
        }

        public Child(String pName, String name) {
            super(pName);
            this.name = name;
        }
    }
}
