
public class propertyArray {
    int index = 0;
    //save all property
    public property[] propertiesArray;

    public propertyArray() {
        this.propertiesArray = new property[50];
    }

    public void addProperty(property a) {
        if (index < 50) {
            propertiesArray[index] = a;
            index++;
        } else {
            System.err.println("Our of Index : property Array");
        }
    }

    public int getSize() {
        return index;
    }

    /**
     * existed : false  don't existed :true
     * @param propertyID
     * @return
     */
    public boolean propertyIDisExisted(String propertyID) {
        boolean isExisted = true;
        for (int i = 0; i < index; i++) {
            if (propertiesArray[i].propertyID.equals(propertyID)) {
               isExisted=false;
            }
        }
        return isExisted;
    }
    public property getPropertyAccordingThePropertyID(String propertyID){
        property p=null;
        for (int i = 0; i <index ; i++) {
            if (propertiesArray[i].propertyID.equals(propertyID)){
                p=propertiesArray[i];
            }
        }
        return  p;
    }
}
