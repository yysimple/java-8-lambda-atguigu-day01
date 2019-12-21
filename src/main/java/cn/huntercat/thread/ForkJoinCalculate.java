package cn.huntercat.thread;

import java.util.concurrent.RecursiveTask;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/16 21:11
 */
public class ForkJoinCalculate extends RecursiveTask<Long>{

    private Long start;
    private Long end;

    public static final Long THRESHOLD = 10000L;
    public ForkJoinCalculate(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if(length < THRESHOLD){
            long sum = 0;

            for (long i = start; i < end; i++) {
                sum += i;
            }

            return sum;
        }else {
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork(); // 拆分子任务，同时压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();

            return left.join() + right.join();

        }
    }
}
