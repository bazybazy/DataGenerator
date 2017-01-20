class Date {
    Integer year;
    Integer month;
    Integer day;

    Date(Integer year, Integer month, Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;

    }
    Date(String date){
        this.year = Integer.parseInt(date.substring(0, 4));
        this.month =Integer.parseInt(date.substring(5, 7));
        this.day =Integer.parseInt(date.substring(8, 10));
    }

    public String toString() {
        return "'" + year + "-" + monthDayFormat(month) + "-" + monthDayFormat(day) + "'";
    }

    String monthDayFormat(Integer x) {
        if (x > 9) return x.toString();
        else return "0" + x;
    }
    Date primitiveNextNDays(int n){
        Date date = new Date(year, month,day);
        for(int i=0;i<n;i++)
            date=date.nextDay();
        return date;
    }

    Date nextDay() {
        if (day == 31) {
            if (month == 12) return new Date(year + 1, 1, 1);
            else return new Date(year, month + 1, 1);
        } else if (day == 30 && (month == 4 || month == 6 || month == 9 || month == 11))
            return new Date(year, month + 1, 1);
        else if ((month == 2 && day == 28 && year != 2016) || ((month == 2 && day == 29 && year == 2016)))
            return new Date(year, month + 1, 1);
        else return new Date(year, month, day + 1);
    }
    Date prevDay() {
        if(day == 1 && month ==1) return new Date(year -1, 12, 31);
        else if (day==1 &&(month == 5 || month == 7 || month == 10 || month == 12)) return new Date(year,month-1,30);
        else if(day==1 && month ==3 && year ==2016 )return new Date(year,2,29);
        else if(day==1 && month ==3  )return new Date(year,2,28);
        else if(day ==1 )return new Date(year,month-1, 31);
        else return new Date(year,month,day-1);
    }


    Date prevMonth() {
        if (month == 1) return new Date(year - 1, 12, day);
        else if (day == 31 && (month == 5 || month == 7 || month == 10 || month == 12))
            return new Date(year, month - 1, 30);
        else if (day > 29 && month == 3 && year == 2016) return new Date(2016, 2, 29);
        else if (day > 28 && month == 3 && year != 2016) return new Date(2016, 2, 28);
        else return new Date(year, month - 1, day);
    }

    @Override
    public boolean equals(Object o) {           // only date! without ID
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Date date = (Date) o;

        if (!year.equals(date.year)) return false;
        if (!month.equals(date.month)) return false;
        return day.equals(date.day);
    }

    public boolean smaller(Date result) {
        if (year < result.year) return true;
        else if(year > result.year) return false;
        else if (month< result.month) return true;
        else if (month > result.month) return false;
        else if (day < result.day) return true;
        else return false;
    }
}


