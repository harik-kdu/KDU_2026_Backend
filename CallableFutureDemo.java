import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class SumTask implements Callable<Integer> {

    private final int n;

    public SumTask(int n) {
        this.n = n;
    }

    @Override
    public Integer call() {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}

public class CallableFutureDemo {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = new SumTask(100);

        Future<Integer> future = executor.submit(task);

        try {
            System.out.println("Waiting for result...");
            Integer result = future.get(); // BLOCKS
            System.out.println("Sum result = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
