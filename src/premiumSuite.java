/**
 * the class about the premium Suite
 */
public class premiumSuite extends property {
    public String lastMaintenance;

    /**
     * the constructor of apartment
     *
     * @param propertyID
     * @param streetNum
     * @param streetName
     * @param suburb
     */
    public premiumSuite(String propertyID, String streetNum, String streetName, String suburb, String lastMaintenance) {
        this.propertyID = propertyID;
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.suburb = suburb;
        this.lastMaintenance = lastMaintenance;
        this.bedroomsNumber = 3;
        this.propertyType = 0;
        this.propertyStatus = 1;
        this.rentalRecord = new String[10][6];//colum 1:Record id ;2:rentDate 3:Estimated return date ;4:Actual return date; 5:Rental fee; 6:Late fee
    }

    @Override
    public String getDetails() {
        return null;
    }

    @Override
    public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
        return false;
    }

    @Override
    public boolean returnProperty(DateTime returnDate) {
        return false;
    }

    @Override
    public boolean performMaintenance() {
        return false;
    }

    @Override
    public boolean completeMaintenance(DateTime completionDate) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void addRecords(String customerid, String rentDate, int rentDay, property p) {

    }


    @Override
    public void addReturnRecords(DateTime dt, property p) {

    }

    @Override
    protected String calculateActualFee() {
        return null;
    }

    @Override
    protected String calculateLateFee() {
        return null;
    }

}
