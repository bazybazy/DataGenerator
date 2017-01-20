/**
 * Created by admin on 04.01.2017.
 */
public class Day {
    Date date;
    Integer confID = -1;
    Integer maxParticipants=-1;
    Integer dayID=-1;

    @Override
    public String toString() {
        return date.year + "-" + date.monthDayFormat(date.month) + "-" + date.monthDayFormat(date.day) + "  confID = " + confID + " dayIDList = " + dayID + " max participants = " + maxParticipants ;
    }
    Day(Integer year, Integer month, Integer day, Integer ID) {
        this.date = new Date(year,month,day);
        this.confID = ID;
    }
    Day(String date, Integer ID) {
        this.date = new Date(Integer.parseInt(date.substring(0, 4)),Integer.parseInt(date.substring(5, 7)),Integer.parseInt(date.substring(8, 10)));
        this.confID=ID;
    }
    Day next() {
        if (date.day == 31) {
            if (date.month == 12) return new Day(date.year + 1, 1, 1, confID);
            else return new Day(date.year, date.month + 1, 1, confID);
        } else if (date.day == 30 && (date.month == 4 || date.month == 6 || date.month == 9 || date.month == 11))
            return new Day(date.year, date.month + 1, 1, confID);
        else if ((date.month == 2 && date.day == 28 && date.year != 2016) || ((date.month == 2 && date.day == 29 && date.year == 2016)))
            return new Day(date.year, date.month + 1, 1, confID);
        else return new Day(date.year, date.month, date.day + 1, confID);
    }
    @Override
    public boolean equals(Object o) {           // only date! without ID
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day = (Day) o;

        return this.date.equals(day.date);
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setDayID(Integer dayID) {
        this.dayID = dayID;
    }
}
