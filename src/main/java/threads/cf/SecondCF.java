package threads.cf;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class SecondCF {
    public static void main(String[] args) {
        Supplier<String> threadName = () -> Thread.currentThread().getName();
        final CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(threadName);
        final String resut = stringCompletableFuture.join();
        System.out.println("resut = " + resut);
    }
}
