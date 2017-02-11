package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import model.Owner;

/**
 * Created by busz on 11.02.17.
 */
public class OwnerController {

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

        name.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().setName(newValue); });
        NIP.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().setNIP(newValue); });
        bankName.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().setBankName(newValue); });
        accountNumber.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().setAccountNumber(newValue); });

        street.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().getAdress().setStreet(newValue); });
        homeNumber.textProperty().addListener((item, oldValue, newValue) -> { Owner.getInstance().getAdress().setHomeNumber(Integer.parseInt(newValue)); });
        flatNumber.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().getAdress().setFlatNumber(Integer.parseInt(newValue)); });
        postalCode.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().getAdress().setPostalCode(newValue); });
        city.textProperty().addListener((item,oldValue,newValue) -> { Owner.getInstance().getAdress().setCity(newValue); });
    }

}
