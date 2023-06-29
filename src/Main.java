import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();

        CountDownLatch countDownLatch = new CountDownLatch(3);

        DataKeeper dataKeeper = new DataKeeperImpl();

        ds0 ds0 = new ds0(dataKeeper, T, countDownLatch);
        ds1 ds1 = new ds1(dataKeeper, T, countDownLatch);
        ds2 ds2 = new ds2(dataKeeper, T, countDownLatch);

        ds0.getData();
        ds1.getData();
        ds2.getData();

        countDownLatch.await();

        Map<String, List<Integer>> savedData = dataKeeper.getDataList();
        List<Integer> ds0Data = savedData.get(ds0.getClass().getName());
        List<Integer> ds1Data = savedData.get(ds1.getClass().getName());
        List<Integer> ds2Data = savedData.get(ds2.getClass().getName());

        System.out.println(savedData);

        int i = 0;
        while (i < T) {
            String out = ds0Data.get(i) + "," + ds1Data.get(i) + "," + ds2Data.get(i);
            System.out.println(out);
            i++;
        }
    }
}