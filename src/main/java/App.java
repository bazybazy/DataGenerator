import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args){
        try{
            //saveParticipants();
             //saveCustomers();
            //saveWorkshops();
            //throw new IOException("a");
           // throw new IOException();
             //new ConfDaysGetter().dayPriceList.forEach(System.out::println);
           //new WorkShopReservationTable().table.forEach(System.out::println);
            // new WorkshopEnrollTable().table.forEach(System.out::println);
            //throw new IOException();
           // saveWorkshopEnrollments();
             savePayments();

        }catch(FileNotFoundException ex) {
            System.out.print("file not found! :(" + ex);
        }
        catch(IOException ex){
            System.out.print("SOmething is wrong with the file" + ex);

        }catch(NumberFormatException ex){
            System.out.print("probable error while parsing dates" + ex);
        }
    }

    private static void savePayments() throws IOException{
        StringBuilder SQLPayment = new StringBuilder();
       PaymentTable paymentTable = new PaymentTable();
        paymentTable.table.forEach(e -> SQLPayment
                .append(e.toString()));

        StringBuilder CSVPayment = new StringBuilder();
        paymentTable.table.forEach(e -> CSVPayment
                .append(e.toCSV()));
        printToFile(SQLPayment,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\Payments\\Payments.sql" );
        printToFile(CSVPayment,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\Payments\\Payments.csv");
    }

    private static void saveWorkshopEnrollments()throws IOException {
        StringBuilder SQLworkshopEnrollment = new StringBuilder();
        WorkshopEnrollTable workShopEnrollmentTable = new WorkshopEnrollTable();
        workShopEnrollmentTable.table.forEach(e -> SQLworkshopEnrollment
                .append(e.toString()));
        
        StringBuilder CSVworkshopEnrollment = new StringBuilder();
        workShopEnrollmentTable.table.forEach(e -> CSVworkshopEnrollment
                .append(e.toCSV()));
        printToFile(SQLworkshopEnrollment,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\WorkshopEnroll\\WorkshopEnroll.sql" );
        printToFile(CSVworkshopEnrollment,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\WorkshopEnroll\\WorkshopEnroll.csv");
    }

    private static void saveConfEnrollment()throws IOException {
        StringBuilder SQLEnrollReservation =new StringBuilder();
        new ConfEnrollTable().table.forEach(e -> SQLEnrollReservation
                .append(e.toString()).append('\n'));
        printToFile(SQLEnrollReservation,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfEnroll\\ConfEnrollment.sql");
    }
    /*
    private static void toUpper() throws IOException{
        StringBuilder x = new StringBuilder();
        List<String> y= Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\participants\\lastName.csv")).filter(e -> e.length() >1).collect(Collectors.toList());
        y.forEach(e -> x.append(e.substring(0,1) + e.substring(1,e.length()).toLowerCase()).append('\n'));
        printIntsToFile(x,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\participants\\lastName.csv");
    }*/
    private static void saveWorkshopReservation() throws IOException{
        StringBuilder SQLworkshopReservation = new StringBuilder();
        WorkShopReservationTable workShopReservationTable= new WorkShopReservationTable();
        workShopReservationTable.table.forEach(e -> SQLworkshopReservation
                                                        .append(e.toString()));
        StringBuilder CSVworkshopReservation = new StringBuilder();
        workShopReservationTable.table.forEach(e -> CSVworkshopReservation
                .append(e.toCSV()).append('\n'));
        printToFile(SQLworkshopReservation,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\WorkshopReservation\\WorkshopReservation.sql" );
        printToFile(CSVworkshopReservation,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\WorkshopReservation\\WorkshopReservation.csv");

    }

    private static void saveConferenceReservation() throws IOException {
        StringBuilder SQLConferenceReservation =new StringBuilder();
        new ConferenceReservationTable().table.forEach(e -> SQLConferenceReservation
                                                        .append(e.toString()));
        printToFile(SQLConferenceReservation,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConfReservation\\ConferenceReservation.sql");
    }

    private static void saveParticipants() throws IOException{
        StringBuilder SQLParticipants =new StringBuilder();
        new ParticipantsTable().table
                .forEach(e -> SQLParticipants
                        .append(e.toString()));

        printToFile(SQLParticipants,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\participants\\Participants.sql");
    }

    private static void saveCustomers()throws IOException {
        StringBuilder SQLCustomers =new StringBuilder();
        new CustomerTable().table
                .forEach(e -> SQLCustomers
                        .append(e.toString()));

        printToFile(SQLCustomers,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\customers\\Customers.sql");
    }

    private void saveConfDays()throws IOException{
        StringBuilder SQLConferenceDays =new StringBuilder();
        ConferenceDaysTable conferenceDaysTable =new ConferenceDaysTable();
        conferenceDaysTable.table
                .forEach(e -> SQLConferenceDays
                        .append("insert into ConferenceDaysTable (DayID, Day, ConferenceID, MaxParticipants) values ("+e.dayID+", "+e.date.toString()+", "+e.confID + ", "+ e.maxParticipants+ ");\n"));

        printToFile(SQLConferenceDays,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\ConferenceDays.sql");
    }

    private static void savePrices()throws IOException{
        StringBuilder SQLPrices =new StringBuilder();
        PricesTable pricesTable = new PricesTable();
        pricesTable.table
                .forEach(e -> SQLPrices
                        .append(e.toString()));

        printToFile(SQLPrices,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\Prices.sql");
    }
    private static void saveWorkshops() throws IOException{
        try {
            StringBuilder SQLWorkshops =new StringBuilder();
            WorkshopTable workshopTable = new WorkshopTable();
            workshopTable.table.forEach(e -> SQLWorkshops
                                        .append(e.toString()));
           printToFile(SQLWorkshops,"C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\Workshops.sql");
        }catch(ParseException ex){
            System.out.print("Wrong time format!");
        }
    }
    private static void printToFile(StringBuilder text, String path) throws IOException{
        PrintWriter writer = new PrintWriter(path);
        writer.print(text);
        writer.close();
    }

}
