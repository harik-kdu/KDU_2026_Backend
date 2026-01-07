import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class MessageQueue {

    private final Deque<String> messageQueue = new ArrayDeque<>();

    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    
    public MessageQueue(){
    }

    public void put(String msg) {
        lock.lock();
        try {
            messageQueue.addLast(msg);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }


    public String take() {
        lock.lock();
        try {
            while (messageQueue.isEmpty()) {
                notEmpty.await();
            }
            return messageQueue.removeFirst();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }

}