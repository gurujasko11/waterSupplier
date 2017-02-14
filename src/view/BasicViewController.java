package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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

    @FXML
    private AnchorPane clients;

    @FXML
    private ClientsController clientsController;

    @FXML
    private AnchorPane owner;

    @FXML
    private OwnerController ownerController;

    @FXML
    Tab ownerTab;

    @FXML
    public void initialize(){
    }

    public ClientsController getClientsController() {
        return clientsController;
    }

    public OwnerController getOwnerController() {
        return ownerController;
    }
}
