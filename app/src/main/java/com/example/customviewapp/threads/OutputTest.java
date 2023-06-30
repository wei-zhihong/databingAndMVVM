package com.example.customviewapp.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OutputTest {
    public static void main(String[] args) {
//       TestThread testThread = new TestThread();
//       Thread thread = new Thread(testThread);
//       Thread thread1 = new Thread(testThread);
//       thread.start();
//       thread1.start();

        OutputData outputData = new OutputData();
        Lock lock = new ReentrantLock();

        new Thread(() -> {
            outputData.tryGetLock(Thread.currentThread(), lock);
        }).start();

        new Thread(() -> {
            outputData.tryGetLock(Thread.currentThread(), lock);
        }).start();
    }

    static class OutputData {

        public void output(Thread thread) {
            synchronized (this) {
                for (int i = 0; i < 5; i++) {
                    System.out.println(thread.getName() + ":" + i);
                }
            }
        }

        public void getLock(Thread thread, Lock lock) {
            lock.lock();
            try {
                System.out.println(thread.getName() + "获得了锁");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(thread.getName() + "释放了锁");
                lock.unlock();
            }
        }

        public void tryGetLock(Thread thread, Lock lock) {
            if (lock.tryLock()) {
                try {
                    System.out.println(thread.getName() + "获取了锁");
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(thread.getName() + "释放了锁");
                }
            } else {
                System.out.println(thread.getName() + "获取锁失败");
            }
        }
    }

    static class TestThread implements Runnable {
        OutputData outputData = new OutputData();

        @Override
        public void run() {
            outputData.output(Thread.currentThread());
        }
    }
}
