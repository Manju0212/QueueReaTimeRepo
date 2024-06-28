package QueueExamples.java;

import java.util.concurrent.LinkedTransferQueue;

public class ProducerConsumerApplication {
    private static LinkedTransferQueue<String> messageQueue = new LinkedTransferQueue<>();

    public static void main(String[] args) {
        // Producer thread
        new Thread(() -> {
            try {
                messageQueue.transfer("***Hello Customer***");
                System.out.println("****Message sent successfully****");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            try {
                String message = messageQueue.take();
                System.out.println("Received message: " + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}


