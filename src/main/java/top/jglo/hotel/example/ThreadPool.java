package top.jglo.hotel.example;

public class ThreadPool {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    // 使用中断机制，来终止线程
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted ...");
                        break;
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted When Sleep ...");
                        Thread.currentThread().interrupt();
                    }
                }

            }

        };
    }
}
