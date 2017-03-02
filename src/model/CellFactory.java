package model;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

/**
 * Created by busz on 22.02.17.
 */
public class CellFactory {
    public static Callback getCell(){
        return new Callback<TableColumn<InvoicePosition, String>, TableCell<InvoicePosition,String>>() {
            public TableCell<InvoicePosition,String> call(TableColumn p) {
                return new EditingCell<String>();
            }
        };
    }

    public static <C> Callback getCell(StringConverter<C> sc){
        return new Callback<TableColumn<InvoicePosition, C>, TableCell<InvoicePosition,C>>() {
            public TableCell<InvoicePosition,C> call(TableColumn p) {
                return new EditingCell<C>(sc);
            }
        };
    }
}
