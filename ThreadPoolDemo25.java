import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
public class ThreadPoolDemo25 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Runnable runnableTask = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " 正在执行 Runnable 任务");
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(threadName + " 完成 Runnable 任务");
        };
        Callable<String> callableTask = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " 正在执行 Callable 任务");
            Thread.sleep(1200);
            return threadName + " 的 Callable 结果";
        };
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            executor.submit(runnableTask);
        }
        for (int i = 0; i < 5; i++) {
            Future<String> future = executor.submit(callableTask);
            futures.add(future);
        }
        for (Future<String> f : futures) {
            try {
                String result = f.get();
                System.out.println("获取到结果 → " + result);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("线程被中断");
            } catch (ExecutionException e) {
                System.err.println("任务执行异常：" + e.getCause());
            }
        }
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.err.println("任务未在规定时间内结束，强制关闭线程池");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            executor.shutdownNow();
        }
        System.out.println("线程池已关闭，程序结束");
    }
}