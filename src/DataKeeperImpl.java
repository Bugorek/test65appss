import java.util.*;

public class DataKeeperImpl implements DataKeeper {
    final Map<String, List<Integer>> dataList = new HashMap<>();
    @Override
    public void saveData(Data data) {
        synchronized (dataList) {
            List<Integer> item = dataList.get(data.repositoryName);
            if (item != null) {
                item.add(data.value);
            } else {
                List<Integer> newItem = new ArrayList<>(data.value);
                dataList.put(data.repositoryName, newItem);
            }
        }
    }

    public Map<String, List<Integer>> getDataList() {
        return dataList;
    }
}
