package model;

import javafx.scene.control.ListCell;

public class ClientCell extends ListCell<Client> {

    @Override
    public void updateItem(Client item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(null);
        } else {
            setText(item.getClientName());
        }
        setGraphic(null);
    }
}
