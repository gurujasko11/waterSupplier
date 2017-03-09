package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Generator;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by janusz on 21.02.17.
 */
public class AccessoriesController {

    MainApp mainApp;
    Stage dialogStage;

    @FXML
    TextField loaderPath;

    @FXML
    TextField saveInvoicePath;

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
    private void handleSaveInvoicePath() {
        Path path = Paths.get(saveInvoicePath.getText());
        if(new File(String.valueOf(path)).exists() == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uwaga");
            alert.setHeaderText("Podany katalog nie istnieje");
            alert.showAndWait();
        }
        else {
            Generator.DEST = path.toString();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gratulacje");
            alert.setHeaderText("Udalo sie zmienic katalog");
            alert.showAndWait();
        }
    }
    @FXML
    public void initialize(){
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
