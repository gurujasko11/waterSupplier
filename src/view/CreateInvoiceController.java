package view;

import Utils.DateUtil;
import Utils.DecimalUtil;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.*;

import java.io.IOException;


/**
 * Created by busz on 08.02.17.
 */
public class CreateInvoiceController {
    
    MainApp mainApp;
    Stage dialogStage;
    Client client ;
    Invoice invoice;

    @FXML
    Label ownerName;
    @FXML
    Label ownerAddress;
    @FXML
    Label ownerNip;

    @FXML
    TextField invoiceNumber;
    @FXML
    TextField issueDate;
    @FXML
    TextField saleDate;
    @FXML
    TextField issuePlace;

    @FXML
    TextField clientName;
    @FXML
    TextField clientAddress;
    @FXML
    TextField clientNip;

    @FXML
    TableView<InvoicePosition> positionsTable;
    @FXML
    TableColumn lpColumn;
    @FXML
    TableColumn<InvoicePosition, String> nameColumn;
    @FXML
    TableColumn<InvoicePosition, Double> nettoPriceColumn;
    @FXML
    TableColumn<InvoicePosition, Double> taxColumn;
    @FXML
    TableColumn<InvoicePosition, Double> nettoValueColumn;
    @FXML
    TableColumn<InvoicePosition, Double> taxValueColumn;
    @FXML
    TableColumn<InvoicePosition, Double> bruttoValueColumn;
    @FXML
    TableColumn<InvoicePosition, Integer> quantityColumn;

    @FXML
    Label nettoTotal;
    @FXML
    Label bruttoTotal;

    @FXML
    SplitMenuButton paymentForm;

    @FXML
    DatePicker paymentDate;
    @FXML
    TextField totalSentence;

    @FXML
    Label total;
    @FXML
    TextField prepayment;
    @FXML
    TextField payment;

    @FXML
    public void initialize(){
        showOwner();
        paymentForm.setText("przelew");
        payment.setText("0.00");
    }

    public CreateInvoiceController(){
    }

    public void showOwner(){
        ownerName.setText("Asset International Sebastian Oleszczuk");
        ownerAddress.setText("ul. Plaszowska 31 30-713 Kraków");
        ownerNip.setText("696-269-42-10");
    }

    public void showClient(){
        clientName.setText(client.getInvoiceName());
        clientAddress.setText(client.getMainAdress().toString());
        if(client instanceof Bussiness)
            clientNip.setText(((Bussiness) client).getNIP());
    }

    public void showInvoiceDetails(){
        issueDate.setText(DateUtil.format(invoice.getIssueDate()));
        saleDate.setText(DateUtil.format(invoice.getSaleDate()));
        prepayment.setText("0");
        paymentDate.setValue(invoice.getIssueDate().plusDays(14));
    }

    public void selectTransferAsPaymentForm(){
        paymentForm.setText("przelew");
    }

    public void selectCashAsPaymentForm(){
        paymentForm.setText("gotówka");
    }

    public void handleAddPositon(){
        invoice.addPosition(new InvoicePosition());
        invoice.getPositions().stream().map(pos -> pos.getName()).forEach(System.out::println);
    }

    public void initPositionsTable(){
        positionsTable.setItems(invoice.getPositions());
        positionsTable.setEditable(true);
        lpColumn.setCellFactory( cell ->
                new TableCell(){
                    @Override
                        public void updateItem( Object item, boolean empty )
                        {
                            super.updateItem( item, empty );
                            setGraphic( null );
                            setText( empty ? null : getIndex() + 1 + "" );
                        }
                }
            );

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameColumn.setCellFactory(CellFactory.getCell());
        nameColumn.setOnEditCommit( t ->
                ((InvoicePosition) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue()));
        nettoPriceColumn.setCellValueFactory(cellData -> cellData.getValue().nettoPriceProperty().asObject());
        nettoPriceColumn.setCellFactory(CellFactory.getCell(new DoubleStringConverter()));
        nettoPriceColumn.setOnEditCommit( t -> {
                    ((InvoicePosition) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNettoPrice(t.getNewValue().doubleValue());
                    t.getTableView().refresh();
                });
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        quantityColumn.setCellFactory(CellFactory.getCell(new IntegerStringConverter()));
        quantityColumn.setOnEditCommit( t -> {
                    ((InvoicePosition) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuantity(t.getNewValue().intValue());
                    t.getTableView().refresh();
                });
        taxColumn.setCellValueFactory(cellData -> cellData.getValue().taxProperty().asObject());
        taxColumn.setCellFactory(CellFactory.getCell(new DoubleStringConverter()));
        taxColumn.setOnEditCommit(
                t -> {
                    ((InvoicePosition) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTax(t.getNewValue().doubleValue());
                    t.getTableView().refresh();
                });
        nettoValueColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getNettoValue()).asObject());
        taxValueColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTaxValue()).asObject());
        taxValueColumn.setCellFactory( cell ->
                new TableCell<InvoicePosition, Double>(){
                    @Override
                    public void updateItem( Double item, boolean empty )
                    {
                        super.updateItem( item, empty );
                        setGraphic( null );
                        setText( empty ? null : DecimalUtil.format(item) );
                    }
                }
        );
        bruttoValueColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBruttoValue()).asObject());
        bruttoValueColumn.setCellFactory( cell ->
                new TableCell<InvoicePosition, Double>(){
                    @Override
                    public void updateItem( Double item, boolean empty )
                    {
                        super.updateItem( item, empty );
                        setGraphic( null );
                        setText( empty ? null : DecimalUtil.format(item) );
                    }
                }
        );

        positionsTable.getItems().addListener(new ListChangeListener<InvoicePosition>() {
            @Override
            public void onChanged(Change<? extends InvoicePosition> c) {
                nettoTotal.setText(DecimalUtil.format(invoice.getNettoTotal()));
                bruttoTotal.setText(DecimalUtil.format(invoice.getBruttoTotal()));
                total.setText(DecimalUtil.format(invoice.getBruttoTotal()));
                payment.setText(DecimalUtil.format(invoice.getBruttoTotal()));
            }
        });
    }

    public void initPayment(){
        prepayment.textProperty().addListener(
                (item, oldValue,newValue) -> {
                    if(newValue.matches("[^0-9.]")){
                        prepayment.setText(oldValue);
                    } else if(!newValue.equals("")  && newValue.matches("[0-9]*.??[0-9]*")) {
                        payment.setText(DecimalUtil.format(invoice.getBruttoTotal() - Double.parseDouble(newValue)));
                    } else {
                        payment.setText(DecimalUtil.format(invoice.getBruttoTotal()));
                    }
                });
    }

    public boolean isValid() {
        if(!DateUtil.validDate(issueDate.getText())) return false;
        if(!DateUtil.validDate(saleDate.getText())) return false;
        if(issuePlace.getText().isEmpty()) return false;
        return true;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setClient(Client client) {
        this.client = client;
        this.invoice = new Invoice(client);
        showClient();
        showInvoiceDetails();
        initPositionsTable();
        initPayment();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void handleGenerete() throws IOException {
        if(isValid()){
            invoice.setID( invoiceNumber.getText() );
            invoice.setIssueDate(DateUtil.parse(issueDate.getText()));
            invoice.setSaleDate(DateUtil.parse(saleDate.getText()));
            invoice.setIssuePlace(issuePlace.getText());
            invoice.setPaymentForm(paymentForm.getText());
            invoice.setPaymentDate(paymentDate.getValue());
            invoice.setPrepayment(Double.parseDouble(prepayment.getText()));
            Generator.generate(invoice);
            dialogStage.close();
        }
    }
}



