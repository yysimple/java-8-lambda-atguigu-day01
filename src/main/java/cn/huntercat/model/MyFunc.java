package cn.huntercat.model;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/9 21:30
 */
@FunctionalInterface
public interface MyFunc<T,R> {

    R getValue(T t1, T t2);
}
