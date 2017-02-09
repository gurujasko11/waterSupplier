package model;

import javafx.beans.property.*;

public class InvoicePosition {
    IntegerProperty quantity;
    StringProperty name;
    DoubleProperty nettoPrice;
    DoubleProperty tax;//by default 23
//-----------------------------------------
//
//          constructors
//
//-----------------------------------------

    public InvoicePosition() {
        this.name = new SimpleStringProperty();
        this.nettoPrice = new SimpleDoubleProperty();
        this.quantity = new SimpleIntegerProperty();
        this.tax = new SimpleDoubleProperty(0.23);
    }

//-----------------------------------------
//
//          functions
//
//-----------------------------------------
    public Double getBruttoValue() {
        return getNettoValue()+getTaxValue();
    }

    public Double getNettoValue() {
        return getNettoPrice()*getQuantity();
    }

    public Double getTaxValue() {
        return getNettoValue()*getTax();
    }

//-----------------------------------------
//
//          getters and setters
//
//-----------------------------------------
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getNettoPrice() {
        return nettoPrice.get();
    }

    public DoubleProperty nettoPriceProperty() {
        return nettoPrice;
    }

    public void setNettoPrice(double nettoPrice) {
        this.nettoPrice.set(nettoPrice);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public double getTax() {
        return tax.get();
    }

    public DoubleProperty taxProperty() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax.set(tax);
    }
}
