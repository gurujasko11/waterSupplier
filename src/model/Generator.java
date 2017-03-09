package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import Utils.ConvertUtil;
import Utils.DateUtil;
import Utils.DecimalUtil;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.sun.jndi.cosnaming.IiopUrl;


/**
 * Created by busz on 05.02.17.
 */
public class Generator{

    public static String  DEST = "/home/busz/Dokumenty/PdfTest/";
    private static Owner owner = Owner.getInstance();

    public static void generate(Invoice invoice) throws IOException {
        PdfFont font = PdfFontFactory.createFont("fonts/NotCourierSans.otf",PdfEncodings.IDENTITY_H, true);
        
        File f = new File(DEST + "/" +invoice.getClient().getInvoiceName());
        f.mkdirs();
        FileOutputStream fos = new FileOutputStream(DEST + "/" + invoice.getClient().getInvoiceName()+ "/" + DateUtil.format(invoice.getIssueDate()) + "_" + invoice.getID().replaceAll("/",".") +".pdf");
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Faktura Vat nr " + invoice.getID()).setTextAlignment(TextAlignment.CENTER).setMargin(0).setBold().setFont(font));
        document.add(new Paragraph("oryginał").setFontSize(6).setTextAlignment(TextAlignment.CENTER).setMargin(0).setBold().setFont(font));
        document.add(new Paragraph("\n"));

        float datyWidths[] = {45 , 55};
        Table daty = new Table(datyWidths).setFont(font).setFontSize(8).setPadding(0).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setWidthPercent(100).setBorder(Border.NO_BORDER);
        daty.addCell(newDefaultCell().add("Data wystawienia:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daty.addCell(newDefaultCell().add(DateUtil.format(invoice.getIssueDate())).setPadding(0).setBorder(Border.NO_BORDER));
        daty.addCell(newDefaultCell().add("Data sprzedaży:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daty.addCell(newDefaultCell().add(DateUtil.format(invoice.getSaleDate())));
        daty.addCell(newDefaultCell().add("Miejsce wystawienia:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daty.addCell(newDefaultCell().add(invoice.getIssuePlace()));

        float colWid[] = {5,45,40};
        Table logoIDaty = new Table(colWid).setBorder(Border.NO_BORDER);
        logoIDaty.setWidthPercent(100).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font);
        logoIDaty.addCell(newDefaultCell());
        logoIDaty.addCell(newDefaultCell().add(new Image(ImageDataFactory.create("logo/logo.jpg"))));
        logoIDaty.addCell(newDefaultCell().add(daty).setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER));

        document.add(logoIDaty);
        document.add(new Paragraph("\n"));

        Adress oAddress = owner.getAdress();
        float snWidths[] = {13,89};
        Table sprzedawca = new Table (snWidths).setFontSize(8).setWidthPercent(100).setBorder(Border.NO_BORDER).setFont(font);
        sprzedawca.addHeaderCell(new Cell(1,2).add("Sprzedawca").setBold().setBorder(Border.NO_BORDER));
        sprzedawca.addCell(newDefaultCell().add("Nazwa:").setTextAlignment(TextAlignment.RIGHT).setBold());
        sprzedawca.addCell(newDefaultCell().add(owner.getName()));
        sprzedawca.addCell(newDefaultCell().add("Adres:").setTextAlignment(TextAlignment.RIGHT).setBold());
        sprzedawca.addCell(newDefaultCell().add(oAddress.toString().substring(0, oAddress.toString().lastIndexOf("-")-3)));
        sprzedawca.addCell(newDefaultCell().setPadding(0));
        sprzedawca.addCell(newDefaultCell().add(oAddress.getPostalCode() + " " + oAddress.getCity()));
        sprzedawca.addCell(newDefaultCell().add("NIP:").setTextAlignment(TextAlignment.RIGHT).setBold());
        sprzedawca.addCell(newDefaultCell().add(owner.getNIP()));

        Adress cAddress = invoice.getClient().getMainAdress();


        Table nabywca = new Table (snWidths).setFontSize(8).setWidthPercent(100).setBorder(Border.NO_BORDER).setFont(font);
        nabywca.addHeaderCell(new Cell(1,2).add("Nabywca").setBorder(Border.NO_BORDER).setBold());
        nabywca.addCell(newDefaultCell().add("Nazwa:").setTextAlignment(TextAlignment.RIGHT).setBold());
        nabywca.addCell(newDefaultCell().add(invoice.getClient().getInvoiceName()));
        nabywca.addCell(newDefaultCell().add("Adres:").setTextAlignment(TextAlignment.RIGHT).setBold());
        nabywca.addCell(newDefaultCell().add(cAddress.toString().substring(0, cAddress.toString().lastIndexOf("-")-3)));
        nabywca.addCell(newDefaultCell());
        nabywca.addCell(newDefaultCell().add(cAddress.getPostalCode() + " " + cAddress.getCity()));
        nabywca.addCell(newDefaultCell().add("NIP:").setTextAlignment(TextAlignment.RIGHT).setBold());
        if(invoice.getClient() instanceof Bussiness)
            nabywca.addCell(newDefaultCell().add(((Bussiness)invoice.getClient()).getNIP()));

        Table sprzedawcaNabywca = new Table(2).setWidthPercent(100).setBorder(Border.NO_BORDER);
        sprzedawcaNabywca.addCell(newDefaultCell().add(sprzedawca));
        sprzedawcaNabywca.addCell(newDefaultCell().add(nabywca));

        document.add(sprzedawcaNabywca);

        float columnWidths[] = {5,35,10,10,10,10,10,10};
        Table pozycje = new Table(columnWidths).setPadding(0);
        pozycje.setWidthPercent(100).setTextAlignment(TextAlignment.CENTER).setFontSize(8).setFont(font);
        pozycje.addHeaderCell(new Cell().add("Lp.").setBold().setPadding(0));
        pozycje.addHeaderCell(new Cell().add("Nazwa towaru lub usługi").setBold().setPadding(0));
        pozycje.addHeaderCell(new Cell().add("Ilość").setBold().setPadding(0));
        pozycje.addHeaderCell(new Cell().add("Cena netto").setBold().setPadding(0));
        pozycje.addHeaderCell(new Cell().add("Stawka VAT").setBold().setPadding(0));
        pozycje.addHeaderCell(new Cell().add("Wartość netto").setBold().setPadding(0));
        pozycje.addHeaderCell(new Cell().add("Wartość VAT").setBold().setPadding(0));
        pozycje.addHeaderCell(new Cell().add("Wartość Brutto").setBold().setPadding(0));

        int lp = 0;
        for (InvoicePosition pos : invoice.getPositions()) {
            addPosition(pozycje, ++lp, pos.getName(), pos.getQuantity(), pos.getNettoPrice(),pos.getTax());
        }

        pozycje.addCell(new Cell(1,8).setBorder(Border.NO_BORDER));

        invoice.taxesValueSum().stream().forEach( taxList ->
                pozycje.addCell(new Cell(1,4).setBorder(Border.NO_BORDER))
                        .addCell(DecimalUtil.format(taxList.get(0)))
                        .addCell(DecimalUtil.format(taxList.get(1)))
                        .addCell(DecimalUtil.format(taxList.get(2)-taxList.get(1)))
                        .addCell(DecimalUtil.format(taxList.get(2)))
        );

        pozycje.addCell(new Cell(1,4).setBorder(Border.NO_BORDER));
        pozycje.addCell(new Cell().add("Razem").setBold())
                .addCell(DecimalUtil.format(invoice.getNettoTotal()))
                .addCell(DecimalUtil.format(invoice.getBruttoTotal()-invoice.getNettoTotal()))
                .addCell(DecimalUtil.format(invoice.getBruttoTotal()));

        document.add(new Paragraph("\n"));
        document.add(pozycje);

        float daneBankoweWidths[] = {20, 80};
        Table daneBankowe = new Table(daneBankoweWidths).setFont(font).setFontSize(10).setWidthPercent(60).setBorder(Border.NO_BORDER);
        daneBankowe.addCell(newDefaultCell().add("Bank:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daneBankowe.addCell(newDefaultCell().add("ING BANK ŚLĄSKI"));
        daneBankowe.addCell(newDefaultCell().add("Nr konta:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daneBankowe.addCell(newDefaultCell().add("57 1050 1445 1000 0022 9748 5050"));

        document.add(new Paragraph("\n"));
        document.add(daneBankowe);

        Table daneLewe = new Table(2).setFontSize(8).setFont(font).setWidthPercent(80).setPadding(0).setBorder(Border.NO_BORDER);
        daneLewe.addCell(newDefaultCell().add("Forma platności:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daneLewe.addCell(newDefaultCell().add(invoice.getPaymentForm()));
        daneLewe.addCell(newDefaultCell().add("Termin Platności:").setTextAlignment(TextAlignment.RIGHT).setBold());
        daneLewe.addCell(newDefaultCell().add(DateUtil.format(invoice.getPaymentDate())));

        Table danePrawe = new Table(2).setFontSize(8).setFont(font).setWidthPercent(80).setHorizontalAlignment(HorizontalAlignment.RIGHT).setPadding(0).setBorder(Border.NO_BORDER);
        danePrawe.addCell(newDefaultCell().add("Razem:").setTextAlignment(TextAlignment.RIGHT).setBold());
        danePrawe.addCell(newDefaultCell().add(DecimalUtil.format(invoice.getBruttoTotal()) + " zł")).setTextAlignment(TextAlignment.RIGHT).setMarginRight(120);
        danePrawe.addCell(newDefaultCell().add("Przedpłata:").setTextAlignment(TextAlignment.RIGHT).setBold());
        danePrawe.addCell(newDefaultCell().add(DecimalUtil.format(invoice.getPrepayment()) + " zł")).setTextAlignment(TextAlignment.RIGHT).setMarginRight(120);
        danePrawe.addCell(newDefaultCell().add("Do zapłaty:").setTextAlignment(TextAlignment.RIGHT).setBold());
        danePrawe.addCell(newDefaultCell().add(DecimalUtil.format(invoice.getBruttoTotal()-invoice.getPrepayment()) + " zł")).setTextAlignment(TextAlignment.RIGHT).setMarginRight(120);

        Table daneKoncowe = new Table(2).setWidthPercent(100).setBorder(Border.NO_BORDER);
        daneKoncowe.addCell(new Cell().add(daneLewe).setBorder(Border.NO_BORDER));
        daneKoncowe.addCell(new Cell().add(danePrawe).setBorder(Border.NO_BORDER));

        document.add(new Paragraph("\n").setFontSize(5));
        document.add(daneKoncowe);

        document.add(new Paragraph("Słownie:" + ConvertUtil.NumToText(invoice.getBruttoTotal()) + "\n").setFontSize(10).setFont(font));

        Table podpisy = new Table(2).setVerticalAlignment(VerticalAlignment.BOTTOM).setWidthPercent(100).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setPadding(0);
        podpisy.addCell( newDefaultCell().add("...............................................................................").setFontSize(5));
        podpisy.addCell( newDefaultCell().add("...............................................................................").setFontSize(5));
        podpisy.addCell( newDefaultCell().add("osoba upoważniona do wystawienia").setFontSize(5).setFont(font));
        podpisy.addCell( newDefaultCell().add("osoba upoważniona do odbioru").setFontSize(5).setFont(font));
        document.add(new Paragraph("\n"));
        document.add(podpisy);

        document.close();

    }

    public static Cell newDefaultCell(){
        return new Cell().setPadding(0).setBorder(Border.NO_BORDER);
    }

    public static void addPosition(Table table, int lp, String nazwa, int ilosc, double cenaNetto, double vat){
        table.addCell(String.valueOf(lp));
        table.addCell(nazwa);
        table.addCell(String.valueOf(ilosc));
        table.addCell(String.valueOf(DecimalUtil.format(cenaNetto)));
        table.addCell(String.valueOf(DecimalUtil.format(vat)));
        table.addCell(String.valueOf(DecimalUtil.format(ilosc*cenaNetto)));
        table.addCell(String.valueOf(DecimalUtil.format(ilosc*cenaNetto*vat)));
        table.addCell(String.valueOf(DecimalUtil.format(ilosc*cenaNetto*(1+vat))));
    }


    public static void main(String[] args) throws IOException {

    }
}
