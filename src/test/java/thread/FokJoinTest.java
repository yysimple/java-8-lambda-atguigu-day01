package thread;

import cn.huntercat.thread.ForkJoinCalculate;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/16 21:35
 */
public class FokJoinTest {

    @Test
    public void test1(){
        Instant start = Instant.now();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0L, 100000000000L);
        long sum = forkJoinPool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).getSeconds());// 10s
    }

    @Test
    public void test2(){
        Instant start = Instant.now();

        long sum = 0;

        for (int i = 0; i < 100000000000L; i++) {
            sum += i;
        }

        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).getSeconds());// 100+
    }

    @Test
    public void test3(){
        Instant start = Instant.now();

        LongStream.rangeClosed(0, 100000000000L)
                .parallel()
                .reduce(0, Long::sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).getSeconds());// 7s
    }

}
