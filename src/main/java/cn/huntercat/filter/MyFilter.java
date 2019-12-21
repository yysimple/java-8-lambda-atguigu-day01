package cn.huntercat.filter;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/8 12:31
 */
public interface MyFilter<T> {

    boolean test(T t);
}
