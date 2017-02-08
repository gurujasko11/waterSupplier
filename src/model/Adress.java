package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Adress {
    StringProperty street;
    IntegerProperty homeNumber;
    IntegerProperty flatNumber;
    StringProperty postalCode;
    StringProperty city;

//-----------------------------------------
//
//constructors
//
//-----------------------------------------

    public Adress() {
        this.street = new SimpleStringProperty();
        this.homeNumber = new SimpleIntegerProperty();
        this.flatNumber = new SimpleIntegerProperty();
        this.postalCode = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
    }
//-----------------------------------------
//
//functions
//
//-----------------------------------------

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getStreet());
        sb.append(" ");
        sb.append(this.getHomeNumber());
        sb.append(" ");
        if(this.flatNumberProperty().getValue() != null) {
            sb.append(this.getFlatNumber());
            sb.append(" ");
        }
        sb.append(postalCode);
        sb.append(" ");
        sb.append(this.getCity());
        return sb.toString();
    }

//-----------------------------------------
//
//getters and setters
//
//-----------------------------------------

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public int getHomeNumber() {
        return homeNumber.get();
    }

    public IntegerProperty homeNumberProperty() {
        return homeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber.set(homeNumber);
    }

    public int getFlatNumber() {
        return flatNumber.get();
    }

    public IntegerProperty flatNumberProperty() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber.set(flatNumber);
    }

    public String getPostalCode() {
        return postalCode.get();
    }

    public StringProperty postalCodeProperty() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

}
