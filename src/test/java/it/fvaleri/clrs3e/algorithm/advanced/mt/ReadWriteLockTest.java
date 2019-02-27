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
public class ReadWriteLockTest {
    private ReadWriteLock cut;

    @Before
    public void setUp() {
        this.cut = new ReadWriteLock();
    }

    @Test
    public void test() throws InterruptedException {
        CountDownLatch signal = new CountDownLatch(1);
        SharedResource resource = new SharedResource();
        int nThreads = 10;
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            if (i % 2 == 0) {
                executor.execute(new Worker(new ReadTask(cut, resource), signal));
            } else {
                executor.execute(new Worker(new WriteTask(cut, resource), signal));
            }
        }
        executor.shutdown();
        signal.countDown();
        executor.awaitTermination(2, TimeUnit.SECONDS);
        assertEquals(nThreads/2, resource.getValue());
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

    class ReadTask implements Task {
        private ReadWriteLock lock;
        private SharedResource resource;

        public ReadTask(ReadWriteLock lock, SharedResource resource) {
            this.lock = lock;
            this.resource = resource;
        }

        @Override
        public void execute() {
            try {
                lock.lockRead();
                resource.getValue();
            } finally {
                lock.unlockRead();
            }
        }
    }

    class WriteTask implements Task {
        private ReadWriteLock lock;
        private SharedResource resource;

        public WriteTask(ReadWriteLock lock, SharedResource resource) {
            this.lock = lock;
            this.resource = resource;
        }

        @Override
        public void execute() {
            try {
                lock.lockWrite();
                resource.setValue(resource.getValue() + 1);
            } finally {
                lock.unlockWrite();
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
