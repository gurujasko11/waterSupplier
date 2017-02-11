package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Bussiness extends Client {

    StringProperty fullName;
    StringProperty regularName;
    StringProperty NIP;

    @Override
    public String getInvoiceName() {
        return fullName.getValue();
    }

    public Bussiness() {
        super();
        fullName = new SimpleStringProperty();
        regularName = new SimpleStringProperty();
        NIP = new SimpleStringProperty();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(fullName.getValue());
        out.writeObject(regularName.getValue());
        out.writeObject(NIP.getValue());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        fullName.setValue((String)in.readObject());
        regularName.setValue((String)in.readObject());
        NIP.setValue((String)in.readObject());
    }

//-----------------------------------------
//
//constructors
//
//-----------------------------------------


    public Bussiness(Adress deliveryAdress, Adress mainAdress, String email, String telephone, String fullName, String regularName, String NIP) {
        super(deliveryAdress, mainAdress, email, telephone);
        this.fullName = new SimpleStringProperty(fullName);
        this.regularName = new SimpleStringProperty(regularName);
        this.NIP = new SimpleStringProperty(NIP);;
    }
    public Bussiness(Adress deliveryAdress, Adress mainAdress, String email, String telephone, String fullName, String regularName, String NIP, long ip) {
        super(deliveryAdress, mainAdress, email, telephone, ip);
        this.fullName = new SimpleStringProperty(fullName);
        this.regularName = new SimpleStringProperty(regularName);
        this.NIP = new SimpleStringProperty(NIP);;
    }

    //-----------------------------------------
//
//setters and getters
//
//-----------------------------------------
    public String getFullName() {
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getRegularName() {
        return regularName.get();
    }

    public StringProperty regularNameProperty() {
        return regularName;
    }

    public void setRegularName(String regularName) {
        this.regularName.set(regularName);
    }

    public String getNIP() {
        return NIP.get();
    }

    public StringProperty NIPProperty() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP.set(NIP);
    }

    public String getClientName() {
        return getRegularName();
    }
}
