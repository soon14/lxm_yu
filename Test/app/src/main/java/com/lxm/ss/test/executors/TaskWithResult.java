package com.lxm.ss.test.executors;

/**
 * Created by lxm on 2018/2/2.
 */

import java.util.Random;
import java.util.concurrent.Callable;

public class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    /**
     * &#x4efb;&#x52a1;&#x7684;&#x5177;&#x4f53;&#x8fc7;&#x7a0b;&#xff0c;&#x4e00;&#x65e6;&#x4efb;&#x52a1;&#x4f20;&#x7ed9;ExecutorService&#x7684;submit&#x65b9;&#x6cd5;&#xff0c;&#x5219;&#x8be5;&#x65b9;&#x6cd5;&#x81ea;&#x52a8;&#x5728;&#x4e00;&#x4e2a;&#x7ebf;&#x7a0b;&#x4e0a;&#x6267;&#x884c;&#x3002;
     *
     * @return
     * @throws Exception
     */
    public String call() throws Exception {
        System.out.println("lxm TaskWithResult call()方法被自动调用,干活！！！    1         " + Thread.currentThread().getName());

        if (new Random().nextBoolean()) {
            System.out.println("lxm TaskWithResult call()方法被自动调用,干活！！！  2         Meet error in task.  ");
            throw new TaskException("Meet error in task." + Thread.currentThread().getName());
        }
        // 一个模拟耗时的操作
        for (int i = 10000; i > 0; i--) {
//            System.out.println("lxm TaskWithResult call()方法被自动调用,干活！！！    3         " +i);
        }
        return "call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();
    }
}
