import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreamDemo {

    public static void main(String[] args) {

        List<Integer> numbers =
                IntStream.rangeClosed(1, 1_000_000)
                         .boxed()
                         .collect(Collectors.toList());

        // Sequential Stream
        long startSeq = System.currentTimeMillis();
        long seqSum = numbers.stream()
                             .mapToLong(Integer::longValue)
                             .sum();
        long endSeq = System.currentTimeMillis();

        // Parallel Stream
        long startPar = System.currentTimeMillis();
        long parSum = numbers.parallelStream()
                             .mapToLong(Integer::longValue)
                             .sum();
        long endPar = System.currentTimeMillis();

        System.out.println("Sequential Sum: " + seqSum);
        System.out.println("Sequential Time: " + (endSeq - startSeq) + " ms");

        System.out.println("Parallel Sum: " + parSum);
        System.out.println("Parallel Time: " + (endPar - startPar) + " ms");
    }
}
