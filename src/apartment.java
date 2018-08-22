import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * the class about the apartment
 */
public class apartment extends property {


    /**
     * the constructor of apartment
     *
     * @param propertyID
     * @param streetNum
     * @param streetName
     * @param suburb
     * @param bedroomsNumber
     */
    public apartment(String propertyID, String streetNum, String streetName, String suburb, int bedroomsNumber) {
        this.propertyID = propertyID;
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.suburb = suburb;
        this.bedroomsNumber = bedroomsNumber;
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
        return true;
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
    public void addRecords(String customerid, String rentDate, int rentDay, property p) {
        String[][] s = new String[10][6];
        DateTime dt = new DateTime(rentDate);
        s[0][0] = p.propertyID + "_" + customerid + "_" + dt.getEightDigitDate();
        s[0][1] = dt.getEightDigitDate();
        s[0][2] = new DateTime(dt, rentDay).getFormattedDate();
        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j <6 ; j++) {
                s[i+1][j]=p.rentalRecord[i][j];
            }
        }
        p.rentalRecord=s;
    }

    @Override
    public void addReturnRecords(DateTime dt, property p) throws ParseException {
        //dt actual return time
        //rent time
        DateTime rentDateTime=new DateTime(new SimpleDateFormat("ddMMyyyy").parse(p.rentalRecord[0][1]).getTime());
        //Estimated return date
        DateTime estimateDateTime=new DateTime(p.rentalRecord[0][2]);

        p.rentalRecord[0][3]=dt.getFormattedDate();
        p.rentalRecord[0][4]=p.calculateActualFee();
        p.rentalRecord[0][5]=p.calculateLateFee();
    }

    @Override
    protected String calculateActualFee() {
        return null;
    }

    @Override
    protected String calculateLateFee() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
