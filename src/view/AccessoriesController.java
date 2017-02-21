package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by janusz on 21.02.17.
 */
public class AccessoriesController {

    MainApp mainApp;
    Stage dialogStage;

    @FXML
    TextField loaderPath;

    @FXML
    private void handleLoadClients() {
        try {
            System.out.println(mainApp);
            mainApp.clients.addAll(FileLoader.readFromFile(loaderPath.getText()));
            System.out.println("Load successful");
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(){
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
