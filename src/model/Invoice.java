package model;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Invoice {
    StringProperty ID;
    LocalDate issueDate;
    LocalDate saleDate;
    StringProperty issuePlace;
    Client client;
    ObservableList<InvoicePosition> positions;
//-----------------------------------------
//
//constructors
//
//-----------------------------------------
    public Invoice() {
        ID = new SimpleStringProperty();

        issueDate = LocalDate.now();
        saleDate = issueDate;
        issuePlace = new SimpleStringProperty();
        client = null;
        positions = FXCollections.observableArrayList(
                invoicePosition -> new Observable[] {
                    invoicePosition.nameProperty(),
                    invoicePosition.nettoPriceProperty(),
                    invoicePosition.taxProperty(),
                    invoicePosition.quantityProperty()
                }
        );
    }

    public Invoice(Client client) {
        this();
        this.client = client;
    }
 //-----------------------------------------
//
//functions
//
//-----------------------------------------
    public Double getBruttoTotal(){
        return this.positions.stream().mapToDouble(position -> position.getBruttoValue()).sum();
    }

    public Double getNettoTotal(){
        return this.positions.stream().mapToDouble(position -> position.getNettoValue()).sum();
    }

    public void addPosition(InvoicePosition position){
        this.positions.add(position);
    }
//-----------------------------------------
//
//setters and getters
//
//-----------------------------------------
    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getIssuePlace() {
        return issuePlace.get();
    }

    public StringProperty issuePlaceProperty() {
        return issuePlace;
    }

    public void setIssuePlace(String issuePlace) {
        this.issuePlace.set(issuePlace);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ObservableList<InvoicePosition> getPositions() {
        return positions;
    }

    public void setPositions(ObservableList<InvoicePosition> positions) {
        this.positions = positions;
    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }
}