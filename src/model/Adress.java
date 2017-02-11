package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Adress implements Externalizable{
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

    public Adress(String street, Integer homeNumber, String postalCode, String city) {
        this.street = new SimpleStringProperty(street);
        this.homeNumber = new SimpleIntegerProperty(homeNumber);
        this.flatNumber = new SimpleIntegerProperty();
        this.postalCode = new SimpleStringProperty(postalCode);
        this.city = new SimpleStringProperty(city);
    }

    public Adress(String street, Integer homeNumber, Integer flatNumber, String postalCode, String city) {
        this(street, homeNumber, postalCode, city);
        this.flatNumber.setValue(flatNumber);
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
        if(this.flatNumberProperty().getValue() != null) {
            sb.append("/");
            sb.append(this.getFlatNumber());
        }
        sb.append(" ");
        sb.append(this.getPostalCode());
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(city.getValue());
        out.writeObject(postalCode.getValue());
        out.writeObject(street.getValue());
        out.writeObject(homeNumber.getValue());
        out.writeObject(flatNumber.getValue());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        city.setValue((String)in.readObject());
        postalCode.setValue((String)in.readObject());
        street.setValue((String)in.readObject());
        homeNumber.setValue((Integer)in.readObject());
        flatNumber.setValue((Integer)in.readObject());
    }
}
