package lambda;

import cn.huntercat.model.User;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/11 21:06
 */
public class StreamApi_01Test {

    List<User> users = Arrays.asList(
            new User("zs", 18, 7777d, User.Status.BUSY),
            new User("ls", 34, 6666d, User.Status.FREE),
            new User("ww", 32, 3333d, User.Status.VACATION),
            new User("zl", 23, 4444d, User.Status.BUSY),
            new User("tq", 56, 5555d, User.Status.BUSY),
            new User("tq", 56, 5555d, User.Status.BUSY)
    );


    /**
     * 创建Stream
     */
    @Test
    public void test1() {
        // 1. 可以通过Collection 系列集合提供的 stream()
        List<String> list = new ArrayList<>();
        Stream<String> stringStream = list.stream();

        // 2. 通过Arrays的方式创建流
        User[] users = new User[10];
        Stream<User> stream = Arrays.stream(users);

        // 3. 通过Stream 的静态方法 of()获取
        Stream<String> stringStream1 = Stream.of("aa", "bb");

        // 4. 创建无限流
        Stream<Integer> stream1 = Stream.iterate(0, (x) -> x + 2);
    }


    /**
     * 中间操作：
     *  filter：接收Lambda，从流中排除某些元素
     *  limit：截断流，使其元素不超过给定的数量
     *  skip：跳过元素，返回一个扔掉了前 n 个元素的流。若不足 n 则返回一个空流，与limit互补
     *  distinct：筛选，通过流所生成元素的hashCode() 和 equals() 去除重复的元素
     */

    /**
     * 内部迭代：由Stream Api完成的
     */
    @Test
    public void test2() {
        // 中间操作：不会执行任何操作
        Stream<User> stream = users.stream()
                .filter((u) -> {
                    System.out.println("我是中间操作");
                    return u.getAge() > 25;
                });
        // 终止操作：一次性执行全部内容，即“惰性求值”
        stream.forEach(System.out::println);


    }

    /**
     * 外部迭代
     */
    @Test
    public void test3() {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * limit：选择前 n 个 并返回
     */
    @Test
    public void test4() {
        users.stream()
                .filter((user) -> user.getSalary() > 3333D)
                .limit(2)
                .forEach(System.out::println);
    }

    /**
     * skip：跳过前n个并返回剩下的
     */
    @Test
    public void test5() {
        users.stream()
                .filter((user) -> user.getAge() > 18)
                .skip(3)
                .forEach(System.out::println);
    }

    /**
     * 需要操作的对象从写 hashCode 和 equals方法
     */
    @Test
    public void test6() {
        users.stream()
                .filter((user) -> user.getAge() > 18)
                .skip(0)
                .distinct()
                .forEach(System.out::println);
    }

    public static Stream<Character> filterList(String str) {
        List<Character> list = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }

    /**
     * map操作
     */
    @Test
    public void testMap() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        list.stream()
                .map((s) -> s.toUpperCase())// 返回的是一个个的字符串
                .forEach(System.out::println);

        System.out.println("----------------------");

        users.stream()
                .map(User::getName)
                .forEach(System.out::println);

        System.out.println("----------------------");

        Stream<Stream<Character>> streamStream = list.stream()
                .map(StreamApi_01Test::filterList);// 传进来的是一个个的流，返回的也是一个个的流，需要进行嵌套遍历
        streamStream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("----------------------");
        list.stream()
                .flatMap(StreamApi_01Test::filterList)
                .forEach(System.out::println);// 传进来是一个个的流，但是输出时会串起来，以一个流返回

    }


    /**
     * sorted()：自然排序（Comparable）
     * sorted(Comparator c): 定制排序(Comparator)
     */
    @Test
    public void testSorted() {
        List<String> list = Arrays.asList("aaa", "ccc", "bbb", "ddd", "eee");

        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("----------------------");

        users.stream()
                .sorted((u1, u2) -> {
                    if (u1.getName().equals(u2.getName())) {
                        return u1.getName().compareTo(u2.getName());
                    } else {
                        return u1.getAge().compareTo(u2.getAge());
                    }
                }).forEach(System.out::println);
    }

