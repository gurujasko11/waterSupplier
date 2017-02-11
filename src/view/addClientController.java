package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

/**
 * Created by janusz on 10.02.17.
 */
public class addClientController {
    MainApp mainApp;
    Stage dialogStage;

    Client client;

    @FXML
    TextField firstName;

    @FXML
    TextField secondName;

    @FXML
    TextField optionalName;

    @FXML
    TextField email;

    @FXML
    TextField phone;

    @FXML
    TextField street;

    @FXML
    TextField houseNumber;

    @FXML
    TextField flatNumber;

    @FXML
    TextField zipCode;

    @FXML
    TextField city;

    @FXML
    Label firstNameLabel;

    @FXML
    Label secondNameLabel;

    @FXML
    Label optionalNameLabel;

    @FXML
    RadioButton personButton;

    @FXML
    RadioButton bussinessButton;

    @FXML
    public void initialize(){
        updateViewPerson();
    }

    @FXML
    private void updateViewBussiness() {
        personButton.setSelected(false);
        bussinessButton.setSelected(true);
        firstNameLabel.setText("Pelna nazwa");
        secondNameLabel.setText("Nip");
        optionalNameLabel.setText("nazwa(opcjonalna)");
        optionalName.setVisible(true);
    }

    @FXML
    private void updateViewPerson() {
        personButton.setSelected(true);
        bussinessButton.setSelected(false);
        firstNameLabel.setText("Imie");
        secondNameLabel.setText("Nazwisko");
        optionalNameLabel.setText("");
        optionalName.setVisible(false);
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
//        return null;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML
    private void handleConfirm() {
        if(checkData() == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uwaga");
            alert.setHeaderText("Wprowadzono za malo danych");
            alert.showAndWait();

        }
        else {
            if(personButton.isSelected())
                addNewPerson();
            else addNewBussiness();
            System.out.println(client);
            dialogStage.close();
        }
    }

    private void addNewPerson() {
        Adress add = null;
        if(flatNumber.getText().isEmpty()) {
            add = new Adress(
                    street.getText(),
                    Integer.parseInt(houseNumber.getText()),
                    zipCode.getText(),
                    city.getText());
        }
        else {
            add = new Adress(
                    street.getText(),
                    Integer.parseInt(houseNumber.getText()),
                    Integer.parseInt(flatNumber.getText()),
                    zipCode.getText(),
                    city.getText());
        }
        client =
                new Person(add,
                        add,
                        email.getText(),
                        phone.getText(),
                        firstName.getText(),
                        secondName.getText());
    }

    private void addNewBussiness() {

        Adress add = null;
        if(flatNumber.getText().isEmpty()) {
            add = new Adress(
                    street.getText(),
                    Integer.parseInt(houseNumber.getText()),
                    zipCode.getText(),
                    city.getText());
        }
        else {
            add = new Adress(
                    street.getText(),
                    Integer.parseInt(houseNumber.getText()),
                    Integer.parseInt(flatNumber.getText()),
                    zipCode.getText(),
                    city.getText());
        }
        client =
                new Bussiness(add,
                        add,
                        email.getText(),
                        phone.getText(),
                        optionalName.getText(),
                        firstName.getText(),
                        secondName.getText());
    }

    public boolean checkData() {
        if(firstName.getText().isEmpty()) return false;
        if(secondName.getText().isEmpty()) return false;
        if(bussinessButton.isPressed() && optionalName.getText().isEmpty()) return false;
        if(email.getText().isEmpty()) return false;
        if(phone.getText().isEmpty()) return false;
        if(street.getText().isEmpty()) return false;
        if(houseNumber.getText().isEmpty()) return false;
        if(zipCode.getText().isEmpty()) return false;
        if(city.getText().isEmpty()) return false;
        return true;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        if(client != null) { //tryb edycji
            if(client instanceof Person){
                updateViewPerson();
                firstName.setText(((Person)client).getFirstName());
                secondName.setText(((Person)client).getLastName());
            }
            else {
                updateViewBussiness();
                firstName.setText(((Bussiness)client).getFullName());
                secondName.setText(((Bussiness)client).getNIP());
                optionalName.setText(((Bussiness)client).getRegularName());
            }
            bussinessButton.setVisible(false);
            personButton.setVisible(false);

            email.setText(client.getEmail());
            phone.setText(client.getTelephone());
            street.setText(client.getMainAdress().getStreet());
            houseNumber.setText(client.getMainAdress().homeNumberProperty().getValue().toString());
            flatNumber.setText(client.getMainAdress().flatNumberProperty().getValue().toString());
            zipCode.setText(client.getMainAdress().getPostalCode());
            city.setText(client.getMainAdress().getCity());


        }
        this.client = null;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public addClientController(){}


}
