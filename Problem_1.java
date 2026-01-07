public class Problem_1 {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();

        Thread sender1 = new Thread(new MessageSender(queue));
        Thread sender2 = new Thread(new MessageSender(queue));
        Thread sender3 = new Thread(new MessageSender(queue));
        
        Thread reciever1 = new Thread(new MessageReciever(queue));
        Thread reciever2 = new Thread(new MessageReciever(queue));
        Thread reciever3 = new Thread(new MessageReciever(queue));

        reciever1.start();
        reciever2.start();
        reciever3.start();

        sender1.start();
        sender2.start();
        sender3.start();
    }
}
