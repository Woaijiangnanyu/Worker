package com.example.guojialin.worker.ja;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPrinter {
    private Lock threadLock = new ReentrantLock();
    int count = 10;
    Thread A = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                threadLock.lock();
                try {
                    if (count < 1) {
                        return;
                    }
                    if (count % 3 == 0) {
                        System.out.println("a : count - " + count);
                        count--;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    threadLock.unlock();
                }
            }
        }
    });

    Thread B = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                threadLock.lock();
                try {
                    if (count < 1) {
                        return;
                    }
                    if (count % 3 == 1) {
                        System.out.println("b : count - " + count);
                        count--;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    threadLock.unlock();
                }
            }
        }
    });

    Thread C = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                threadLock.lock();
                try {
                    if (count < 1) {
                        return;
                    }
                    if (count % 3 == 2) {
                        System.out.println("c : count - " + count);
                        count--;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    threadLock.unlock();
                }
            }
        }
    });

    public void startPrint() {
        A.start();
        B.start();
        C.start();
    }

}
