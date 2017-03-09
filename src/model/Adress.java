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
    StringProperty homeNumber;
    StringProperty flatNumber;
    StringProperty postalCode;
    StringProperty city;

//-----------------------------------------
//
//constructors
//
//-----------------------------------------

    public Adress() {
        this.street = new SimpleStringProperty();
        this.homeNumber = new SimpleStringProperty();
        this.flatNumber = new SimpleStringProperty();
        this.postalCode = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
    }

    public Adress(String street, String homeNumber, String postalCode, String city) {
        this.street = new SimpleStringProperty(street);
        this.homeNumber = new SimpleStringProperty(homeNumber);
        this.flatNumber = new SimpleStringProperty();
        this.postalCode = new SimpleStringProperty(postalCode);
        this.city = new SimpleStringProperty(city);
    }

    public Adress(String street, String homeNumber, String flatNumber, String postalCode, String city) {
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
        if(this.flatNumberProperty().getValue() != null && !this.flatNumberProperty().getValue().isEmpty()) {
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

    public String getHomeNumber() {
        return homeNumber.get();
    }

    public StringProperty homeNumberProperty() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber.set(homeNumber);
    }

    public String getFlatNumber() {
        return flatNumber.get();
    }

    public StringProperty flatNumberProperty() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
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
        homeNumber.setValue((String)in.readObject());
        flatNumber.setValue((String)in.readObject());
    }
}
