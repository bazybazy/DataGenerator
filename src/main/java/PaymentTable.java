import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by admin on 07.01.2017.
 */
public class PaymentTable {
    public class Payment{
        Integer PaymentID;
        Integer ConferenceReservationID;
        Double  PaymentValue;
        Date PaymentDate;

        @Override
        public String toString() {
            return "insert into Payments (PaymentID, ConferenceReservationID, PaymentValue, PaymentDate) values" +
                    "(" + PaymentID +
                    ", " + ConferenceReservationID +
                    ", " +  new BigDecimal(PaymentValue).setScale(2, BigDecimal.ROUND_HALF_UP) +
                    ", " + PaymentDate +
                    ");\n";
        }
        public String toCSV() {
            return  PaymentID +
                    ";" + ConferenceReservationID +
                    ";" +  new BigDecimal(PaymentValue).setScale(2, BigDecimal.ROUND_HALF_UP) +
                    ";" + PaymentDate +
                    "\n";
        }

        public Payment(Integer paymentID, Integer conferenceReservationID, Double paymentValue, Date paymentDate) {
            PaymentID = paymentID;
            ConferenceReservationID = conferenceReservationID;
            PaymentValue = paymentValue;
            PaymentDate = paymentDate;

        }
    }
    List<Payment> table = new LinkedList<>();

    PaymentTable()throws IOException {
            PaymentDueGetter paymentDueGetter =new PaymentDueGetter() ;
        int counter =0;
            for(int i=0; i< paymentDueGetter.toPayList.size();i++){
                Double toPay = paymentDueGetter.toPayList.get(i).toPay;
                    if(paymentDueGetter.toPayList.get(i).isCancelled ==1){
                        if(new Random().nextInt(1000) >850 && toPay > 0.01){
                            counter++;
                            table.add(new Payment(counter,paymentDueGetter.toPayList.get(i).ConfresID,((Integer) (new Random().nextInt(toPay.intValue()))).doubleValue()+1.0,randDate(paymentDueGetter.toPayList.get(i))));
                        }
                    }
                    else{
                        int rand =new Random().nextInt(1000);
                        if( rand<600 &&toPay > 0.01){ counter++; table.add(new Payment(counter,paymentDueGetter.toPayList.get(i).ConfresID,toPay,randDate(paymentDueGetter.toPayList.get(i))));}
                        else {
                            int rand1 =new Random().nextInt(6);
                            for(int j=0;j<rand1;j++){
                                Double randprice = ((Integer)new Random().nextInt(toPay.intValue())).doubleValue() + Math.abs(toPay - ((Integer) toPay.intValue()).doubleValue()) ;
                                if(toPay > 0.01){
                                    counter++;
                                    table.add(new Payment(counter,paymentDueGetter.toPayList.get(i).ConfresID,randprice,randDate(paymentDueGetter.toPayList.get(i))));
                                    toPay=toPay -randprice;
                                } else break;
                            }
                            if(toPay > 0.01 && rand <970){
                                counter++;
                                table.add(new Payment(counter,paymentDueGetter.toPayList.get(i).ConfresID,toPay,randDate(paymentDueGetter.toPayList.get(i))));
                            }

                        }
                    }

            }
    }

    private Date randDate(PaymentDueGetter.Record record) {
        Date max =record.untilDate;
        Date result = record.reservationDate;
        if(record.first)
             max = record.untilDate.prevDay().prevDay().prevDay().prevDay().prevDay().prevDay().prevDay().prevDay().prevDay();
        if(max.smaller(result)) return result;

        if(max.year>(record.reservationDate.year)) return record.reservationDate;
            int rand = new Random().nextInt(40);
            for(int i=0;i<rand;i++){
                if(result.equals(max)) return result.prevDay();
                result = result.nextDay();
            }
        return result;
    }
}
