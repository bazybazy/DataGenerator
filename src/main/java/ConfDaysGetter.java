import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 05.01.2017.
 */
public class ConfDaysGetter extends FromFileGetter{
    public class ConfDay{
        Integer dayID;
        Integer dayCapacity;
        Date dayDate;

        public ConfDay(Integer dayID, Integer dayCapacity, Date dayDate) {
            this.dayID = dayID;
            this.dayCapacity = dayCapacity;
            this.dayDate=dayDate;
        }

        @Override
        public String toString() {
            return "ConfDay{" +
                    "dayIDList=" + dayID +
                    ", dayCapacity=" + dayCapacity +
                    " day = "+dayDate+'}';
        }
    }
    List<ConfDay> dayPriceList= new LinkedList<>();

    public ConfDaysGetter() throws IOException{
        List<String> confDaysList = Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConferenceDays.sql")).collect(Collectors.toList());
        for (String confDay : confDaysList) {
            String[] tmp = confDay.split(",");
            dayPriceList.add(new ConfDay(extractInt(tmp[3]),extractInt(tmp[6]),extractDate(tmp[4])));
        }
    }

    private Date extractDate(String s) {
        return new Date(s.substring(2,12));
    }

}
