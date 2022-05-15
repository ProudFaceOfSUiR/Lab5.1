import Multithreadder.Multithreadder;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Main {
    /**
     * @param args
     * @author Alexandr Grebtsov
     * Main class
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(new Multithreadder()));
    }
}


