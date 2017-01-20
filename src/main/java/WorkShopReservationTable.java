import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by admin on 06.01.2017.
 */
public class WorkShopReservationTable {
    public class  WorkShopReservation{
        Integer workshopReservationID;
        Integer workShopID;
        Integer placesReserved;
        Integer isCancelled;
        Integer ConferenceReservationID;

        public WorkShopReservation(Integer workshopReservationID, Integer workShopID, Integer placesReserved, Integer isCancelled, Integer conferenceReservationID) {
            this.workshopReservationID = workshopReservationID;
            this.workShopID = workShopID;
            this.placesReserved = placesReserved;
            this.isCancelled = isCancelled;
            this.ConferenceReservationID = conferenceReservationID;
        }

        @Override
        public String toString() {
            return "insert into WorkshopReservation (WorkshopReservationID, WorkshopID, PlacesReserved, IsCancelled, ConferenceReservationID) values "  +
                    "(" + workshopReservationID +
                    ", " + workShopID +
                    ", " + placesReserved +
                    ", " + isCancelled +
                    ", " + ConferenceReservationID +
                    ");\n";
        }
        public String toCSV(){
            return workshopReservationID + ";" +  workShopID  + ";" +  placesReserved  + ";" +  isCancelled  + ";" +  ConferenceReservationID;
        }

    }
   public class ConfReservation{
        Integer Places;
        Integer confID;
        Integer isCancelled;
        Integer dayID;
        ConfReservation(Integer Places, Integer confID, Integer isCancelled,Integer dayID){
            this.Places=Places;
            this.confID=confID;
            this.isCancelled =isCancelled;
            this.dayID = dayID;
        }

        public void decreasePlaces(Integer places) {
            Places = Places-places;
        }
    }
    List<WorkShopReservation> table = new LinkedList<>();

    List<Integer> studentPlaces = new ArrayList<>();
    List<Integer> adultPlaces = new ArrayList<>();
    List<Integer> dayIDList = new ArrayList<>();
    List<Integer> isCancelled = new ArrayList<>();

    ArrayList<ConfReservation> confReservationList= new ArrayList<>();

    WorkShopReservationTable() throws IOException{   //czzzaaaaassss nie zabezpieczony!!!!!!!!!!!!!
        getInfoFromConfRes();
        WorkshopsGetter workshopsGetter= new WorkshopsGetter();

        int counter =0;
        for(int i=0; i<workshopsGetter.workshopDatas.size();i++) {

            int capacity = workshopsGetter.workshopDatas.get(i).workshopCapacity;
            LinkedList<ConfReservation> possibleConfRes = getPossibleConfRes(workshopsGetter.workshopDatas.get(i).dayID);
            while(true){
                counter++;
                Integer rand = new Random().nextInt(possibleConfRes.size());
                if(possibleConfRes.get(rand).Places == 0) break;
                Integer placesOccupied = new Random().nextInt(possibleConfRes.get(rand).Places)+1;
                if(capacity -placesOccupied <0) break;
                Integer cancelled;
                if(possibleConfRes.get(rand).isCancelled ==1) cancelled=1;
                else cancelled =randCancelled();
                if(cancelled == 0){
                    possibleConfRes.get(rand).decreasePlaces(placesOccupied);
                    capacity -= placesOccupied;
                }
                table.add(new WorkShopReservation(counter,i+1,placesOccupied,cancelled,possibleConfRes.get(rand).confID));
            }

        }

    }

    private LinkedList<ConfReservation> getPossibleConfRes(Integer dayID) {
        LinkedList<ConfReservation> result = new LinkedList<>();
        for(int i=0; i< confReservationList.size();i++)
            if(dayID.equals(confReservationList.get(i).dayID)) result.add( confReservationList.get(i));
        return result;
    }

    private int randCancelled() {
        if(new Random().nextInt(1000)>970) return 1;
        else return 0;
    }




    void getInfoFromConfRes() throws IOException{
        Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfReservation\\studPlaces.txt")).mapToInt(Integer::parseInt).forEach(studentPlaces::add);
        Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfReservation\\adultPlaces.txt")).mapToInt(Integer::parseInt).forEach(adultPlaces::add);
        Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfReservation\\dayID.txt")).mapToInt(Integer::parseInt).forEach(dayIDList::add);
        Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfReservation\\isCancelled.txt")).mapToInt(Integer::parseInt).forEach(isCancelled::add);
        for(int i=0;i<studentPlaces.size();i++)
            confReservationList.add(new ConfReservation(studentPlaces.get(i)+adultPlaces.get(i),i+1,isCancelled.get(i),dayIDList.get(i)));
    }
}
