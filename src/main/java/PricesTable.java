import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by admin on 04.01.2017.
 */
public class PricesTable {
    List<Prices> table= new LinkedList<>();

    PricesTable() throws IOException {
        Random generator = new Random();

        ConferenceDaysTable conferenceDaysTable = new ConferenceDaysTable();
        for(int i=0; i<conferenceDaysTable.table.size(); i++){
           Float price=50 +generator.nextInt(100)+generator.nextFloat();
            table.add(new Prices(3*i+1,conferenceDaysTable.table.get(i).dayID, price, conferenceDaysTable.table.get(i).date));
            table.add(new Prices(3*i+2,conferenceDaysTable.table.get(i).dayID, price * 0.9f, conferenceDaysTable.table.get(i).date.prevMonth()));
            table.add(new Prices(3*i+3,conferenceDaysTable.table.get(i).dayID, price * 0.8f, conferenceDaysTable.table.get(i).date.prevMonth().prevMonth()));
        }
    }

}
