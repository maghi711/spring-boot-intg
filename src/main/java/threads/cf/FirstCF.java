package threads.cf;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class FirstCF {
    public static void main(String[] args) throws Exception {
        System.out.println("LocalDateTime = " + LocalDateTime.now());
        long startTime = System.currentTimeMillis();
        final CompletableFuture<Void> greeting = CompletableFuture.runAsync(
                () -> System.out.println("Hello Aadavan, I am " + Thread.currentThread().getName() + " here."));
        //greeting.join();
        TimeUnit.SECONDS.sleep(10);
        System.out.println((System.currentTimeMillis() - startTime)/1_000);

        System.out.println("LocalDateTime = " + LocalDateTime.now());
    }
}
