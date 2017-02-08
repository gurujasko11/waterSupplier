package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Owner {
    Adress adress;
    StringProperty name;
    StringProperty NIP;
    StringProperty bankName;
    StringProperty accountNumber;

    public Owner(Adress adress, StringProperty name, StringProperty NIP, StringProperty bankName, StringProperty accountNumber) {
        this.adress = adress;
        this.name = name;
        this.NIP = NIP;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    public Owner() {
        this.adress = new Adress();
        this.name = new SimpleStringProperty();
        this.NIP = new SimpleStringProperty();
        this.bankName = new SimpleStringProperty();
        this.accountNumber = new SimpleStringProperty();
    }
}
