package threads.interrupt;

import java.util.concurrent.TimeUnit;

public class Sales {

    private static int[] salesByDay = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {
        Sales sales = new Sales();
        Thread calculationThread = new Thread(
                () -> sales.calculateTotal(1, 4), "Separate Thread"
        );
        calculationThread.start();
        Thread backupThread = new Thread(() -> {
            sales.backup();
        });
        backupThread.start();
        try {
            calculationThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        backupThread.interrupt();
        System.out.println("The application is done and going to shutdown.");
    }

    private void backup() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("Creating backups");

            if (Thread.currentThread().isInterrupted()) {
                return;
            }
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
