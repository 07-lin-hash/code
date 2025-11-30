import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
public class ProducerConsumerSimple17 {
    public static void main(String[] args) throws InterruptedException {
        MessageQueue buffer = new MessageQueue(5);
        Thread producer = new Thread(() -> {
            try {
                int count = 0;
                while (!Thread.currentThread().isInterrupted()) {
                    buffer.produce("消息-" + (++count));
                    Thread.sleep(new Random().nextInt(1000));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");
        Thread consumer = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    buffer.consume();
                    Thread.sleep(new Random().nextInt(1500));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");
        producer.start();
        consumer.start();
        Thread.sleep(10000);
        producer.interrupt();
        consumer.interrupt();
    }
}
class MessageQueue {
    private final Queue<String> queue = new LinkedList<>();
    private final int maxSize;
    public MessageQueue(int maxSize) {
        this.maxSize = maxSize;
    }
    public synchronized void produce(String message) throws InterruptedException {
        while (queue.size() == maxSize) wait();
        queue.offer(message);
        System.out.println(Thread.currentThread().getName() + " 生产: " + message);
        notify();
    }
    public synchronized String consume() throws InterruptedException {
        while (queue.isEmpty()) wait();
        String message = queue.poll();
        System.out.println(Thread.currentThread().getName() + " 消费: " + message);
        notify();
        return message;
    }
}