package model;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class EditingCell<T> extends TableCell<InvoicePosition, T> {

    StringConverter<T> sc = null;
    private TextField textField;

    public EditingCell() {
    }

    public EditingCell(StringConverter<T> sc) {
        this.sc = sc;
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        if(sc != null)
            setText(sc.toString( getItem() ));
        else
            setText((String) getItem() );
        setGraphic(null);
    }

    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
        getTableRow().focusedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t0, Boolean t1) -> {
            if(!t1)
                if(sc != null)
                    commitEdit(sc.fromString(textField.getText()));
                else
                    commitEdit((T)textField.getText());
        });
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                if (!arg2) {
                    if(sc != null)
                        commitEdit(sc.fromString(textField.getText()));
                    else
                        commitEdit((T)textField.getText());
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}