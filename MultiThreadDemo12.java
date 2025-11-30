public class MultiThreadDemo12 {
    private static class NumberTask implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                System.out.println("数字线程 -> " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    private static class LetterTask implements Runnable {
        @Override
        public void run() {
            for (char c = 'A'; c <= 'J'; c++) {
                System.out.println("字母线程 -> " + c);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    public static void main(String[] args) {
        Thread numberThread = new Thread(new NumberTask(), "NumberThread");
        Thread letterThread = new Thread(new LetterTask(), "LetterThread");
        numberThread.start();
        letterThread.start();
        try {
            numberThread.join();
            letterThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("所有线程执行完毕。");
    }
}