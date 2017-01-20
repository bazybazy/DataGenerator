import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 07.01.2017.
 */
public class ConfEnrollGetter {
    public class ConfEnr{
        Integer ConfID;
        Integer ParticipantID;
        String StudentID;

        public ConfEnr(Integer confID, Integer participantID,String StudentID) {
            ConfID = confID;
            ParticipantID = participantID;
            this.StudentID = StudentID;
        }
    }
    List<ConfEnr> ConfEnrList = new ArrayList<>();

    ConfEnrollGetter() throws IOException{
        List<String> conftxt =  Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfEnroll\\ConfReservationID.txt")).collect(Collectors.toList());
        List<String> participanttxt =  Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfEnroll\\ParticipantID.txt")).collect(Collectors.toList());
        List<String> studentxt =  Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfEnroll\\StudentID.txt")).collect(Collectors.toList());
        for(int i=0; i<conftxt.size();i++){
            ConfEnrList.add(new ConfEnr(Integer.parseInt(conftxt.get(i)),Integer.parseInt(participanttxt.get(i)),studentxt.get(i)));
        }

    }


}
