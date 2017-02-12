package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

/**
 * Created by janusz on 08.02.17.
 */
public class BasicViewController {

    MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public boolean tabControllFlag = true;
    //flaga jest dlatego, ze klikajac na tab wywolywana jest ta sama funkcja ktora wywoluje
    //sie przy wyjsciu z tego taba i trzeba odrozniac te sytuacje

    @FXML
    Tab clientsTab;

    @FXML
    private ClientsController clientsController;

    @FXML
    Tab ownerTab;

    @FXML
    public void initialize(){
    }


    @FXML
    public void handleClientsClicked() {
        if(clientsTab.isSelected()){
            System.out.println("KLAJENTS");
        }
    }

    @FXML
    public void handleOwnerClicked() {
        if(ownerTab.isSelected()){
            System.out.println("OŁNER");
        }
    }
}
