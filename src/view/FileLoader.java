package view;

import Utils.AdressParser;
import model.Adress;
import model.Bussiness;
import model.Client;
import model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Created by janusz on 21.02.17.
 */
public class FileLoader {
    //FORMAT:
    //L.p.;NAZWA;ULICA;KOD POCZTOWY; NIP ;TELEFON;adres dostawy/uwagi;;
    //0     1       2       3          4      5             6
    public static Collection<Client> readFromFile(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        // InputStreamReader in = new InputStreamReader(fis, "UTF-8");
        Scanner scanner= new Scanner(fis, "UTF-8");
        ArrayList<Client> result = new ArrayList<>();
        while(scanner.hasNext()) {
            String phone = "";
            String tmp = scanner.nextLine();
            String[] input = tmp.split(";");
            if(input.length < 5) {
                System.out.println("brak danych dla rekordu nr:"+input[0]);
            }
            else if(input[4].isEmpty()) {
                //KLIENT
                System.out.println("klient" + input[1]);
                Adress adress = AdressParser.parse(input[2],input[3]);
                String firstName = null;
                String lastName = null;
                if(input[1].contains(" ")){
                    firstName = input[1].split(" ")[0];
                    if(firstName.length()+1 < input[1].length())
                        //niepytaj
                        lastName = input[1].split(" ")[1];
                    else
                        lastName = "";
                }
                else {
                    firstName = input[1];
                    lastName = "";
                }
                if(input.length > 5)
                    phone = input[5];
                result.add(new Person(adress,adress,"email",phone,firstName,lastName));
            }
            else {
                //FIRMA
                System.out.println("firma" + input[1]);
                System.out.println(AdressParser.parse(input[2],input[3]));
                Adress adress = AdressParser.parse(input[2],input[3]);
                if(input.length > 5)
                    phone = input[5];
                result.add(new Bussiness(adress,adress,"email",phone,input[1],input[1],input[4]));
            }
        }
        fis.close();
        scanner.close();
        return result;
    }
}
