package view;
/**
 * Created by janusz on 08.02.17.
 */

//import database.DataBase;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Client;
import model.testLoader;

import java.io.IOException;


public class MainApp extends Application {
    private MainApp mainApp = this;
    private transient Stage primaryStage;
    private transient BorderPane rootLayout = new BorderPane();
    public ObservableList<Client> clients = FXCollections.observableArrayList();
    public DataLoader dataLoader;

//    public DataBase db = null;


    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        this.primaryStage = primaryStage;
        dataLoader = new DataLoader(this);
        dataLoader.load();
//        dataLoader.loadOwnerData();
//        testLoader.load(this);
//        Adress add = new Adress("jana",3,4,"pc","krakow");
//        clients.add(new Person(add,add,"daniel@slaby.com","2234","Daniel","Slaby"));
//        clients.add(new Person(add,add,"mocny@mariusz.com","4322","Mariusz","Mocny"));
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                DataLoader dataLoader = new DataLoader(mainApp);
                try {
                    dataLoader.save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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


            BasicViewController controller = loader.getController();
            controller.setMainApp(this);
            controller.getClientsController().setMainApp(this);
            controller.getOwnerController().setMainApp(this);
            controller.getAccessoriesController().setMainApp(this);

            rootLayout.setCenter(BasicLayout);

            primaryStage.close();
            rootLayout.setPrefSize(BasicLayout.getPrefWidth(), BasicLayout.getPrefHeight());
            primaryStage.setTitle("Welcome");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showClientsView(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("clients.fxml"));

        BasicViewController controller = loader.getController();
        controller.setMainApp(this);
    }

    public void showOwnerView(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("owner.fxml"));

        BasicViewController controller = loader.getController();
        controller.setMainApp(this);
    }

    public void showAccessoriesView(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("accessories.fxml"));

        BasicViewController controller = loader.getController();
        controller.setMainApp(this);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public Client showAddClient(Client client) {
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
            controller.setClient(client);
            controller.setMainApp(this);

            dialogStage.showAndWait();
            return controller.getClient();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void showCreateInvoice(Client client) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("createInvoice.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("faktura");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            CreateInvoiceController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);
            controller.setClient(client);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
