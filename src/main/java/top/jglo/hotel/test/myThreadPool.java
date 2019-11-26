package top.jglo.hotel.test;

import java.util.concurrent.*;

public class myThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10, 10, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
//        ExecutorService executorService = Executors.newFixedThreadPool(10);


        //future可以接收返回值
        Future future1 = threadPoolExecutor.submit(new Poll_B("1"));
        Future future2 = threadPoolExecutor.submit(new Poll_B("2"));
        Future future3 = threadPoolExecutor.submit(new Poll_B("3"));
        Future future4 = threadPoolExecutor.submit(new Poll_B("4"));
        Future future5 = threadPoolExecutor.submit(new Poll_B("5"));
        Future future6 = threadPoolExecutor.submit(new Poll_B("6"));
        threadPoolExecutor.shutdown();

    }
}
