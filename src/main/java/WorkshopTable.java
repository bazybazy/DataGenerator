import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by admin on 05.01.2017.
 */
public class WorkshopTable {
    List<Workshop> table=new LinkedList<>();;

    WorkshopTable() throws IOException,ParseException{
        ConferenceDaysTable conferenceDaysTable =new ConferenceDaysTable();
        int counter =1;
        Random generator =new Random();
        List<String> workshopNames = Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\WorkshopNames.csv")).collect(Collectors.toList());
        int nameCounter =0;
        for(Day day : conferenceDaysTable.table){
            int x =3+generator.nextInt(3);
            for(int i=0; i< x; i++,counter++) {
                nameCounter ++;
                String start = countStart();
                table.add(new Workshop(counter, day.dayID,workshopNames.get(nameCounter), start, CountEnd(start),randomPrice(),randomCancel(),randomParticipants() ));
            }
        }

    }

    private Double randomPrice() {
        if(new Random().nextInt(100) >75) return 0.0;
        else return 20.0 + new Integer(new Random().nextInt(20)).doubleValue() +new Random().nextDouble();
    }

    private Integer randomParticipants() {
        return 10 + new Random().nextInt(8)*2;
    }

    private Integer randomCancel() {
        if(new Random().nextInt(100)>96) return 1;
        else return 0;
    }

    private String CountEnd(String startTime) {
        Integer hours = Integer.parseInt(startTime.substring(0,2));
        Integer minutes = Integer.parseInt(startTime.substring(3,5));
        int x = new Random().nextInt(3);
        switch(x){
            case 0 : hours++; break;
            case 1 : int tmp = (minutes +30); hours =hours +1+ tmp/60; minutes =tmp%60; break;
            case 2 : hours+=2; break;
            default: throw new IllegalArgumentException ("it shouldn't pass sucha  value! check the random generator");
        }
        return timeToString(hours,minutes);
    }

    private String timeToString(Integer hours, Integer minutes) {
        if (hours <10 && minutes <10) return "0"+hours+":0"+minutes;
        else
            if(hours<10) return "0"+hours+":"+minutes;
        else if(minutes<10) return hours+":0"+minutes;
        else return hours+":"+minutes;
    }

    private String countStart() {
        return timeToString(new Random().nextInt(9) +9,new Random().nextInt(12)*5);
    }
}
