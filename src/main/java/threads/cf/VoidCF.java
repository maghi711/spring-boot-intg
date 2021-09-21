package threads.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class VoidCF {
    public static void main(String[] args) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.complete(null);
        };
        CompletableFuture.runAsync(task);
        future.join();
        System.out.println("Hello! coders");
    }
}
