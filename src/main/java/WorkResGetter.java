import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 07.01.2017.
 */
public class WorkResGetter {
    public class WorkRes{
        Integer WorkreservationID;
        Integer WorkshopID;
        Integer PlacesReseved;
        Integer ConfResID;

        public WorkRes( Integer WorkreservationID,Integer workshopID, Integer placesReserved,Integer confResID) {
            this.WorkreservationID=WorkreservationID;
            ConfResID = confResID;
            WorkshopID = workshopID;
            PlacesReseved = placesReserved;
        }
    }
    ArrayList<WorkRes> workResList = new ArrayList<>();

    WorkResGetter()throws IOException {
        List<String> csv =  Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\WorkshopReservation\\WorkshopReservation.csv")).collect(Collectors.toList());
        for (String workRes:csv) {
            String[] tmp = workRes.split(";");
            workResList.add(new WorkRes(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]),Integer.parseInt(tmp[2]),Integer.parseInt(tmp[4])));
        }

    }

}
