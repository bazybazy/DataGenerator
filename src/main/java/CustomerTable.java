import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by admin on 05.01.2017.
 */
public class CustomerTable {
    public class Customer {
        Integer customerID;
        Integer isCompany;
        String companyName;
        String contactName;
        String phone;
        String email;

        public Customer(Integer customerID, Integer isCompany, String companyName, String contactName, String phone, String email) {
            this.customerID = customerID;
            this.isCompany = isCompany;
            this.companyName = companyName;
            this.contactName = contactName;
            this.phone = phone;
            this.email = email;
        }

        @Override
        public String toString() {
            if(isCompany ==1)
            return "insert into Customers (CustomerID, IsCompany, CompanyName, ContactName, Phone, Email) values " +
                    "(" + customerID +
                    ", "+ isCompany +
                    ", '" + companyName  +
                    "', '" + contactName  +
                    "', '" + phone +
                    "', '" + email +
                    "');\n";
            else
                return "insert into Customers (CustomerID, IsCompany, CompanyName, ContactName, Phone, Email) values " +
                    "(" + customerID +
                    ", "+ isCompany +
                    ", " + companyName  +
                    ", '" + contactName  +
                    "', '" + phone +
                    "', '" + email +
                    "');\n";
        }
    }

    List <Customer> table =new LinkedList<>();

    public CustomerTable() throws IOException {
        List<String> companyNameList = Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\customers\\CustomerCompanyName.csv")).collect(Collectors.toList());
        List<String> contactNameList = Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\customers\\CustomerCOntactName.csv")).collect(Collectors.toList());
        List<String> phoneList = Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\customers\\CustomerPhone.csv")).collect(Collectors.toList());
        List<String> emailList = Files.lines(Paths.get("C:\\Users\\admin\\Desktop\\WIET\\Bazy\\Dane\\customers\\CustomerEmail.csv")).collect(Collectors.toList());
        int companyIndex =0;
        int individualIndex =0;
        for(int i=1; i<2999; i++){
            int x =new Random().nextInt(2);
            if(x==1)
                companyIndex++;
            individualIndex++;
        table.add(new Customer(i,x,generateCompanyName(companyNameList,x,companyIndex),generateData(contactNameList,individualIndex),generateData(phoneList,individualIndex),generateData(emailList,individualIndex)) );
        }

    }

    private String generateData(List<String> contactNameList, int individualIndex) {
        return contactNameList.get(individualIndex);
    }

    private String generateCompanyName(List<String> companyNameList, int x, int index) {
        if( x==0) return "NULL";
        else return companyNameList.get(index);
    }
}
