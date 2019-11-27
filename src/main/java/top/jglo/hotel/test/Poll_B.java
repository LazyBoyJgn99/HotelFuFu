package top.jglo.hotel.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Poll_B implements Callable {
    private String name;

    public Poll_B(String name) {
        this.name = name;
    }

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        //调用顺序1，2，3但是输出不一定是1，2，3
        System.out.println(name);
        return sum;
    }


}

