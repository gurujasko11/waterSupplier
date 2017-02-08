package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Client {

    Adress deliveryAdress;
    Adress mainAdress;
    StringProperty email;
    StringProperty telephone;

    abstract public String getInvoiceName();

//-----------------------------------------
//
//setters and getters
//
//-----------------------------------------

    public Client() {
        this.deliveryAdress = null;
        this.mainAdress = null;
        this.email = new SimpleStringProperty();
        this.telephone = new SimpleStringProperty();
    }

    public Client(Adress deliveryAdress, Adress mainAdress, String email, String telephone) {
        this.deliveryAdress = deliveryAdress;
        this.mainAdress = mainAdress;
        this.email = new SimpleStringProperty(email);
        this.telephone = new SimpleStringProperty(telephone);
    }

    //-----------------------------------------
//
//setters and getters
//
//-----------------------------------------
    public Adress getDeliveryAdress() {
        return deliveryAdress;
    }

    public void setDeliveryAdress(Adress deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

    public Adress getMainAdress() {
        return mainAdress;
    }

    public void setMainAdress(Adress mainAdress) {
        this.mainAdress = mainAdress;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getTelephone() {
        return telephone.get();
    }

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

}
