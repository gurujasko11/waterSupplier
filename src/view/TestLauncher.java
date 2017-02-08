package view; /**
 * Created by busz on 08.02.17.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TestLauncher extends Application {

    Stage stage;
    AnchorPane layout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TestLauncher.class.getResource("/view/createInvoice.fxml"));
        layout = loader.load();
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        //CreateInvoiceController controller = loader.getController();



        primaryStage.show();

    }
}
