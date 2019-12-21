package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/9 21:37
 * <p>
 * java 8 内置的四大核心接口
 * <p>
 * Consumer<T>: 消费型接口
 * void accept(T t);
 * <p>
 * Supplier<T>: 供给型接口
 * T get();
 * <p>
 * Function<T, R>: 函数型接口
 * R apply(T t)
 * <p>
 * Predicate<T>: 断言型接口
 * boolean test(T t)
 */
public class LambdaTest2 {
    /**
     * 消费型接口
     */
    @Test
    public void testConsumer() {
        happy(10000D, m -> System.out.println("这次用了：" + m + "元"));
    }

    public void happy(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    /**
     * 供给型接口
     */
    @Test
    public void testSupplier() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer integer : numList) {
            System.out.println("integer = " + integer);
        }
    }

    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * 函数式接口
     */
    @Test
    public void testFunction() {
        String upper = strHandler("acasfsafa", (str) -> str.toUpperCase());
        System.out.println("upper = " + upper);

    }

    public String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    /**
     * 断言型接口
     */
    @Test
    public void testPredicate() {
        List<String> stringList = Arrays.asList("hello","www","hh","xxxx");

        List<String> filter = filter(stringList, (s) -> s.length() > 3);
        for (String s : filter) {
            System.out.println("s = " + s);
        }
    }

    public List<String> filter(List<String> list, Predicate<String> predicate){
        List<String> stringList = new ArrayList<>();

        for (String s : list) {
            if (predicate.test(s)) {
                stringList.add(s);
            }
        }
        return stringList;
    }


}
