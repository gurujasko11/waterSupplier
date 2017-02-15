package view;

import model.Adress;
import model.Owner;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.Collection;

/**
 * Created by janusz on 11.02.17.
 */
public class DataLoader {
    public static MainApp mainApp;

    public DataLoader(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public <T extends Externalizable> void saveTypeToCollection(File file, Collection<T> collection) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (T item: collection) {
            oos.writeObject(item);
        }
        fos.close();
    }

    public <T extends Externalizable, Cloneable> void loadTypeFromCollection(File file, Collection<T> collection) throws IOException, ClassNotFoundException {
        collection.clear();
        if(file.exists()){
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);

            while(fos.available() > 0) {
//            T x =
//            x.readExternal(oos);
                collection.add((T)oos.readObject());
            }
            fos.close();
        }
    }

    public void save() throws IOException {
        saveTypeToCollection(new File("clients.dat"),mainApp.getClients());
    }

    public void saveOwnerData() throws IOException {
        File file = new File("owner.dat");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(Owner.getInstance().getAdress());
        oos.writeObject(Owner.getInstance().getName());
        oos.writeObject(Owner.getInstance().getNIP());
        oos.writeObject(Owner.getInstance().getBankName());
        oos.writeObject(Owner.getInstance().getAccountNumber());

        fos.close();
    }

    public void loadOwnerData() throws IOException, ClassNotFoundException {
        File file = new File("owner.dat");
        if(file.exists()){
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);

            Owner.getInstance().setAdress((Adress)oos.readObject());
            Owner.getInstance().setName((String)oos.readObject());
            Owner.getInstance().setNIP((String)oos.readObject());
            Owner.getInstance().setBankName((String)oos.readObject());
            Owner.getInstance().setAccountNumber((String)oos.readObject());
        }
    }
    public void load() throws IOException, ClassNotFoundException {
        File f = new File("clients.dat");
        loadTypeFromCollection(f,mainApp.getClients());
        loadOwnerData();
    }
}
