//package lambda;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
///**
// * @author Richard_yyf
// * @version 1.0 2019/8/7
// */
//public class StreamDemo2 {
//
//    public static void main(String[] args) {
//
//        List<Map<String, String>> list = new ArrayList<>(3);
//
//        Map<String, String> m = new HashMap<>(1);
//        m.put("name", "樟树街");
//
//        Map<String, String> m1 = new HashMap<>(1);
////        m1.put("name", "樟树街1");
//
//        Map<String, String> m2 = new HashMap<>(1);
//        m2.put("name", "樟树街2");
//
//        list.add(m);
//        list.add(m1);
//        list.add(m2);
//
//        String s = list.stream()
//                .filter(p -> p.get("name") != null)
//                .map(p -> p.get("name"))
//                .collect(Collectors.joining(","));
//
//        System.out.println(s);
//    }
//}
