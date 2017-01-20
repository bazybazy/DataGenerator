import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;// zwiększyć ilosć osób w rezerwacjach, dodać zapisywanie do plików

/**
 * Created by admin on 05.01.2017.
 */
public class ConferenceReservationTable {
    public class ConferenceReservation{
        Integer confResID;
        Integer studPlacesReserved;
        Integer adultPlacesReserced;
        Integer isCancelled;
        Date resDate;
        Integer customerID;
        Integer dayID;

        public ConferenceReservation(Integer confResID, Integer studPlacesReserved, Integer adultPlacesReserced, Integer isCancelled, Date resDate, Integer customerID, Integer dayID) {
            this.confResID = confResID;
            this.studPlacesReserved = studPlacesReserved;
            this.adultPlacesReserced = adultPlacesReserced;
            this.resDate = resDate;
            this.customerID = customerID;
            this.dayID = dayID;
            this.isCancelled = isCancelled;
        }

        @Override
        public String toString() {
            return "insert into ConferenceReservation (ConferenceReservationID, StudentPlacesReserved, AdultPlacesReserved, IsCancelled, ReservationDate, CustomerID, DayID) values " +
                    "(" + confResID +
                    ", " + studPlacesReserved +
                    ", " + adultPlacesReserced +
                    ",  "+isCancelled +
                    ", " + resDate +
                    ", " + customerID +
                    ", " + dayID +
                    ");\n";
        }
    }
    public class Places{
        Integer studPlaces;
        Integer adultPlaces;
        public Places(Integer studPlaces, Integer adultPlaces){
            this.studPlaces=studPlaces;
            this.adultPlaces =adultPlaces;
        }
    }

    List<ConferenceReservation> table = new LinkedList<>();

    public ConferenceReservationTable() throws IOException{
        ConfDaysGetter confDaysGetter = new ConfDaysGetter();
        CustomerTable customerTable = new CustomerTable();
        List<Integer> studPlacesList = new LinkedList<>();
        List<Integer> adultPlacesList = new LinkedList<>();
        List<Integer> isCancelledList = new LinkedList<>();
        List<Date> resDateList = new LinkedList<>();
        List<Integer> customerIDList = new LinkedList<>();
        List<Integer> dayIDList = new LinkedList<>();
        Integer counter =0;


        for(ConfDaysGetter.ConfDay confDay : confDaysGetter.dayPriceList) {
            Integer totalPeople = confDay.dayCapacity;
                while(true){
                    int rand = new Random().nextInt(customerTable.table.size());
                    Integer company = customerTable.table.get(rand).isCompany;
                    Integer customerID = customerTable.table.get(rand).customerID;
                    Integer cancelled =generateIsCancelled();
                    Places places = generatePlaces(1,company);
                    Date date = generateDate(confDay.dayDate);

                    if(cancelled !=1) {
                        totalPeople -= places.adultPlaces;
                        totalPeople -= places.studPlaces;
                    }
                    if(totalPeople<0) break;
                    counter++;
                    studPlacesList.add(places.studPlaces);
                    adultPlacesList.add(places.adultPlaces);
                    isCancelledList.add(cancelled);
                    resDateList.add(date);
                    customerIDList.add(customerID);
                    dayIDList.add(confDay.dayID);
                    table.add(new ConferenceReservation(counter,places.studPlaces,places.adultPlaces,cancelled,date,customerID,confDay.dayID));
                }
        }
        //System.out.println(table.stream().filter(e-> e.isCancelled==0).mapToInt(e-> e.adultPlacesReserced + e.studPlacesReserved).sum());
        //System.out.println(confDaysGetter.dayPriceList.stream().mapToInt(e -> e.dayCapacity).sum());
        ToFilePrinter.printIntsToFile(studPlacesList,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfReservation\\studPlaces.txt");
        ToFilePrinter.printIntsToFile(adultPlacesList,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfReservation\\adultPlaces.txt");
        ToFilePrinter.printIntsToFile(isCancelledList,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfReservation\\isCancelled.txt");
        ToFilePrinter.printIntsToFile(customerIDList,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfReservation\\customerID.txt");
        ToFilePrinter.printIntsToFile(dayIDList,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfReservation\\dayIDList.txt");
        StringBuilder text =new StringBuilder();
        resDateList.forEach(e -> text.append(e.toString()).append('\n'));
        PrintWriter writer = new PrintWriter("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfReservation\\resDay.txt");
        writer.print(text);
        writer.close();




    }

    private Date generateDate(Date dayDate) {
        int x = new Random().nextInt(3)+1;
        int y = new Random().nextInt(32);
        Date result ;
        switch(x){
            case 1: result = dayDate.prevMonth().primitiveNextNDays(y); break;
            case 2: return dayDate.prevMonth().prevMonth().primitiveNextNDays(y);
            case 3: return dayDate.prevMonth().prevMonth().prevMonth().primitiveNextNDays(y);
            default: throw new IllegalArgumentException("something's wrong with random generator in ConfResTable.generateData");
        }
        if(dayDate.smaller(result)) return dayDate.prevDay();
        else return result;
    }

    private Places generatePlaces(int i, int company) {
        Random x = new Random();
        int rand =x.nextInt(1000);
        if(company ==1){
            if(rand<30) return new Places(0,x.nextInt(15)+13);
            else if(rand<100) return new Places(0,x.nextInt(20)+1);
            else if(rand <600){
                return new Places(0,x.nextInt(8)+1);
            }else if(rand<800) return new Places(x.nextInt(5)+1,x.nextInt(5)+1);
            else if(rand<980) return new Places(x.nextInt(6)+1,0);
            else return new Places(x.nextInt(15)+1,0);
        }
        else{
            if(rand<20) return new Places(0,x.nextInt(10)+5);
            else if(rand<40) return new Places(x.nextInt(7)+1,0);
            else if (rand<400) return new Places(0,1);
            else if(rand<600) return new Places(1,0);
            else if(rand<800) return new Places(0,x.nextInt(4)+1);
            else if(rand<900) return new Places(x.nextInt(6)+1,0);
            else return new Places(x.nextInt(4)+1,x.nextInt(4)+1);
        }
    }

    private Integer generateIsCancelled() {
        if(new Random().nextInt(1000) >930 ) return 1;
        else return 0;
    }




}



