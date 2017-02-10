package view;
/**
 * Created by janusz on 08.02.17.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Adress;
import model.Client;
import model.Person;
import model.testLoader;
import view.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class MainApp extends Application {
    private transient Stage primaryStage;
    private transient BorderPane rootLayout = new BorderPane();
    public ObservableList<Client> clients = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        testLoader.load(this);
//        Adress add = new Adress("jana",3,4,"pc","krakow");
//        clients.add(new Person(add,add,"daniel@slaby.com","2234","Daniel","Slaby"));
//        clients.add(new Person(add,add,"mocny@mariusz.com","4322","Mariusz","Mocny"));
        initRootLayout();
        showBasicView();
    }

    private void initRootLayout() {
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showBasicView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("basicView.fxml"));
            TabPane BasicLayout = (TabPane)loader.load();

            rootLayout.setCenter(BasicLayout);

            primaryStage.close();
            rootLayout.setPrefSize(BasicLayout.getPrefWidth(), BasicLayout.getPrefHeight());
            primaryStage.setTitle("Welcome");
            primaryStage.show();

            BasicViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public void showAddClient() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("addClient.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("dodaj klienta");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            addClientController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}