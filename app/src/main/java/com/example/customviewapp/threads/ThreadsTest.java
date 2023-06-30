package com.example.customviewapp.threads;

import java.util.concurrent.TimeUnit;

public class ThreadsTest {
    public static void main(String[] args) throws InterruptedException {
        MoonRunner runnable = new MoonRunner();
        Thread thread = new Thread(runnable, "MoonThread");
        thread.start();
        TimeUnit.MILLISECONDS.sleep(10);
        runnable.cancel();
    }

    public static class TestRunnable implements Runnable {
        private long i;

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
            System.out.println("stop");
        }
    }

    public static class MoonRunner implements Runnable {
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on) {
                i++;
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
            System.out.println("stop");
        }

        public void cancel() {
            on = false;
        }
    }
}
