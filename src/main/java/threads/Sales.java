package threads;

import java.util.concurrent.TimeUnit;

public class Sales {

    private static int[] salesByDay = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {
        Sales sales = new Sales();
        Thread t1 = new Thread(
                () -> sales.calculateTotal(1, 4), "Separate Thread"
        );
        t1.start();
        Thread backupThread = new Thread(() -> {
            sales.backup();
        });
        backupThread.setDaemon(true);
        backupThread.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The application is done and going to shutdown.");
    }

    private void backup() {
        while (true) {
            sleepFor(1, TimeUnit.SECONDS);
            System.out.println("Creating backups");
        }
    }

    private long calculateTotal(int startDay, int endDay) {
        long salesForPeriod = 0;
        for (int i = startDay; i < endDay; i++) {
            salesForPeriod += salesByDay[i];
            sleepFor(2, TimeUnit.SECONDS);
        }
        System.out.println("salesForPeriod = " + salesForPeriod);
        Thread t1 = Thread.currentThread();
        System.out.println("Id: " + t1.getId() + ", Name: " + t1.getName());
        return salesForPeriod;
    }

    private void sleepFor(long value, TimeUnit t) {
        try {
            t.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
