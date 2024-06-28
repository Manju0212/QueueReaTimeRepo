package QueueExamples.java;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ChatApplication {


//Example of ConcurrentLinkedQueue

    private static ConcurrentLinkedQueue<String> messageQueue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        // Producer thread
        new Thread(() -> {
            messageQueue.add("User1: Hello");
            messageQueue.add("User2: Hi");
            messageQueue.add("User3: How are you?");
        }).start();

        // Consumer thread
        new Thread(() -> {
            while (true) {
                String message = messageQueue.poll();
                if (message != null) {
                    System.out.println(message);
                }
            }
        }).start();
    }
}



