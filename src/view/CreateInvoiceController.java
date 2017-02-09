package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by busz on 08.02.17.
 */
public class CreateInvoiceController {

    @FXML
    Label ownerName;
    @FXML
    Label ownerAddress;
    @FXML
    Label ownerNip;

    Client client ;
    Invoice invoice;

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
        this.client = new Person(
                    new Adress("ul. Dostawy", 35,5,"31-313","Krakow"),
                    new Adress("ul. Zamieszkania", 36,1,"31-111","Krakow"),
                    "email@mail.com",
                    "501-502-500",
                    "TestoweImie",
                    "TestoweNazwisko"
                );
        this.invoice = new Invoice(this.client);
        showClient();
        showInvoiceDetails();
        paymentForm.setText("przelew");
        initPositionsTable();
    }

    public CreateInvoiceController(){
    }

    public void showOwner(){
        ownerName.setText("Asset International Sebastian Oleszczuk");
        ownerAddress.setText("ul. Plaszowska 31");
        ownerNip.setText("696-269-42-10");
    }

    public void showClient(){
        clientName.setText(client.getInvoiceName());
        clientAddress.setText(client.getMainAdress().toString());
        if(client instanceof Bussiness)
            clientNip.setText(((Bussiness) client).getNIP());
    }

    public void showInvoiceDetails(){
        issueDate.setText(invoice.getIssueDate().toString());
        saleDate.setText(invoice.getSaleDate().toString());
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
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit( t ->
                ((InvoicePosition) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue()));
        nettoPriceColumn.setCellValueFactory(cellData -> cellData.getValue().nettoPriceProperty().asObject());
        nettoPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        nettoPriceColumn.setOnEditCommit( t -> {
                    ((InvoicePosition) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNettoPrice(t.getNewValue().doubleValue());
                    t.getTableView().refresh();
                });
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityColumn.setOnEditCommit( t -> {
                    ((InvoicePosition) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuantity(t.getNewValue().intValue());
                    t.getTableView().refresh();
                });
        taxColumn.setCellValueFactory(cellData -> cellData.getValue().taxProperty().asObject());
        taxColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        taxColumn.setOnEditCommit(
                t -> {
                    ((InvoicePosition) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTax(t.getNewValue().doubleValue());
                    t.getTableView().refresh();
                });
        nettoValueColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getNettoValue()).asObject());
        taxValueColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTaxValue()).asObject());
        taxValueColumn.setCellFactory( cell ->
                new TableCell<InvoicePosition, Double>(){
                    DecimalFormat df = new DecimalFormat("#0.00", DecimalFormatSymbols.getInstance(Locale.US));

                    @Override
                    public void updateItem( Double item, boolean empty )
                    {
                        super.updateItem( item, empty );
                        setGraphic( null );
                        setText( empty ? null : df.format(item) );
                    }
                }
        );
        bruttoValueColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBruttoValue()).asObject());
        bruttoValueColumn.setCellFactory( cell ->
                new TableCell<InvoicePosition, Double>(){
                    DecimalFormat df = new DecimalFormat("#0.00", DecimalFormatSymbols.getInstance(Locale.US));

                    @Override
                    public void updateItem( Double item, boolean empty )
                    {
                        super.updateItem( item, empty );
                        setGraphic( null );
                        setText( empty ? null : df.format(item) );
                    }
                }
        );
    }


}
