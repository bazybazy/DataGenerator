import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by admin on 06.01.2017.
 */
public class ConfEnrollTable {
    public class ConfEnroll{
        Integer confEnrollID;
        String studentID;
        Integer participantID;
        Integer confReservationID;

        public ConfEnroll(Integer confEnrollID, String studentID, Integer participantID, Integer confReservationID) {
            this.confEnrollID = confEnrollID;
            this.studentID = studentID;
            this.participantID = participantID;
            this.confReservationID = confReservationID;
        }

        @Override
        public String toString() {
            return "insert into ConferenceEnrollment (ConferenceEnrollmentID, StudentID, ParticipantID, ConferenceReservationID) values (" +
                    + confEnrollID +
                    ", " + studentID +
                    ", " + participantID +
                    ", " + confReservationID +
                    ");";
        }
    }
    List<ConfEnroll> table= new LinkedList<>();
    Integer studID =222222;

    ConfEnrollTable() throws IOException{
        List<Integer> studentPlaces = new LinkedList<>();
        Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfReservation\\studPlaces.txt")).mapToInt(Integer::parseInt).forEach(studentPlaces::add);
        List<Integer> adultPlaces = new LinkedList<>();
        Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfReservation\\adultPlaces.txt")).mapToInt(Integer::parseInt).forEach(adultPlaces::add);
        List<Integer> confResIDList = new LinkedList<>();
        List<Integer> participantIDList = new LinkedList<>();
        List<String> studIDList = new LinkedList<>();
        int counter =0;
        for(int i=0; i< studentPlaces.size();i++){
            Random generator = new Random();
            updateStudID();
            for(int j=0; j<studentPlaces.get(i);j++){
                counter ++;
                studID += generator.nextInt(30);
                int participant = generator.nextInt(15788)+1;
                table.add(new ConfEnroll(counter,studID.toString(),participant, i+1));
                confResIDList.add(i+1);
                participantIDList.add(participant);
                studIDList.add(studID.toString());
            }
            for(int j=0; j<adultPlaces.get(i);j++){
                counter ++;
                int participant =generator.nextInt(15788)+1;
                table.add(new ConfEnroll(counter,"NULL",participant, i+1));
                confResIDList.add(i+1);
                participantIDList.add(participant);
                studIDList.add("NULL");
            }
        }

        ToFilePrinter.printIntsToFile(confResIDList,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfEnroll\\ConfReservationID.txt");
        ToFilePrinter.printIntsToFile(participantIDList,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfEnroll\\ParticipantID.txt");
        ToFilePrinter.printStringsToFile(studIDList,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfEnroll\\StudentID.txt");


    }

    private void updateStudID() {
        studID += new Random().nextInt(30);
    }


}
