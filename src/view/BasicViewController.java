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

    Client currentClient;

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
        currentClient = null;
    }
    @FXML
    public void initialize(){
        personList.setCellFactory( list -> new ClientCell());
        businessList.setCellFactory(list -> new ClientCell());
        personList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    currentClient = newValue;
                    if(newValue == null)
                        showNull();
                    else
                        showPersonDetails((Person)newValue);
                }
        );
        businessList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    currentClient = newValue;
                    if(newValue == null)
                        showNull();
                    else
                        showBussinessDetails((Bussiness)newValue);
                }
        );
        showNull();
    }

    @FXML
    public void handleDelete() {
        if(currentClient != null) {
            mainApp.clients.remove(currentClient);
//            currentClient = null;
            refresh();
        }
    }
    @FXML
    public void handleEditCustomer() {
        Client tmp = currentClient;
        Client result = null;
        if(personList.getSelectionModel().getSelectedItems().isEmpty()
                &&
                businessList.getSelectionModel().getSelectedItems().isEmpty() )
            return;
        //przypadek kiedy nic nie jest wybrane
        mainApp.showAddClient(tmp);
        refresh();
        showClient(tmp);

    }

    private void showClient(Client client) {
        if(client == null)
            showNull();
        if(client instanceof Person)
            showPersonDetails((Person)client);
        if(client instanceof Bussiness)
            showBussinessDetails((Bussiness)client);
    }

    @FXML
    public void handleNewCustomer() {
        Client client = mainApp.showAddClient(null);
        if(client != null) mainApp.clients.add(client);
        refresh();
    }

    public void refresh() {
        personList.refresh();
        businessList.refresh();
    }
    private void showBussinessDetails(Bussiness newValue) {
        clientName.setText(newValue.getRegularName());
        clientSurename.setText(newValue.getNIP());
        clientAddress.setText(newValue.getMainAdress().toString());
        clientEmail.setText(newValue.getEmail());
        clientPhone.setText(newValue.getPhone());

        LabelClientName.setText("nazwa");
        LabelClientSurename.setText("nip");
    }

    private void showPersonDetails(Person newValue) {
        clientName.setText(newValue.getFirstName());
        clientSurename.setText(newValue.getLastName());
        clientAddress.setText(newValue.getMainAdress().toString());
        clientEmail.setText(newValue.getEmail());
        clientPhone.setText(newValue.getPhone());

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
