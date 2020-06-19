package com.example.simplepaging;

import java.util.concurrent.ExecutorService;

public class Executors {

    //创建线程池
    private static ExecutorService IO_EXECUTOR =
            java.util.concurrent.Executors.newSingleThreadExecutor();

    public static void ioThread(Runnable runnable) {
        IO_EXECUTOR.execute(runnable);
    }
}