    /**
     * 查找与匹配：
     * allMatch: 检查是否匹配所有元素
     * anyMatch: 检查是否至少匹配一个元素
     * noneMatch: 检查是否没有匹配所有元素
     * findFirst: 返回第一个元素
     * findAny: 返回当前流中的任意元素
     * count: 返回流中元素的总个数
     * max: 返回流中最大值
     * min: 返回流中最小值
     */
    @Test
    public void testFind() {
        boolean b1 = users.stream()
                .allMatch(user -> user.getStatus().equals(User.Status.BUSY));
        System.out.println(b1);

        boolean b2 = users.stream()
                .anyMatch(user -> user.getStatus().equals(User.Status.BUSY));
        System.out.println(b2);

        boolean b3 = users.stream()
                .noneMatch(user -> user.getStatus().equals(User.Status.BUSY));
        System.out.println(b3);

        Optional<User> optionalUser = users.stream()
                // .sorted((u1, u2) -> Double.compare(u1.getSalary(), u2.getSalary()))
                .sorted(Comparator.comparing(User::getSalary))
                .findFirst();
        // 可以防止空指针
        System.out.println(optionalUser.get());

        Optional<User> optionalUser1 = users.parallelStream()
                .filter(user -> user.getAge() > 22)
                .findAny();
        System.out.println(optionalUser1.get());

        Long count = users.stream()
                .count();
        System.out.println(count);

        Optional<User> max = users.stream()
                .max(Comparator.comparing(User::getAge));
        System.out.println(max.get().getAge());

    }

    /**
     * 归约
     * reduce：可以将流中的元素反复的结合起来，最后得到一个值
     */
    @Test
    public void testReduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer num = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(num);

        System.out.println("----------------------");

        Optional<Double> aDouble = users.stream()
                .map(User::getSalary)
                .reduce(Double::sum);
        System.out.println(aDouble.get());
    }

    /**
     * 收集
     * collect：将流转化成其他形式，接收一个collector接口的实现，用于给stream中元素的汇总
     */
    @Test
    public void testCollect() {
        // 收集用户姓名到List集合中
        List<String> list = users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("----------------------");

        // 收集用户姓名到Set集合中
        Set<String> set = users.stream()
                .map(User::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);

        System.out.println("----------------------");

        // 收集用户姓名到HashSet集合中
        HashSet<String> hashSet = users.stream()
                .map(User::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }

    /**
     * collect 常见的其他相关操作
     */
    @Test
    public void testCollect2() {
        // 总数
        Long count = users.stream()
                .collect(Collectors.counting());
        System.out.println(count);
        System.out.println("----------------------");
        notifyAll();

        // 平均值
        Double avg = users.stream()
                .collect(Collectors.averagingDouble(User::getSalary));
        System.out.println(avg);
        System.out.println("----------------------");

        // 总和
        Double sum = users.stream()
                .collect(Collectors.summingDouble(User::getSalary));
        System.out.println(sum);
        System.out.println("----------------------");

        // 最大值
        Optional<User> user = users.stream()
                .collect(Collectors.maxBy(Comparator.comparing(User::getSalary)));
        System.out.println(user.get());

        // 最小值
        Optional<Double> aDouble = users.stream()
                .map(User::getSalary)
                .collect(Collectors.minBy(Double::compareTo));
        System.out.println(aDouble.get());
    }

    /**
     * collect：一些分组操作
     */
    @Test
    public void testCollect3() {
        Map<User.Status, List<User>> listMap = users.stream()
                .collect(Collectors.groupingBy(User::getStatus));
        System.out.println(listMap);

        System.out.println("----------------------");

        Map<User.Status, Map<String, List<User>>> mapMap = users.stream()
                .collect(Collectors.groupingBy(User::getStatus, Collectors.groupingBy((u) -> {
                    if (((User) u).getAge() < 30) {
                        return "青年";
                    } else if (((User) u).getAge() < 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(mapMap);
    }

    /**
     * 分区操作 false true
     */
    @Test
    public void testCollect4() {
        Map<Boolean, List<User>> collect = users.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 5000));
        System.out.println(collect);
    }

    /**
     * DoubleSummaryStatistics: 这里有一些求最大值 平均数灯相关的操作
     */
    @Test
    public void testCollect5() {
        DoubleSummaryStatistics collect = users.stream()
                .collect(Collectors.summarizingDouble(User::getSalary));

        System.out.println(collect.getMax());
        System.out.println(collect.getAverage());
        System.out.println(collect.getCount());
    }

    @Test
    public void testCollect6() {
        String collect = users.stream()
                .map(User::getName)
                .collect(Collectors.joining(",", "===", "---"));
        System.out.println(collect);
    }


}
