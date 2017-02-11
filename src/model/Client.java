package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public abstract class Client implements Externalizable {

    Adress deliveryAdress;
    Adress mainAdress;
    StringProperty email;
    StringProperty phone;
    static long IP = 0;
    long client_ip;
  
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
        this.phone = new SimpleStringProperty();
        this.client_ip = IP;
        IP++;
    }

    public Client(Adress deliveryAdress, Adress mainAdress, String email, String phone) {
        this.deliveryAdress = deliveryAdress;
        this.mainAdress = mainAdress;
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.client_ip = IP;
        IP++;
    }

    public long getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(long client_ip) {
        this.client_ip = client_ip;
    }

    public Client(Adress deliveryAdress, Adress mainAdress, String email, String phone, long ip) {
        this.deliveryAdress = deliveryAdress;
        this.mainAdress = mainAdress;
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.client_ip = ip;
        if(ip >= IP)
            IP = ip + 1;

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

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public abstract String getClientName();

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getMainAdress());
        out.writeObject(this.getDeliveryAdress());
        out.writeObject(this.getEmail());
        out.writeObject(this.getPhone());
        out.writeObject(this.getClient_ip());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        mainAdress = (Adress) in.readObject();
        deliveryAdress = (Adress) in.readObject();
        email = new SimpleStringProperty((String) in.readObject());
        phone = new SimpleStringProperty((String) in.readObject());
        client_ip = (long) in.readObject();
        if(client_ip >= IP)
            IP = client_ip+1;
    }
}
