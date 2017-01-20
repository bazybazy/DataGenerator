import java.math.BigDecimal;

/**
 * Created by admin on 05.01.2017.
 */
public class Workshop {
    Integer workshopID;
    Integer DayID;
    String workshopName;
    String start;
    String finish;
    Double price;
    Integer isCancelled;
    Integer maxParticipants;

    public Workshop(Integer workshopID, Integer dayID, String workshopName, String start, String finish, Double price, Integer isCancelled, Integer maxParticipants) {
        this.workshopID = workshopID;
        this.DayID = dayID;
        this.workshopName = workshopName;
        this.start = start;
        this.finish = finish;
        this.price = price;
        this.isCancelled = isCancelled;
        this.maxParticipants = maxParticipants;
    }

    @Override
    public String toString() {
        return "insert into Workshops (WorkshopID, DayID, WorkshopName, Start, Finish, Price, isCancelled, MaxParticipants) values " +
                "(" + workshopID +
                ", " + DayID +
                ", '" + workshopName +
                "', '" + start +
                "', '" + finish +
                "', " + new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP) +
                ", " + isCancelled +
                ", " + maxParticipants +
                ");\n";
    }
}
