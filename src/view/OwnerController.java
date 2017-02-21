package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import model.Owner;

import java.io.IOException;

/**
 * Created by busz on 11.02.17.
 */
public class OwnerController {
    MainApp mainApp;
    Stage dialogStage;

    @FXML
    TextField name;
    @FXML
    TextField NIP;
    @FXML
    TextField bankName;
    @FXML
    TextField accountNumber;

    @FXML
    TextField street;
    @FXML
    TextField homeNumber;
    @FXML
    TextField flatNumber;
    @FXML
    TextField postalCode;
    @FXML
    TextField city;

    @FXML
    public void initialize(){
        name.setText(Owner.getInstance().getName());
        NIP.setText(Owner.getInstance().getNIP());
        bankName.setText(Owner.getInstance().getBankName());
        accountNumber.setText(Owner.getInstance().getAccountNumber());

        street.setText(Owner.getInstance().getAdress().getStreet());
        homeNumber.setText(String.valueOf(Owner.getInstance().getAdress().getHomeNumber()));
        flatNumber.setText(String.valueOf(Owner.getInstance().getAdress().getFlatNumber()));
        postalCode.setText(Owner.getInstance().getAdress().getPostalCode());
        city.setText(Owner.getInstance().getAdress().getCity());

        name.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().setName(newValue);
            try {
                mainApp.dataLoader.saveOwnerData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        NIP.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().setNIP(newValue);
            try {
                mainApp.dataLoader.saveOwnerData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bankName.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().setBankName(newValue);
            try {
                mainApp.dataLoader.saveOwnerData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        accountNumber.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().setAccountNumber(newValue);
            try {
                mainApp.dataLoader.saveOwnerData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        street.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().getAdress().setStreet(newValue);
            try {
                mainApp.dataLoader.saveOwnerData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        homeNumber.textProperty().addListener((item, oldValue, newValue) -> { Owner.getInstance().getAdress().setHomeNumber(newValue);
            try {
                mainApp.dataLoader.saveOwnerData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        flatNumber.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().getAdress().setFlatNumber(newValue);
            try {
                mainApp.dataLoader.saveOwnerData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        postalCode.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().getAdress().setPostalCode(newValue);
            try {
                mainApp.dataLoader.saveOwnerData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        city.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().getAdress().setCity(newValue);
            try {
                mainApp.dataLoader.saveOwnerData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
