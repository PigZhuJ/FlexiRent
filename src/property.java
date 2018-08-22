import java.text.ParseException;

/**
 * the abstract class about Apartment and Premium Suite
 */
public abstract class property {
    public String propertyID;
    public String streetNum;
    public String streetName;
    public String suburb;
    public int bedroomsNumber;
    public int propertyType;//0: appartment 1:Premium Suite
    public int propertyStatus;//0 : being rented 1 : under maintenance
    public String[][] rentalRecord;
    /**
     * the abstract method which can get details about the property
     * @return string
     */
    public abstract String getDetails();
    public abstract boolean rent(String customerId, DateTime rentDate, int numOfRentDay);
    //detemine can return or don't return
    public abstract boolean returnProperty(DateTime returnDate);
    public abstract boolean performMaintenance();
    public abstract boolean completeMaintenance(DateTime completionDate);
    public abstract String toString();
    public abstract void addRecords(String customerid, String rentDate, int rentDay,property p);
    public abstract void addReturnRecords(DateTime dt,property p) throws ParseException;

    protected abstract String calculateActualFee();

    protected abstract String calculateLateFee();
}
