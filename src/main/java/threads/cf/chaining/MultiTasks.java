package threads.cf.chaining;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MultiTasks {

    // 1. Define the tasks
    // 2. Now chain the tasks with CF

    public static void main(String[] args) {
        // 1. Fetch all the id's from DB
        // 2. Use the id's and create/fetch the user
        // 3. display all of them

        // 1. Fetch all the id's from DB
        Supplier<List<Long>> supplyIds = () -> {
            sleepFor(2, SECONDS);
            return Arrays.asList(1l, 2l, 3l, 4l, 5l);
        };

        // 2. Use the id's and create the user
        Function<List<Long>, List<User>> users = id -> {
            sleepFor(2, SECONDS);
            return id.stream().map(User::new).collect(Collectors.toList());
        };

        // 3. display all of them
        Consumer<List<User>> displayer = users1 -> users1.forEach(System.out::println);

        // Use CF to create a pipeline.
        CompletableFuture<List<Long>> ids = CompletableFuture.supplyAsync(supplyIds);
        ids.thenApply(users)
                .thenAccept(displayer);
        sleepFor(2, SECONDS);
    }

    static void sleepFor(long timeout, TimeUnit t) {
        try {
            t.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
