import java.util.List;
import java.util.Map;

public interface DataKeeper {
    public void saveData(Data data);
    public Map<String, List<Integer>> getDataList();
}
