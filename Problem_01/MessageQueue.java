import java.util.ArrayDeque;
import java.util.Deque;

class MessageQueue {
    public Deque<String> messageQueue;
    
    public MessageQueue(){
        this.messageQueue = new ArrayDeque<>();
    }

    synchronized void put(String msg){
        messageQueue.add(msg);
        System.out.println("Addded: " + msg);
        notifyAll();
    }

    synchronized String take(){
        //Waiting till the Queue is Empty
        while(messageQueue.isEmpty()){
            try{
                wait();
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        return messageQueue.removeFirst();
    }

}