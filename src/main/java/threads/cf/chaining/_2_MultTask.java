package threads.cf.chaining;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class _2_MultTask {

    // 1. Define the task
    // 2. compose them using CF

    public static void main(String[] args) {
        // 1. Fetch the list of id's from the DB
        // 2. Convert the id's to users
        // 3. print all of them

        // 1. Fetch the list of id's from the DB
        Supplier<List<Long>> idSupplier = () -> Arrays.asList(1l, 2l, 3l, 4l, 5l);

        // 2. Convert the id's to users
        Function<List<Long>, List<User>> mapIdToUser = ids -> ids.stream().map(User::new).collect(Collectors.toList());

        // 3. print all of them
        Consumer<List<User>> consumeUsers = userIds -> userIds.forEach(System.out::println);

        final CompletableFuture<List<Long>> listCompletableFuture = CompletableFuture.supplyAsync(idSupplier);
        listCompletableFuture.thenApply(mapIdToUser).thenAccept(consumeUsers);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
