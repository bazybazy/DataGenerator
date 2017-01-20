import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by admin on 06.01.2017.
 */
public class WorkshopEnrollTable {
    public class WorkshopEnroll{
        Integer WorkshopEnrollmentID;
        Integer ParticipantID;
        Integer WorkshopreservationID;

        public WorkshopEnroll(Integer workshopEnrollmentID, Integer participantID, Integer workshopreservationID) {
            WorkshopEnrollmentID = workshopEnrollmentID;
            ParticipantID = participantID;
            WorkshopreservationID = workshopreservationID;
        }

        @Override
        public String toString() {
            return "insert into WorkshopEnrollment (WorkshopEnrollmentID, ParticipantID, WorkshopreservationID) values " +
                    "(" + WorkshopEnrollmentID +
                    ", " + ParticipantID +
                    ", " + WorkshopreservationID +
                    ");\n";
        }
        public String toCSV() {
            return  WorkshopEnrollmentID +
                    ":" + ParticipantID +
                    ";" + WorkshopreservationID +
                    "\n";
        }
    }
    List<WorkshopEnroll> table = new LinkedList<>();
    WorkResGetter workResGetter = new WorkResGetter();
    ConfEnrollGetter confEnrollGetter = new ConfEnrollGetter();

    WorkshopEnrollTable()throws IOException{
        int counter =0;
        for(int i=0;i<workResGetter.workResList.size(); i++){
            LinkedList<ConfEnrollGetter.ConfEnr> possibleParticipants = getPossibleParticipant(workResGetter.workResList.get(i).ConfResID);
            for(int j=0;j<workResGetter.workResList.get(i).PlacesReseved;j++){
                counter++;
                int rand = new Random().nextInt(possibleParticipants.size());
                table.add(new WorkshopEnroll(counter,possibleParticipants.get(rand).ParticipantID,workResGetter.workResList.get(i).WorkreservationID));
                possibleParticipants.remove(rand);
            }

        }

    }

    private LinkedList<ConfEnrollGetter.ConfEnr> getPossibleParticipant(Integer confResID) {
        LinkedList<ConfEnrollGetter.ConfEnr> result= confEnrollGetter.ConfEnrList.stream().filter(confEnr -> confResID.equals(confEnr.ConfID)).collect(Collectors.toCollection(LinkedList::new));
        return result;
    }


}
