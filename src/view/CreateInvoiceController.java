package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

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
    TableColumn<InvoicePosition, Integer> lpColumn;
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


}
