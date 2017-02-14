package view;

import model.Owner;

import java.io.*;
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
        FileInputStream fos = new FileInputStream(file);
        ObjectInputStream oos = new ObjectInputStream(fos);

        while(fos.available() > 0) {
//            T x =
//            x.readExternal(oos);
            collection.add((T)oos.readObject());
        }
        fos.close();
    }

    public void save() throws IOException {
        saveTypeToCollection(new File("clients.dat"),mainApp.getClients());
    }

    public void saveOwnerData() throws IOException {
        File file = new File("owner.dat");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(Owner.getInstance());
        fos.close();
    }

    public void loadOwnerData() throws IOException, ClassNotFoundException {
        File file = new File("owner.dat");
        FileInputStream fos = new FileInputStream(file);
        ObjectInputStream oos = new ObjectInputStream(fos);
        System.out.println(oos.readObject());
    }
    public void load() throws IOException, ClassNotFoundException {
        loadTypeFromCollection(new File("clients.dat"),mainApp.getClients());
    }
}
