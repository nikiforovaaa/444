package barBossHouse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;

public final class Customer {
    String firstName, secondName;
    int age;
    LocalDate currentDate= LocalDate.now();
    LocalDate birthDate;
    public Address address;
    public static final String FIRST_NAME_DEFAULT = "";
    public static final String SECOND_NAME_DEFAULT = "";
    public static final int AGE_DEFAULT = -1;
    public static final int UNDERAGE_CUSTOMER = 14;
    public static final int ADULT_CUSTOMER = 21;
    public static final Customer MATURE_UNKNOWN_CUSTOMER = new Customer(ADULT_CUSTOMER);
    public static final Customer NOT_MATURE_UNKNOWN_CUSTOMER = new Customer(UNDERAGE_CUSTOMER);


    public  Customer(){
        this(FIRST_NAME_DEFAULT,SECOND_NAME_DEFAULT,AGE_DEFAULT,Address.EMPTY_ADDRESS);

    }
    public Customer(int age){
        this(FIRST_NAME_DEFAULT,SECOND_NAME_DEFAULT,age,Address.EMPTY_ADDRESS);
    }
    public Customer(String firstName, String secondName, int age, Address address){
        this.firstName=firstName;
        this.secondName=secondName;
        if ((currentDate.compareTo(getBirthDate()))<0) throw new IllegalArgumentException("Wrong date!");
        this.currentDate=currentDate;
        this.address=address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }


    public static int getAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return -1;
        }
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public int getAge1() {
        return age;
    }

    public Address getAddress() {
        return address;
    }
    @Override
    public String toString(){
        StringBuilder builderCustomer = new StringBuilder("Customer: ");
        if((secondName!=null) && (!secondName.isEmpty())) builderCustomer.append(secondName+" ");
        if((firstName!=null) && (!firstName.isEmpty())) builderCustomer.append(firstName+", ");
        if(getAge(birthDate, currentDate)!=AGE_DEFAULT) builderCustomer.append(getAge(birthDate, currentDate)+", ");
        if(address!=Address.EMPTY_ADDRESS) builderCustomer.append(address);
        return builderCustomer.toString();
    }
    //“Customer: <secondName> <firstName>, <age>, <address>”
    @Override
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null) return false;
        Customer equalsCheck = (Customer) obj;
        if((equalsCheck.secondName.equals(this.secondName))&&(equalsCheck.firstName.equals(this.firstName))&&(equalsCheck.address.equals(this.address)))
            return true;
        else
            return false;
    }
    @Override
    public int hashCode(){
        return secondName.hashCode()^firstName.hashCode()^getAge(birthDate, currentDate)^address.hashCode();
    }
}
