import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class main {
    public static void main (String[] args) {
        HashMap<String, String> a = new HashMap<> ();
        System.out.println (a.compute (null, new BiFunction<String, String, String> () {
            @Override
            public String apply (String s, String s2) {
                return "ddddddddddddd"+s2;
            }
        }));
        System.out.println (a.compute ("a", new BiFunction<String, String, String> () {
            @Override
            public String apply (String s, String s2) {
                return "ddddddddddddd"+"    "+s2+s;
            }
        }));
        System.out.println (a);
//        System.out.println (a.putIfAbsent ("a", "3"));
//        System.out.println (a);
        System.out.println ("start test");
        MyMap<String, String> myMap = new MyMap<> ();
        System.out.println (myMap.compute (null, new BiFunction<String, String, String> () {
            @Override
            public String apply (String s, String s2) {
                return "ddddddddddddd"+s2;
            }
        }));
        System.out.println (myMap.compute ("a", new BiFunction<String, String, String> () {
            @Override
            public String apply (String s, String s2) {
                return "ddddddddddddd"+"    "+s2+s;
            }
        }));
        System.out.println (myMap);
    }
}
