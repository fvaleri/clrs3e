package it.fvaleri.clrs3e.algorithm.advanced.mt;

import java.util.ArrayList;
import java.util.List;

/**
 * Prevents thread starvation using a queue.
 * Note: always use a try-finally block to avoid forever locks.
 * 
 * @author fvaleri
 */
public class FairLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();
    private boolean debug;

    public FairLock() {
        this.debug = false;
    }

    public FairLock(boolean debug) {
        this.debug = debug;
    }

    public void lock() {
        QueueObject queueObject = new QueueObject();
        boolean isLockedForThisThread = true;
        synchronized (this) {
            waitingThreads.add(queueObject);
        }

        while (isLockedForThisThread) {
            synchronized (this) {
                // test-and-set executed atomically to avoid slipped conditions
                isLockedForThisThread = isLocked || waitingThreads.get(0) != queueObject;
                if (!isLockedForThisThread) {
                    isLocked = true;
                    waitingThreads.remove(queueObject);
                    lockingThread = Thread.currentThread();
                    if (debug) {
                        System.out.println(Thread.currentThread().getName() + ": lock acquired");
                    }
                    return;
                }
            }
            try {
                // wait called outside sync block to avoid nested monitor lockout
                queueObject.doWait();
            } catch (InterruptedException e) {
                synchronized (this) {
                    waitingThreads.remove(queueObject);
                }
            }
        }
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLocked = false;
        lockingThread = null;
        if (waitingThreads.size() > 0) {
            waitingThreads.get(0).doNotify();
        }
        if (debug) {
            System.out.println(Thread.currentThread().getName() + ": lock released");
        }
    }

    class QueueObject {
        // semaphore that avoids missed signals
        private boolean isNotified = false;

        public synchronized void doWait() throws InterruptedException {
            // this loop avoids spurious wakeups
            while (!isNotified) {
                this.wait();
            }
            this.isNotified = false;
        }

        public synchronized void doNotify() {
            this.isNotified = true;
            this.notify();
        }

        public boolean equals(Object o) {
            return this == o;
        }
    }
}
