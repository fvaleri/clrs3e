package it.fvaleri.clrs3e.algorithm.advanced.mt;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

/**
 * @author fvaleri
 */
public class FairLockTest {
    private FairLock cut;

    @Before
    public void setUp() {
        this.cut = new FairLock();
    }

    @Test
    public void test() throws InterruptedException {
        CountDownLatch signal = new CountDownLatch(1);
        SharedResource resource = new SharedResource();
        int nThreads = 10;
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            executor.execute(new Worker(new MyTask(cut, resource), signal));
        }
        executor.shutdown();
        signal.countDown();
        executor.awaitTermination(2, TimeUnit.SECONDS);
        assertEquals(nThreads, resource.getValue());
    }

    class SharedResource {
        int value = 0;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            try {
                // simulating some work
                Thread.sleep(10);
            } catch (InterruptedException e) {}
            this.value = value;
        }
    }

    public static interface Task {
        public void execute();
    }

    class MyTask implements Task {
        private FairLock lock;
        private SharedResource resource;

        public MyTask(FairLock lock, SharedResource resource) {
            this.lock = lock;
            this.resource = resource;
        }

        @Override
        public void execute() {
            try {
                lock.lock();
                resource.setValue(resource.getValue() + 1);
            } finally {
                lock.unlock();
            }
        }
    }

    class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private Task task = null;

        public Worker(Task task, CountDownLatch startSignal) {
            this.task = task;
            this.startSignal = startSignal;
        }

        @Override
        public void run() {
            try {
                startSignal.await();
                task.execute();
            } catch (InterruptedException e) {
            }
        }
    }
}
