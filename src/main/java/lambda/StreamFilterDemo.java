//package lambda;
//
//import java.io.Serializable;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//
///**
// * @author Richard_yyf
// * @version 1.0 2019/7/15
// */
//public class StreamFilterDemo {
//
//    public static void main(String[] args) {
//        List<Person> personList = new ArrayList<>();
//        Person p1 = new Person("xiaoming",1);
//        Person p2 = new Person("xiaoming",2);
////        Person p5 = new Person("xiaoming",5);
//        Person p3 = new Person("xiaoxin",4);
//        Person p4 = new Person("xiaoxin",5);
//        personList.add(p1);
//        personList.add(p2);
//        personList.add(p3);
//        personList.add(p4);
//
//        // 去重
//        List<Person> resultList = personList.stream()
//                .sorted((h1, h2) -> h2.getAge() - h1.getAge())
//                .filter(distinctByName(Person::getName))
////                .filter(distinctByNameAndAge(Person::getName, Person::getAge))
//                .collect(Collectors.toList());
//
////        List<Person> resultList2 = personList.stream()
////                .distinct()
////                .sorted((h1, h2) -> h2.getAge() - h1.getAge())
////                .collect(Collectors.toList());
//        System.out.println(personList);
//        System.out.println(resultList);
////        System.out.println(resultList2);
//
//        // 选出最大的
////        Person oldest = resultList.get(0);
////        System.out.println(oldest.toString());
////
////        System.out.println(personList);
////        personList.sort(Comparator.comparing(Person::getName).thenComparing(Person::getAge));
////        System.out.println(personList);
//    }
//
//
//    public static Predicate<Person> distinctByNameAndAge(Function<Person, String> keyExtractor, Function<Person, Integer> valExtractor) {
//        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>(3);
//        return p -> {
//            String key = keyExtractor.apply(p);
//            Integer val = valExtractor.apply(p);
//            Integer oldVal = map.get(key);
//            if (oldVal == null) {
//                map.put(key, val);
//                return true;
//            } else if (oldVal < val) {
//                map.put(key, val);
//                return true;
//            }
//            return false;
//        };
//    }
//
//    public static <T> Predicate<T> distinctByName(Function<? super T, ?> keyExtractor) {
//        Set<Object> seen = ConcurrentHashMap.newKeySet();
//        return t -> seen.add(keyExtractor.apply(t));
//    }
//
//
//    private static class Person implements Serializable {
//        String name;
//        int age;
//        public Person(String name,int age) {
//            this.name = name;
//            this.age = age;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//
//        @Override
//        public String toString() {
//            return "Person{" +
//                    "name='" + name + '\'' +
//                    ", age=" + age +
//                    '}';
//        }
//
//    }
//
//
//}
