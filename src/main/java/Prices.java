import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by admin on 04.01.2017.
 */
public class Prices {
    Integer priceID;
    Integer dayID;
    Float price;
    Date untilDate;

    Prices(Integer priceID, Integer dayID, Float price, Date untilDate){
        this.priceID = priceID;
        this.dayID = dayID;
        this.price = price;
        this.untilDate=untilDate;
    }
    public String toString(){
        DecimalFormat f = new DecimalFormat("##.00");
        return "insert into Prices (PriceID, UntilDate, Price, DayID) values ("+priceID+", "+ untilDate+", "+ new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP) +", "+dayID+");\n ";
    }//insert into Prices (PriceID, first_name, last_name, email, gender, ip_address) values

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

