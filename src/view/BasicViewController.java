package view;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

/**
 * Created by janusz on 08.02.17.
 */
public class BasicViewController {
    MainApp mainApp;
    Stage dialogStage;

    @FXML
    Label ownerName;
    @FXML
    Label ownerAddress;
    @FXML
    Label ownerNip;
    @FXML
    Label ownerBank;
    @FXML
    Label ownerAccountNumber;
    @FXML
    Label clientName;
    @FXML
    Label clientSurename;
    @FXML
    Label LabelClientName;
    @FXML
    Label LabelClientSurename;
    @FXML
    Label clientAddress;
    @FXML
    Label clientPhone;
    @FXML
    Label clientEmail;

    @FXML
    ListView<Client> personList;

    @FXML
    ListView<Client> businessList;

    FilteredList<Client> persons;

    FilteredList<Client> bussinesses;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        persons = new FilteredList<Client>(mainApp.getClients(), client -> client instanceof Person);
        bussinesses = new FilteredList<Client>(mainApp.getClients(), client -> client instanceof Bussiness);
        System.out.println("gowno");
        personList.setItems(persons);
        businessList.setItems(bussinesses);
    }
    @FXML
    public void initialize(){
        personList.setCellFactory( list -> new ClientCell());
        businessList.setCellFactory(list -> new ClientCell());
        personList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showPersonDetails((Person)newValue);
                }
        );
        businessList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showBussinessDetails((Bussiness)newValue);
                }
        );
        showNull();
    }

    @FXML
    public void handleNewCustomer() {
        mainApp.showAddClient();
    }

    private void showBussinessDetails(Bussiness newValue) {
        clientName.setText(newValue.getRegularName());
        clientSurename.setText(newValue.getNIP());
        clientAddress.setText(newValue.getMainAdress().toString());
        clientEmail.setText(newValue.getEmail());
        clientPhone.setText(newValue.getTelephone());

        LabelClientName.setText("nazwa");
        LabelClientSurename.setText("nip");
    }

    private void showPersonDetails(Person newValue) {
        clientName.setText(newValue.getFirstName());
        clientSurename.setText(newValue.getLastName());
        clientAddress.setText(newValue.getMainAdress().toString());
        clientEmail.setText(newValue.getEmail());
        clientPhone.setText(newValue.getTelephone());

        LabelClientName.setText("imie");
        LabelClientSurename.setText("nazwisko");
    }

    private void showNull() {
        clientName.setText("");
        clientSurename.setText("");
        clientAddress.setText("");
        clientEmail.setText("");
        clientPhone.setText("");
    }

    public BasicViewController() {
    }


}
