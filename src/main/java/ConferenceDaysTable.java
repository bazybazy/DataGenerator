import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class ConferenceDaysTable {              //refactor the dates
    private class DoubleDay {
        Day start;
        Day end;

        DoubleDay(Day start, Day end) {
            this.start = start;
            this.end = end;
        }
    }

    List<Day> table = new LinkedList<>();

    ConferenceDaysTable() throws IOException {
        this.table = parse();
    }

    LinkedList<Day> parse() throws IOException {
        Random generator = new  Random();
        List<String> listOfConfs = Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\Conferences.sql")).collect(Collectors.toList());
        List<DoubleDay> datePairs = new LinkedList<>();
        for (int i = 0; i < listOfConfs.size(); i++) {
            extractDates(listOfConfs.get(i), datePairs, i + 1);
        }
        LinkedList<Day> confDays = addAllDates(datePairs);
        for(int i=0; i<confDays.size(); i++){
            confDays.get(i).setDayID(i+1);
            confDays.get(i).setMaxParticipants(80 + generator.nextInt(50));
        }
        return confDays;
    }

    private LinkedList<Day> addAllDates(List<DoubleDay> datePairs) {
        LinkedList<Day> result = new LinkedList<>();

        for (DoubleDay doubleDay : datePairs) {
            Day tmp = doubleDay.start;
            for (; !tmp.equals(doubleDay.end); tmp = tmp.next()) {
                result.add(tmp);
            }
            result.add(doubleDay.end);
        }
        return result;
    }

    private void extractDates(String e, List<DoubleDay> list, Integer ID) {
        Integer index = e.indexOf("201");     //dates start with a year. only 2014-2016 are on the list. and there are only 72 confs
        list.add(new DoubleDay(new Day(e.substring(index, index + 10), ID), new Day(e.substring(index + 14, index + 24), ID)));// in that places I have the begining and ending dates of Conferences
    }


}
