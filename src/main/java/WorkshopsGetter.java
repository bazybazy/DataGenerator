import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 06.01.2017.
 */
public class WorkshopsGetter extends FromFileGetter{
    public class WorkshopData {
        Integer workShopID;
        Integer dayID;
        Integer workshopCapacity;

        public WorkshopData(Integer workShopID, Integer dayID, Integer workshopCapacity) {
            this.workShopID = workShopID;
            this.dayID = dayID;
            this.workshopCapacity=workshopCapacity;
        }
    }

    List<WorkshopData> workshopDatas = new ArrayList<>();

    public WorkshopsGetter() throws IOException {
        List<String> workshops = Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\Workshops.sql")).collect(Collectors.toList());
        for (String workshop : workshops) {
            String[] tmp = workshop.split(",");
            workshopDatas.add(new WorkshopData(extractInt(tmp[7]),extractInt(tmp[8]),extractInt(tmp[14])));
        }
    }
}
