package lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/9 20:22
 * <p>
 * <p>
 * 语法格式：() -> TODO
 *
 * 函数式编程，需要函数驱动、即接口
 *
 * 函数式接口：里面只有一个抽象方法
 *
 * 左侧：表达式的参数
 * 右侧：表达式所执行的功能，即Lambda体
 */
public class LambdaGrammerTest {

    /**
     * 无参无返回
     */
    @Test
    public void test1() {

        int num = 1;// 在jdk1.7之前 同级别的内部类要使用 需要加上final （1.8默认加上了）

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("我是匿名内部类" + num);
            }
        };

        runnable.run();
        System.out.println("----------------------");

        Runnable runnable1 = () -> System.out.println("我是Lambda表达式");
        runnable1.run();
    }

    /**
     * 有一个参数(小括号可以省去)、没有返回值
     */
    @Test
    public void test2() {
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("我是x");
    }

    /**
     * 有两个参数、有多条语句、有返回值
     */
    @Test
    public void test3() {
        Comparator<Integer> comparable = (x, y) -> {
            System.out.println("我是第一条语句");
            return Integer.compare(x, y);
        };
    }

    /**
     * 只有一条语句时、return和{} 都可以省略
     *
     *  lambda的参数类型可以不写，因为jvm编译器可以通过上下文推断出，即“类型推断”
     */
    @Test
    public void test4() {
        Comparator<Integer> comparator = (Integer x, Integer y) -> Integer.compare(x, y);
    }


}
