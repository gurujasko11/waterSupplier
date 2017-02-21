package Utils;

import model.Adress;

/**
 * Created by janusz on 21.02.17.
 */
public class AdressParser {
    public static Adress parse(String ulica, String kod) {
        String street, number, city, zip;
        if(ulica.contains(" ")){
            street = ulica.substring(0,ulica.lastIndexOf(" "));
            number = ulica.substring(ulica.lastIndexOf(" ")+1);
        }
        else {
            street = "TODO";
            number = "-1";
        }
        if(kod.contains(" ")) {
            city = kod.substring(kod.lastIndexOf(" ")+1);
            zip = kod.substring(0,kod.lastIndexOf(" "));
        }
        else {
            city = "TODO";
            zip = "TODO";
        }
        String flatNumber;
        String homeNumber;
        Adress result = null;
        if(number.contains("/")) {
            homeNumber = number.split("/")[0];
            flatNumber = number.split("/")[1];
            result = new Adress(street,homeNumber,flatNumber,zip,city);
        }
        else {
            homeNumber = number;
            result = new Adress(street,homeNumber,zip,city);
        }
        return result;
    }
}
