package threads.locks;

import java.util.concurrent.TimeUnit;

public class Sales {

    private static int[] salesByDay = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private long totalSales = 0; // a shared variable

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int startDay = 1;
        int endDay = 7;
        int daysPerThread = (int) Math.ceil((endDay - startDay) / 2.0);

        Sales sales = new Sales();
        Thread firstCalcThread = new CalculationThread(sales, startDay, daysPerThread + startDay, "Calculation Thread - 1");
        Thread secondCalcThread = new CalculationThread(sales, daysPerThread + startDay, endDay, "Calculation Thread - 2");
        firstCalcThread.start();
        secondCalcThread.start();

        Thread backupThread = new Thread(() -> {
            sales.backup();
        });
        backupThread.start();
        try {
            firstCalcThread.join();
            secondCalcThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        backupThread.interrupt();
        try {
            backupThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("The application is done and going to shutdown." + sales.totalSales + "  Time taken to execute is " + totalTime + " ms");
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

    private void addPartialSales(long partialSales) {
        totalSales += partialSales;
    }

    synchronized private long calculateTotal(int startDay, int endDay) {
        // this monitor.lock
        System.out.println("A monitor of the object " + this + " is locked by the thread " + Thread.currentThread().getName());
        long salesForPeriod = 0;
        for (int i = startDay; i < endDay; i++) {
            salesForPeriod += salesByDay[i];
            sleepFor(100, TimeUnit.MILLISECONDS);
        }
        addPartialSales(salesForPeriod);

        for (int i = 0; i < 1_00_000; i++) {
            addPartialSales(1_00_000);
            addPartialSales(-1_00_000);
        }

        Thread t1 = Thread.currentThread();
        System.out.println("Total sales For the Period = " + salesForPeriod + " for start day " + startDay + " and end Day " + endDay + " Id: " + t1.getId() + ", Name: " + t1.getName());
        System.out.println("A monitor of the object " + this + " is unlocked by the thread " + Thread.currentThread().getName());
        // this monitor.unlock
        return salesForPeriod;
    }

    private void sleepFor(long value, TimeUnit t) {
        try {
            t.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class CalculationThread extends Thread {
        private final Sales sales;
        private final int startDay;
        private final int endDay;

        CalculationThread(Sales sales, int startDay, int endDay, String threadName) {
            super(threadName);
            this.sales = sales;
            this.startDay = startDay;
            this.endDay = endDay;
        }

        @Override
        public void run() {
            sales.calculateTotal(startDay, endDay);
        }
    }
}
