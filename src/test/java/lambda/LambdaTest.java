package lambda;

import cn.huntercat.filter.MyFilter;
import cn.huntercat.filter.MyFilterImpl;
import cn.huntercat.model.User;
import org.junit.Test;

import java.util.*;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/8 11:49
 */
public class LambdaTest {

    List<User> users = Arrays.asList(
            new User("zs", 18, 7777d, User.Status.BUSY),
            new User("ls", 34, 6666d, User.Status.FREE),
            new User("ww", 32, 3333d, User.Status.VACATION),
            new User("zl", 23, 4444d, User.Status.BUSY),
            new User("tq", 56, 5555d, User.Status.BUSY)
    );

    @Test
    public void test1() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        Comparator<Integer> comparator1 = (x, y) -> Integer.compare(x, y);


        TreeSet<Integer> set = new TreeSet<Integer>();
    }

    @Test
    public void test2() {

        /**
         *  使用 策略设计模式的过滤方法
         */
        List<User> users1 = filterUser(users, new MyFilterImpl());
        for (User user : users1) {
            System.out.println(user);
        }

        /**
         * 使用匿名内部类的方式
         */
        System.out.println("----------------------");
        List<User> users2 = filterUser(users, new MyFilter<User>() {
            public boolean test(User user) {
                return user.getAge() > 33;
            }
        });
        for (User user : users2) {
            System.out.println(user);
        }

        System.out.println("----------------------");

        /**
         * 使用lambda表达式的方式
         */
        List<User> users3 = filterUser(users, (u) -> u.getAge() < 25);
        for (User user : users3) {
            System.out.println(user);
        }
        users3.forEach(System.out::println);
    }

    public List<User> filterUser(List<User> list, MyFilter<User> myFilter) {
        List<User> users = new ArrayList<User>();
        for (User user : list) {
            if (myFilter.test(user)) {
                users.add(user);
            }
        }
        return users;
    }

    @Test
    public void test() {
        users.stream()
                .filter((user -> user.getAge() > 34))
                .forEach(System.out::println);
        System.out.println("----------------------");

        users.stream()
                .map(User::getName)
                .forEach(System.out::println);
    }


}
