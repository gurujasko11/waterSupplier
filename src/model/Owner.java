package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public enum Owner{
    INSTANCE();

    Adress adress;
    StringProperty name;
    StringProperty NIP;
    StringProperty bankName;
    StringProperty accountNumber;

    Owner(){
        name = new SimpleStringProperty();
        NIP = new SimpleStringProperty();
        bankName = new SimpleStringProperty();
        accountNumber = new SimpleStringProperty();
    }

    public static Owner getInstance(){
        return Owner.INSTANCE;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getBankName() {
        return bankName.get();
    }

    public StringProperty bankNameProperty() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName.set(bankName);
    }

    public String getAccountNumber() {
        return accountNumber.get();
    }

    public StringProperty accountNumberProperty() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber.set(accountNumber);
    }
}