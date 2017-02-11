package model;

import view.MainApp;

public class testLoader {
    public static void load(MainApp mainApp) {
        Adress add = new Adress("jana",3,4,"pc","krakow");
        Adress add2 = new Adress("pawla",4,1,"cp","warszawa");
        mainApp.clients.add(new Person(add,add,"daniel@slaby.com","2234","Daniel","Slaby"));
        mainApp.clients.add(new Person(add2,add2,"mocny@mariusz.com","4322","Mariusz","Mocny"));
        mainApp.clients.add(new Bussiness(add,add,"firma@a.pl","2231","Powazna Firma A","Firma A", "12345"));
        mainApp.clients.add(new Bussiness(add2,add2,"firma@b.pl","1322","Powazna Firma B","Firma b", "54321"));
    }
}
