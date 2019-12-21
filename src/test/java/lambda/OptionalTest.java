package lambda;

import cn.huntercat.model.User;
import org.junit.Test;

import java.util.Optional;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/17 19:41
 */
public class OptionalTest {

    @Test
    public void test1() {
        Optional<User> user = Optional.of(null);
        System.out.println(user.get());
    }

    @Test
    public void test2() {
        Optional<User> op = Optional.empty();
        System.out.println(op.get());
    }

    @Test
    public void test3() {
        Optional<User> op = Optional.ofNullable(null);

        User user = op.orElse(new User("战三", 18, 888.88D, User.Status.BUSY));
        System.out.println(user);
    }
}
