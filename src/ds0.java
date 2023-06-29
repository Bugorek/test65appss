import java.util.concurrent.CountDownLatch;

public class ds0 implements Repository {
    private final DataKeeper dataKeeper;
    private final int T;
    private final CountDownLatch countDownLatch;

    public ds0(DataKeeper dataKeeper, int T, CountDownLatch countDownLatch) {
        this.dataKeeper = dataKeeper;
        this.T = T;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void getData() {
        System.out.println("getData");
        new Thread(() -> {
            System.out.println("run");
            int i = 0;
            while (i < T + 1) {
                dataKeeper.saveData(new Data(
                        ds0.class.getName(),
                        getValue()
                ));

                try {
                    Thread.sleep(getSleepTime());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                i++;
            }

            countDownLatch.countDown();
        }).start();
    }

    private int getValue() {
        int min = 0;
        int max = 65000;

        return (int) ((Math.random() * (max - min)) + min);
    }

    private Long getSleepTime() {
        int min = 0;
        int max = 5;

        int sleepTime = (int) (Math.random() * (max - min) + min);
        System.out.println(sleepTime * 1000L);
        return sleepTime * 1000L;
    }
}
