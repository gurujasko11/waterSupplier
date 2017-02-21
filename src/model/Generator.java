package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import Utils.DateUtil;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;


/**
 * Created by busz on 05.02.17.
 */
public class Generator{

    public static String  DEST = "/home/busz/Dokumenty/PdfTest/";

    public static void generate(Invoice invoice) throws IOException {

        FileOutputStream fos = new FileOutputStream(DEST + invoice.getClient().getInvoiceName() + "/" + invoice.getIssueDate() + ".pdf");
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Faktura Vat nr").setTextAlignment(TextAlignment.CENTER).setMargin(0).setBold().setFont(PdfFontFactory.createFont(FontConstants.COURIER)));
        document.add(new Paragraph("oryginał").setFontSize(6).setTextAlignment(TextAlignment.CENTER).setMargin(0).setBold().setFont(PdfFontFactory.createFont(FontConstants.COURIER)));
        document.add(new Paragraph("\n"));

        float datyWidths[] = {45 , 55};
        Table daty = new Table(datyWidths).setFont(PdfFontFactory.createFont(FontConstants.COURIER)).setFontSize(8).setPadding(0).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setWidthPercent(100).setBorder(Border.NO_BORDER);
        daty.addCell(newDefaultCell().add("Data wystawienia:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daty.addCell(newDefaultCell().add(DateUtil.format(invoice.getIssueDate())).setPadding(0).setBorder(Border.NO_BORDER));
        daty.addCell(newDefaultCell().add("Data sprzedaży:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daty.addCell(newDefaultCell().add(DateUtil.format(invoice.getSaleDate())));
        daty.addCell(newDefaultCell().add("Miejsce wystawienia:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daty.addCell(newDefaultCell().add(invoice.getIssuePlace()));

        float colWid[] = {5,45,40};
        Table logoIDaty = new Table(colWid).setBorder(Border.NO_BORDER);
        logoIDaty.setWidthPercent(100).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(PdfFontFactory.createFont(FontConstants.COURIER));
        logoIDaty.addCell(newDefaultCell());
        logoIDaty.addCell(newDefaultCell().add(new Image(ImageDataFactory.create("/home/busz/Dokumenty/PdfTest/logo.jpg"))));
        logoIDaty.addCell(newDefaultCell().add(daty).setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER));

        document.add(logoIDaty);
        document.add(new Paragraph("\n"));

        float snWidths[] = {13,89};
        Table sprzedawca = new Table (snWidths).setFontSize(8).setWidthPercent(100).setBorder(Border.NO_BORDER).setFont(PdfFontFactory.createFont(FontConstants.COURIER));
        sprzedawca.addHeaderCell(new Cell(1,2).add("Sprzedawca").setBold().setBorder(Border.NO_BORDER));
        sprzedawca.addCell(newDefaultCell().add("Nazwa:").setTextAlignment(TextAlignment.RIGHT).setBold());
        sprzedawca.addCell(newDefaultCell().add("Asset International Sebastian Oleszczuk"));
        sprzedawca.addCell(newDefaultCell().add("Adres:").setTextAlignment(TextAlignment.RIGHT).setBold());
        sprzedawca.addCell(newDefaultCell().add("ul. Plaszowska 31"));
        sprzedawca.addCell(newDefaultCell().setPadding(0));
        sprzedawca.addCell(newDefaultCell().add("30-713 Krakow"));
        sprzedawca.addCell(newDefaultCell().add("NIP:").setTextAlignment(TextAlignment.RIGHT).setBold());
        sprzedawca.addCell(newDefaultCell().add("679-269-42-10"));

        String[] address = invoice.getClient().getMainAdress().toString().split(" ");


        Table nabywca = new Table (snWidths).setFontSize(8).setWidthPercent(100).setBorder(Border.NO_BORDER).setFont(PdfFontFactory.createFont(FontConstants.COURIER));
        nabywca.addHeaderCell(new Cell(1,2).add("Nabywca").setBorder(Border.NO_BORDER).setBold());
        nabywca.addCell(newDefaultCell().add("Nazwa:").setTextAlignment(TextAlignment.RIGHT).setBold());
        nabywca.addCell(newDefaultCell().add(invoice.getClient().getInvoiceName()));
        nabywca.addCell(newDefaultCell().add("Adres:").setTextAlignment(TextAlignment.RIGHT).setBold());
        nabywca.addCell(newDefaultCell().add(address[0] + " " + address[1]));
        nabywca.addCell(newDefaultCell());
        nabywca.addCell(newDefaultCell().add(address[2] + " " + address[3]));
        nabywca.addCell(newDefaultCell().add("NIP:").setTextAlignment(TextAlignment.RIGHT).setBold());
        if(invoice.getClient() instanceof Bussiness)
            nabywca.addCell(newDefaultCell().add(((Bussiness)invoice.getClient()).getNIP()));

        Table sprzedawcaNabywca = new Table(2).setWidthPercent(100).setBorder(Border.NO_BORDER);
        sprzedawcaNabywca.addCell(newDefaultCell().add(sprzedawca));
        sprzedawcaNabywca.addCell(newDefaultCell().add(nabywca));

        document.add(sprzedawcaNabywca);

        float columnWidths[] = {5,35,10,10,10,10,10,10};
        Table pozycje = new Table(columnWidths);
        pozycje.setWidthPercent(100).setTextAlignment(TextAlignment.CENTER).setFontSize(8).setFont(PdfFontFactory.createFont(FontConstants.COURIER, PdfEncodings.UNICODE_BIG, true));
        pozycje.addHeaderCell(new Cell().add("Lp.").setBold());
        pozycje.addHeaderCell(new Cell().add("Nazwa towaru lub usługi").setBold());
        pozycje.addHeaderCell(new Cell().add("Ilość").setBold());
        pozycje.addHeaderCell(new Cell().add("Cena netto").setBold());
        pozycje.addHeaderCell(new Cell().add("Stawka VAT").setBold());
        pozycje.addHeaderCell(new Cell().add("Wartość netto").setBold());
        pozycje.addHeaderCell(new Cell().add("Wartość VAT").setBold());
        pozycje.addHeaderCell(new Cell().add("Wartość Brutto").setBold());

        int lp = 0;
        for (InvoicePosition pos : invoice.getPositions()) {
            addPosition(pozycje, ++lp, pos.getName(), pos.getQuantity(), pos.getNettoPrice(),pos.getTax());
        }

        pozycje.addCell(new Cell(1,8).setBorder(Border.NO_BORDER));
        pozycje.addCell(new Cell(1,4).setBorder(Border.NO_BORDER));

        invoice.taxesValueSum().stream().forEach( taxList ->
                pozycje.addCell(taxList.get(0).toString())
                        .addCell(taxList.get(1).toString())
                        .addCell((taxList.get(2)-taxList.get(1)) + "")
                        .addCell(taxList.get(2).toString())
        );

        pozycje.addCell(new Cell(1,4).setBorder(Border.NO_BORDER));
        pozycje.addCell(new Cell().add("Razem").setBold())
                .addCell(invoice.getNettoTotal().toString())
                .addCell((invoice.getBruttoTotal()-invoice.getNettoTotal()) + "")
                .addCell(invoice.getBruttoTotal().toString());

        document.add(new Paragraph("\n"));
        document.add(pozycje);

        float daneBankoweWidths[] = {20, 80};
        Table daneBankowe = new Table(daneBankoweWidths).setFont(PdfFontFactory.createFont(FontConstants.COURIER)).setFontSize(10).setWidthPercent(60).setBorder(Border.NO_BORDER);
        daneBankowe.addCell(newDefaultCell().add("Bank:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daneBankowe.addCell(newDefaultCell().add("ING BANK ŚLĄSKI"));
        daneBankowe.addCell(newDefaultCell().add("Nr konta:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daneBankowe.addCell(newDefaultCell().add("57 1050 1445 1000 0022 9748 5050"));

        document.add(new Paragraph("\n"));
        document.add(daneBankowe);

        Table daneLewe = new Table(2).setFontSize(8).setFont(PdfFontFactory.createFont(FontConstants.COURIER)).setWidthPercent(80).setPadding(0).setBorder(Border.NO_BORDER);
        daneLewe.addCell(newDefaultCell().add("Forma platności:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daneLewe.addCell(newDefaultCell().add(invoice.getPaymentForm()));
        daneLewe.addCell(newDefaultCell().add("Termin Platności:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daneLewe.addCell(newDefaultCell().add(invoice.getPaymentDate().toString()));

        Table danePrawe = new Table(2).setFontSize(8).setFont(PdfFontFactory.createFont(FontConstants.COURIER)).setWidthPercent(80).setHorizontalAlignment(HorizontalAlignment.RIGHT).setPadding(0).setBorder(Border.NO_BORDER);
        danePrawe.addCell(newDefaultCell().add("Razem:").setTextAlignment(TextAlignment.RIGHT).setBold());
        danePrawe.addCell(newDefaultCell().add(invoice.getBruttoTotal() + " zl"));
        danePrawe.addCell(newDefaultCell().add("Przedpłata/Zaplacono:").setTextAlignment(TextAlignment.RIGHT).setBold());
        danePrawe.addCell(newDefaultCell().add(invoice.getPrepayment() + " zl"));
        danePrawe.addCell(newDefaultCell().add("Do zapłaty:").setTextAlignment(TextAlignment.RIGHT).setBold());
        danePrawe.addCell(newDefaultCell().add((invoice.getBruttoTotal()-invoice.getPrepayment()) + " zl"));

        Table daneKoncowe = new Table(2).setWidthPercent(100).setBorder(Border.NO_BORDER);
        daneKoncowe.addCell(new Cell().add(daneLewe).setBorder(Border.NO_BORDER));
        daneKoncowe.addCell(new Cell().add(danePrawe).setBorder(Border.NO_BORDER));

        document.add(new Paragraph("\n").setFontSize(5));
        document.add(daneKoncowe);

        document.add(new Paragraph("Slownie:" + "osiemdzisiat osiem, 56/100\n").setFontSize(10).setFont(PdfFontFactory.createFont(FontConstants.COURIER)));

        Table podpisy = new Table(2).setVerticalAlignment(VerticalAlignment.BOTTOM).setWidthPercent(100).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setPadding(0);
        podpisy.addCell( newDefaultCell().add("...............................................................................").setFontSize(5));
        podpisy.addCell( newDefaultCell().add("...............................................................................").setFontSize(5));
        podpisy.addCell( newDefaultCell().add("osoba upoważniona do wystawienia").setFontSize(5).setFont(PdfFontFactory.createFont(FontConstants.COURIER)));
        podpisy.addCell( newDefaultCell().add("osoba upoważniona do odbioru").setFontSize(5).setFont(PdfFontFactory.createFont(FontConstants.COURIER)));
        document.add(new Paragraph("\n"));
        document.add(podpisy);

        document.close();

    }

    public static Cell newDefaultCell(){
        return new Cell().setPadding(0).setBorder(Border.NO_BORDER);
    }

    public static void addPosition(Table table, int lp, String nazwa, int ilosc, double cenaNetto, double vat){
        DecimalFormat df = new DecimalFormat("#.##");
        table.addCell(String.valueOf(lp));
        table.addCell(nazwa);
        table.addCell(String.valueOf(ilosc));
        table.addCell(String.valueOf(df.format(cenaNetto)));
        table.addCell(String.valueOf(df.format(vat)));
        table.addCell(String.valueOf(df.format(ilosc*cenaNetto)));
        table.addCell(String.valueOf(df.format(ilosc*cenaNetto*vat)));
        table.addCell(String.valueOf(df.format(ilosc*cenaNetto*(1+vat))));
    }


    public static void main(String[] args) throws IOException {

    }
}
