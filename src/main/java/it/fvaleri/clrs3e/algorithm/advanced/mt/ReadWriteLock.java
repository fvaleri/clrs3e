package it.fvaleri.clrs3e.algorithm.advanced.mt;

import java.util.HashMap;
import java.util.Map;

/**
 * Allows multiple readers but only one writer.
 *
 * Java concurrent API:
 * - There is 'StampedLock.tryOptimisticRead' for optimistic locking, 
 * which is faster than pessimisting locking and no deadlock is possible.
 * - Atomic classes do heavy use CAS (compare-and-swap) instruction provided 
 * by modern CPUs, which are much faster than syncronizing via locks. 
 * 
 * @author fvaleri
 */
public class ReadWriteLock {
    private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();
    private int writeAccesses = 0;
    private int writeRequests = 0;
    private Thread writingThread = null;
    private boolean debug;

    public ReadWriteLock() {
        this.debug = false;
    }

    public ReadWriteLock(boolean debug) {
        this.debug = debug;
    }

    public synchronized void lockRead() {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccess(callingThread)) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        readingThreads.put(callingThread, (getReadAccessCount(callingThread) + 1));
        if (debug) {
            System.out.println(Thread.currentThread().getName() + ": read lock acquired");
        }
    }

    private boolean canGrantReadAccess(Thread callingThread) {
        if (isWriter(callingThread)) {
            return true;
        } if (hasWriter()) {
            return false;
        } if (isReader(callingThread)) {
            return true;
        } if (hasWriteRequests()) {
            return false;
        }
        return true;
    }

    public synchronized void unlockRead() {
        Thread callingThread = Thread.currentThread();
        if (!isReader(callingThread)) {
            throw new IllegalMonitorStateException("Calling thread does not hold a read lock");
        }
        int accessCount = getReadAccessCount(callingThread);
        if (accessCount == 1) {
            readingThreads.remove(callingThread);
        } else {
            readingThreads.put(callingThread, (accessCount - 1));
        }
        notifyAll();
        if (debug) {
            System.out.println(Thread.currentThread().getName() + ": read lock released");
        }
    }

    public synchronized void lockWrite() {
        writeRequests++;
        Thread callingThread = Thread.currentThread();
        while (!canGrantWriteAccess(callingThread)) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        writeRequests--;
        writeAccesses++;
        writingThread = callingThread;
        if (debug) {
            System.out.println(Thread.currentThread().getName() + ": write lock acquired");
        }
    }

    private boolean canGrantWriteAccess(Thread callingThread) {
        if (isOnlyReader(callingThread)) {
            return true;
        } if (hasReaders()) {
            return false;
        } if (writingThread == null) {
            return true;
        } if (!isWriter(callingThread)) {
            return false;
        }
        return true;
    }

    public synchronized void unlockWrite() {
        if (!isWriter(Thread.currentThread())) {
            throw new IllegalMonitorStateException("Calling thread does not hold the write lock");
        }
        writeAccesses--;
        if (writeAccesses == 0) {
            writingThread = null;
        }
        notifyAll();
        if (debug) {
            System.out.println(Thread.currentThread().getName() + ": write lock released");
        }
    }

    private int getReadAccessCount(Thread callingThread) {
        Integer accessCount = readingThreads.get(callingThread);
        if (accessCount == null) {
            return 0;
        }
        return accessCount.intValue();
    }

    private boolean hasReaders() {
        return readingThreads.size() > 0;
    }

    private boolean isReader(Thread callingThread) {
        return readingThreads.get(callingThread) != null;
    }

    private boolean isOnlyReader(Thread callingThread) {
        return readingThreads.size() == 1 && readingThreads.get(callingThread) != null;
    }

    private boolean hasWriter() {
        return writingThread != null;
    }

    private boolean isWriter(Thread callingThread) {
        return writingThread == callingThread;
    }

    private boolean hasWriteRequests() {
        return this.writeRequests > 0;
    }
}
