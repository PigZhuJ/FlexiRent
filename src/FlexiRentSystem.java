import java.util.Arrays;
import java.util.Scanner;

public class FlexiRentSystem {
    propertyArray pa = new propertyArray();

    /**
     * start the system
     */
    public void startRun() throws InterruptedException {
        showMenu();
        System.out.println("Enter your choice:");
        int input = getInputInteger();
        if (input == 1) {
            addProperty();
        } else if (input == 2) {
            rentProperties();
        } else if (input == 3) {
            returnProperty();
        } else {
            startRun();
        }
    }


    /**
     * add new Prpperty
     */
    private void addProperty() throws InterruptedException {
        System.out.println("*******Add Property*******");
        System.out.println("please input the Property ID :");
        //get the input propertyID
        String propertyID = getInputString().trim();
        //determine what kind of property to add
        if (propertyID.startsWith("A_")) {
            if (pa.propertyIDisExisted(propertyID)) {
                //get street Number
                System.out.println("Enter street Number :");
                String streetNum = getInputString().trim();
                //get street Name
                System.out.println("Enter street Name :");
                String streetName = getInputString().trim();
                //get suburb
                System.out.println("Enter street suburb :");
                String suburb = getInputString().trim();
                //get the number of bedrooms
                System.out.println("Enter the numbers of bedrooms :");
                int number = getInputInteger();
                if (number == 1 || number == 2 || number == 3) {
                    apartment a = new apartment(propertyID, streetNum, streetName, suburb, number);
                    //add property to array
                    pa.addProperty(a);
                    startRun();
                } else {
                    System.err.println("Error input : the number of bedrooms of the property");
                    Thread.sleep(100);
                    startRun();
                }
            } else {
                System.err.println("The property have existed !");
                startRun();
            }
        } else if (propertyID.startsWith("S_")) {
            if (pa.propertyIDisExisted(propertyID)) {
                //get street Number
                System.out.println("Enter street Number :");
                String streetNum = getInputString().trim();
                //get street Name
                System.out.println("Enter street Name :");
                String streetName = getInputString().trim();
                //get suburb
                System.out.println("Enter street suburb :");
                String suburb = getInputString().trim();
                //get lastMaintenance
                System.out.println("Enter lastMaintenance :");
                String lastMaintenance = getInputString().trim();
                premiumSuite p = new premiumSuite(propertyID, streetNum, streetName, suburb, lastMaintenance);
                pa.addProperty(p);
                startRun();
            } else {
                System.err.println("The property have existed !");
                startRun();
            }
        } else {
            System.err.println("Error PropertyID");
            startRun();
        }
    }

    /**
     * Rent Property
     *
     * @throws InterruptedException
     */
    private void rentProperties() throws InterruptedException {
        System.out.println("***********Rent Property**********");
        System.out.println("Enter property id:");
        String propertyid = getInputString().trim();
        if (propertyid.startsWith("A_")) {
            rentApartment(propertyid);
        } else if (propertyid.startsWith("S_")) {
            rentPremiumsuite(propertyid);
        } else {
            System.err.println("ERROR PROPERTY !!!");
        }
    }


    /**
     * rent apartment
     *
     * @param propertyid
     * @throws InterruptedException
     */
    private void rentApartment(String propertyid) throws InterruptedException {
        //check the status propertyStatus==0;being rental
        property p = pa.getPropertyAccordingThePropertyID(propertyid);
        if (p.propertyStatus == 1) {
            System.out.println("Customer id: ");
            String customerid = getInputString().trim();
            System.out.println("Rent date (dd/mm/yyyy): ");
            String rentDate = getInputString().trim();
            String[] rentDateArr = rentDate.split("/");
            System.out.println("How many days?: ");
            int rentDay = getInputInteger();
            if (p.rent(customerid, new DateTime(Integer.valueOf(rentDateArr[0]), Integer.valueOf(rentDateArr[1]), Integer.valueOf(rentDateArr[2])), rentDay)) {
                System.out.println("Appartment " + propertyid + " is now rented by customer " + customerid);
                p.propertyStatus=0;
                p.addRecords(customerid, rentDate, rentDay, p);
                System.out.println(Arrays.deepToString(p.rentalRecord));
                startRun();
            } else {
                System.out.println("Apartment " + propertyid + "could not be rented");
                startRun();
            }
        } else {
            System.out.println("Apartment " + propertyid + " could not be rented");
            startRun();
        }
    }

