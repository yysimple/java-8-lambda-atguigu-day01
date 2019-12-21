package cn.huntercat.filter;

import cn.huntercat.model.User;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/8 12:32
 */
public class MyFilterImpl implements MyFilter<User> {

    @Override
    public boolean test(User user) {
        return user.getAge() > 22;
    }
}
