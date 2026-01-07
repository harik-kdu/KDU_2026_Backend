import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Problem2 {
    public static void main(String[] args) {

        MessageQueue queue = new MessageQueue();

        ExecutorService senderService = Executors.newFixedThreadPool(3);
        
        ExecutorService recieverService = Executors.newFixedThreadPool(3);
        
        //3 recievers
        recieverService.submit(new MessageReciever(queue));
        recieverService.submit(new MessageReciever(queue));
        recieverService.submit(new MessageReciever(queue));
        
        senderService.submit(new MessageSender(queue));
        senderService.submit(new MessageSender(queue));
        senderService.submit(new MessageSender(queue));

        senderService.shutdown();
        recieverService.shutdown();

        try{
            senderService.awaitTermination(10, TimeUnit.SECONDS);
            recieverService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