    /**
     * rent preminum suite
     *
     * @param propertyid
     * @throws InterruptedException
     */
    private void rentPremiumsuite(String propertyid) throws InterruptedException {
        //check the status propertyStatus==0;being rental
        property p = pa.getPropertyAccordingThePropertyID(propertyid);
        if (p.propertyStatus == 1) {
            System.out.println("Customer id: ");
            String customerid = getInputString().trim();
            System.out.println("Rent date (dd/mm/yyyy): ");
            String rentDate = getInputString().trim();
            String[] rentDateArr = rentDate.split("/");
            System.out.println("How many days?: ");
            int rentDay = getInputInteger();
            if (p.rent(customerid, new DateTime(Integer.valueOf(rentDateArr[0]), Integer.valueOf(rentDateArr[1]), Integer.valueOf(rentDateArr[2])), rentDay)) {
                System.out.println("Premium Suite " + propertyid + " is now rented by customer " + customerid);
                p.propertyStatus=0;
                p.addRecords(customerid, rentDate, rentDay, p);
                System.out.println(Arrays.deepToString(p.rentalRecord));
                startRun();
            } else {
                System.out.println("Premium Suite " + propertyid + "could not be rented");
                startRun();
            }
        } else {
            System.out.println("Premium Suite " + propertyid + " could not be rented");
            startRun();
        }
    }


    private void returnProperty() throws InterruptedException {
        System.out.println("***********Return Property***************");
        System.out.println("Enter property id:");
        //get the propertyID
        String propertyID = getInputString().trim();
        if (pa.propertyIDisExisted(propertyID) == false) {
            System.out.println("Return date (dd/mm/yyyy): ");
            String returnDate = getInputString().trim();
            DateTime dt = new DateTime(returnDate);
            property p = pa.getPropertyAccordingThePropertyID(propertyID);
            if (propertyID.startsWith("A_")) {
                if (p.returnProperty(dt)) {//@TODO finish return Property
                    System.out.println("Apartment"+ propertyID+"is returned by customer "+p.rentalRecord[0][0].split("_")[1]);
                    System.out.println("Property ID: "+propertyID);
                    System.out.println("Address: "+p.streetNum+" "+p.streetName+" "+p.suburb);
                    System.out.println("Type:  Apartment");
                    System.out.println("Bedroom: "+p.bedroomsNumber);
                    p.propertyStatus=1;
                    p.addReturnRecords(dt,p);
                    System.out.println("Status: Available");
                    System.out.println("Record");
                    System.out.println("ID : "+p.rentalRecord[0][0]);
                    System.out.println("Rent Date : "+p.rentalRecord[0][1]);
                    System.out.println("Estimated Return Date : "+p.rentalRecord[0][2]);
                    System.out.println("Actual Return Date : "+p.rentalRecord[0][1]);
                    System.out.println("Rental Fee : "+p.rentalRecord[0][1]);
                    System.out.println("Late Fee : "+p.rentalRecord[0][1]);
                    startRun();
                } else {
                    System.out.println("Can't Return!!!");
                    startRun();
                }
            } else if (propertyID.startsWith("S_")) {
                if (p.returnProperty(dt)) {
                    System.out.println("Premium Suite"+ propertyID+"is returned by customer "+p.rentalRecord[0][0].split("_")[1]);
                    System.out.println("Property ID: "+propertyID);
                    System.out.println("Address: "+p.streetNum+" "+p.streetName+" "+p.suburb);
                    System.out.println("Type:  Premium Suite");
                    System.out.println("Bedroom: "+p.bedroomsNumber);
                    p.propertyStatus=1;
                    p.addReturnRecords(dt,p);
                    System.out.println("Status: Available");
                    System.out.println("Record");
                    System.out.println("ID : "+p.rentalRecord[0][0]);
                    System.out.println("Rent Date : "+p.rentalRecord[0][1]);
                    System.out.println("Estimated Return Date : "+p.rentalRecord[0][2]);
                    System.out.println("Actual Return Date : "+p.rentalRecord[0][1]);
                    System.out.println("Rental Fee : "+p.rentalRecord[0][1]);
                    System.out.println("Late Fee : "+p.rentalRecord[0][1]);
                    startRun();
                } else {
                    System.out.println("Can't Return!!!");
                    startRun();
                }
            }
        } else {
            System.out.println("ERROR PROPERTY !!!");
            showMenu();
        }
    }

    /**
     * show the FlexiRent System Menu
     */
    private void showMenu() {
        System.out.println("******** FLEXIRENT SYSTEM MENU ********");
        System.out.println("Add Property :         1");
        System.out.println("Rent Property :        2");
        System.out.println("Return Property :      3");
        System.out.println("Property Maintenance :      4");
        System.out.println("Complete Maintenance :      5");
        System.out.println("Display All Properties :    6");
        System.out.println("Exit Program :          7");
    }

    /**
     * get user's input from the key
     *
     * @return the number what the user want to input
     */
    private int getInputInteger() throws InterruptedException {
        int input = 0;
        try {
            Scanner sc = new Scanner(System.in);
            input = sc.nextInt();
        } catch (Exception e) {
            System.err.println("Error Input, please input number 1-7");
            Thread.sleep(100);
        }
        return input;
    }

    /**
     * get the user's input
     *
     * @return a String the user what to input
     */
    public String getInputString() {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        return input;
    }
}
