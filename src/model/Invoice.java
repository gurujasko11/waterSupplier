package model;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Invoice {
    StringProperty ID;
    LocalDate issueDate;
    LocalDate saleDate;
    StringProperty issuePlace;
    Client client;
    ObservableList<InvoicePosition> positions;
    Double prepayment;
    LocalDate paymentDate;
    String paymentForm;
    public static Set<String> IDSet = new HashSet<>();
    public static Integer staticID = 1;
//-----------------------------------------
//
//constructors
//
//-----------------------------------------
    public Invoice() {
        ID = new SimpleStringProperty(getNextID().toString()+"/"+ LocalDate.now().getYear() + "/A");

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

    private List<Double> taxesDistinct(){
        return positions.stream().mapToDouble(position -> position.getTax()).distinct().boxed().sorted().collect(Collectors.toList());
    }

    private List<InvoicePosition> positionsOfTax( Double tax){
        return positions.stream().filter(position -> tax.equals(position.getTax())).collect(Collectors.toList());
    }

    private Double nettoTaxSum(Double tax){
         return positionsOfTax(tax).stream().mapToDouble(invPos -> invPos.getNettoPrice()).sum();
    }

    private Double bruttoTaxSum(Double tax){
        return positionsOfTax(tax).stream().mapToDouble(invPos -> invPos.getBruttoValue()).sum();
    }

    public List<List<Double>> taxesValueSum(){
        return taxesDistinct().stream().map( tax -> Arrays.asList(tax, nettoTaxSum(tax), bruttoTaxSum(tax)) ).collect(Collectors.toList());
    }


    public Integer getNextID() {
        List<Integer> set = IDSet.stream().mapToInt(i -> Integer.parseInt(i.split("/")[0])).sorted().boxed().collect(Collectors.toList());
        Integer last = 1;
        for(Iterator<Integer> it = set.listIterator(); it.hasNext();){
            if(!it.next().equals(last+1))
                return last+1;
            last++;
        }
        return last+1;
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

    public Double getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(Double prepayment) {
        this.prepayment = prepayment;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentForm() {
        return paymentForm;
    }

    public void setPaymentForm(String paymentForm) {
        this.paymentForm = paymentForm;
    }
}