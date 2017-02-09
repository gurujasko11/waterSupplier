package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Bussiness;
import model.ClientCell;
import model.Person;

/**
 * Created by janusz on 10.02.17.
 */
public class addClientController {
    MainApp mainApp;
    Stage dialogStage;

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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public addClientController(){}


}
