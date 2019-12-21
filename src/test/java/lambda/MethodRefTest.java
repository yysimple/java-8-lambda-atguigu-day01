package lambda;

import cn.huntercat.model.User;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/11 20:18
 */
public class MethodRefTest {

    /**
     * 对象::实例方法名
     */
    @Test
    public void test1() {
        PrintStream ps = System.out;

        Consumer<String> consumer = ps::print;

        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("hahahahah");
    }

    @Test
    public void test2() {
        User user = new User();
        user.setName("zyy");
        user.setAge(10);
        Supplier<String> name = () -> user.getName();
        System.out.println(name.get());

        System.out.println("----------------------");

        Supplier<Integer> age = user::getAge;
        System.out.println(age.get());
    }

    /**
     * 类::静态方法名
     */
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> comparator = Integer::compare;
    }

    /**
     * 当两个参数时，是前面的参数调用后面的参数，可以直接使用 类名::方法名
     */
    @Test
    public void test4() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);

        BiPredicate<String, String> bp1 = String::equals;
    }

    /**
     * 构造器方式  User::new 跟接口传的参数有关
     */
    @Test
    public void test5() {

        Supplier<User> supplier = () -> new User();
        System.out.println(supplier.get());

        Supplier<User> supplier1 = User::new;
        System.out.println(supplier.get());
    }

    /**
     * 数组引用
     */
    @Test
    public void test6() {
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);
        System.out.println("----------------------");

        Function<Integer, String[]> function = String[]::new;
        System.out.println(function.apply(100).length);
    }
}
