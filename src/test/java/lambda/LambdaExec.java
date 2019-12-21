package lambda;

import cn.huntercat.MyFunction;
import cn.huntercat.model.MyFunc;
import cn.huntercat.model.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/9 21:06
 */
public class LambdaExec {

    List<User> users = Arrays.asList(
            new User("zs", 18, 7777d, User.Status.BUSY),
            new User("ls", 34, 6666d, User.Status.FREE),
            new User("ww", 32, 3333d, User.Status.VACATION),
            new User("zl", 23, 4444d, User.Status.BUSY),
            new User("tq", 56, 5555d, User.Status.BUSY)
    );



    @Test
    public void test1() {
        Collections.sort(users, (u1, u2) -> {
            if (u1.getAge() == u2.getAge()) {
                return u1.getName().compareTo(u2.getName());
            } else {
                return Integer.compare(u1.getAge(), u2.getAge());
            }
        });

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test2() {
        String trimStr = strHandler("\t\t\t 我是哈哈哈哈", (str -> str.trim()));
        System.out.println(trimStr);

        String upper = strHandler("abcdef", str -> str.toUpperCase());
        System.out.println("upper = " + upper);
    }

    public String strHandler(String str, MyFunction mf) {
        return mf.getValue(str);
    }

    @Test
    public void test3() {
        op(100L, 200L, (x, y) -> x + y);
        op(100L, 200L, (x, y) -> x * y);
    }

    public void op(Long x, Long y, MyFunc<Long,Long> myFunc){
        System.out.println(myFunc.getValue(x, y));
    }
}
