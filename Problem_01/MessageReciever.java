
public class MessageReciever implements Runnable{

    private MessageQueue queue;

    public MessageReciever(MessageQueue queue){
        this.queue = queue;
    }

    public void run(){

        for(int i=0; i<5; i++){
            
            System.out.println(
                Thread.currentThread().getName() + " -> Recieved : " + queue.take()
            );  
        }
    }
}
