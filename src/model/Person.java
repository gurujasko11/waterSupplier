package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person extends Client {

    StringProperty firstName;
    StringProperty lastName;

    @Override
    public String getInvoiceName() {
        return firstName.getValue() + " " + lastName.getValue();
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
