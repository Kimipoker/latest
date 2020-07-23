package com.example.lamda;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MyLamda {
    public static void main(String[] args) {
        
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        Arrays.asList( "a", "b", "d" ).forEach(e -> System.out.println( e ) );
        //编译器会根据上下文来推测参数的类型，或者你也可以显示地指定参数类型，只需要将类型包在括号里
        Arrays.asList( "a", "b", "d" ).forEach( ( String e ) -> System.out.println( e ) );
        //一句省略return
        Arrays.asList( "a", "b", "d" ).sort(Comparator.naturalOrder());
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
            int result = e1.compareTo( e2 );
            return result;
        } );
        //lambda 写法
        new Thread(() -> System.out.println("lambda写法")).start();

        Callable<String> stringCallable = () -> "hi, no param";


        Function<String, String> stringStringFunction = (String name) -> "hi, " + name;
        Comparator<String> tComparator = Comparator.comparingInt(String::length);

        // 对象::实例方法，将lambda的参数当做方法的参数使用
        Consumer<String> sc = System.out::println;
        //等效
        Consumer<String> sc2 = (x) -> System.out.println(x);
        sc.accept("618, 狂欢happy");
        // 类::静态方法，将lambda的参数当做方法的参数使用
        //ClassName::staticMethod  类的静态方法：把表达式的参数值作为staticMethod方法的参数
        Function<Integer, String> sf = String::valueOf;
        //等效
        Function<Integer, String> sf2 = (x) -> String.valueOf(x);
        String apply1 = sf.apply(61888);


        //无参的构造方法就是类::实例方法模型，如：
         Supplier<User> us = User::new;
         //等效
         Supplier<User> us2 = () -> new User();
         //获取对象
         User user = us.get();


        //一个参数,参数类型不同则会编译出错
        Function<Integer, User> uf = id -> new User(id);
        //或加括号
        Function<Integer, User> uf2 = (id) -> new User(id);
        //等效
        Function<Integer, User> uf3 = (Integer id) -> new User(id);
        User apply = uf.apply(61888);

        //两个参数
        BiFunction<Integer, String, User> ubf = (id, name) -> new User(id, name);
        User 狂欢happy = ubf.apply(618, "狂欢happy");
    }





}

class User{
    private Integer id;
    private String name;

    public User(Integer id) {
        this.id = id;
    }
    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}





