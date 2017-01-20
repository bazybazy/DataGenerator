import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 05.01.2017.
 */
public class ParticipantsTable {
    public class Participant{
        Integer participantID;
        String firstName;
        String lastName;

        public Participant(Integer participantID, String firstName, String lastName) {
            this.participantID = participantID;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "insert into Participant (ParticipantID, FirstName, LastName) values " +
                    "(" + participantID +
                    ", '" + firstName + '\'' +
                    ", '" + lastName + '\'' +
                    ");\n";
        }
    }
    List<Participant>  table= new LinkedList<>();
    public ParticipantsTable() throws IOException {
        List<String> firstNamesList = Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\participants\\firstName.csv")).collect(Collectors.toList());
        List<String> lastNamesList = Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\participants\\lastName.csv")).collect(Collectors.toList());
        int counter=0;
        for(int i=1; i< 15789;i++){
            counter++;
            table.add( new Participant(i,generateData(firstNamesList,counter),generateData(lastNamesList,counter)));
        }
    }

    private String generateData(List<String> list, int counter) {
        return list.get(counter);
    }
}
