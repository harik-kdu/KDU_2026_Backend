public class MessageSender  implements Runnable{
    
    private MessageQueue queue;

    public MessageSender(MessageQueue queue){
        this.queue = queue;
    }

    public void run(){
        
        for(int i=0; i<5; i++){
            String msg = Thread.currentThread().getName() + "->" + System.currentTimeMillis();
            queue.put(msg);
        }

        // this.queue.put();
    }
}
