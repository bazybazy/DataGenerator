import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 07.01.2017.
 */
public class PaymentDueGetter {
    public class Record {
        Integer ConfresID;
        Integer isCancelled;
        Date reservationDate;
        Date untilDate;
        Double toPay;
        boolean first= false;

        public Record(Integer confresID, Integer isCancelled, Date reservationDate, Date untilDate, Double toPay) {
            ConfresID = confresID;
            this.isCancelled = isCancelled;
            this.reservationDate = reservationDate;
            this.untilDate = untilDate;
            this.toPay = toPay;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }
    }

    public class TrippleRecord {
        Record first;
        Record second;
        Record third;

        public TrippleRecord(Record first, Record second, Record third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    List<Record> toPayList = new LinkedList<>();

    PaymentDueGetter() throws IOException {
        List<String> toPayTrippleList= Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\Payments\\dozaplaty.txt")).collect(Collectors.toList());
        //int counter =0;
        for(int i=0;i<toPayTrippleList.size();i=i+3){
                toPayList.add(extractValidRecord(new TrippleRecord(extractData(toPayTrippleList.get(i)),extractData(toPayTrippleList.get(i+1)),extractData(toPayTrippleList.get(i+2)))));

        }

    }
    private Record extractData(String line){
        String[] tmp = line.split(";");



        return new Record(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]),new Date(tmp[2]),new Date(tmp[3]),Double.parseDouble(tmp[4]));
    }

    private Record extractValidRecord(TrippleRecord trippleRecord){
        if(trippleRecord.third.reservationDate.smaller(trippleRecord.third.untilDate) || trippleRecord.third.reservationDate.equals(trippleRecord.third.untilDate))
            return trippleRecord.third;
        if(trippleRecord.second.reservationDate.smaller(trippleRecord.second.untilDate) || trippleRecord.second.reservationDate.equals(trippleRecord.second.untilDate))
            return trippleRecord.second;
        else {
            trippleRecord.first.setFirst(true);
            return trippleRecord.second;
        }
    }


}
