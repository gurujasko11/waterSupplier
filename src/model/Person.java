package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Person extends Client {

    StringProperty firstName;
    StringProperty lastName;

    @Override
    public String getInvoiceName() {
        return firstName.getValue() + " " + lastName.getValue();
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(firstName.getValue());
        out.writeObject(lastName.getValue());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        firstName.setValue((String)in.readObject());
        lastName.setValue((String)in.readObject());
    }

//-----------------------------------------
//
//constructors
//
//-----------------------------------------


    public Person(){
        super();
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
    }

    public Person(Adress deliveryAdress, Adress mainAdress, String email, String telephone, String firstName, String lastName) {
        super(deliveryAdress, mainAdress, email, telephone);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }


    public Person(Adress deliveryAdress, Adress mainAdress, String email, String telephone, String firstName, String lastName, long ip) {
        super(deliveryAdress, mainAdress, email, telephone, ip);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }
//-----------------------------------------
//
//setters and getters
//
//-----------------------------------------
    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getClientName() {
        return getFirstName()+" "+getLastName();
    }

}
