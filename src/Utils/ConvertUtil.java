package Utils;

/**
 * Created by janusz on 21.02.17.
 */
public class ConvertUtil {
    private static String jednosci[] = {"", " jeden", " dwa", " trzy", " cztery", " piec", " szesc", " siedem", " osiem", " dziewiec"};
    private static String nascie[] = {"dziesiec", " jedenascie", " dwanascie", " trzynascie", " czternascie", " pietnascie", " szesnascie", " siedemnascie", " osiemnascie", " dziewietnascie"};
    private static String dziesiatki[] ={"", " dziesiec", " dwadziescia", " trzydziesci", " czterdziesci", " piecdziesiat", " szescdziesiat", " siedemdziesiat", " osiemdziesiat", " dziewiecdziesiat"};
    private static String setki[] = {"", " sto", " dwiescie", " trzysta", " czterysta", " piecset", " szescset", " siedemset", " osiemset", " dziewiecset"};
    private static String x[] = {"", " tys.", " mln.", " mld.", " bln.", " bld."};
    public static String NumToText(int num) {
        String slownie = " ";
        int koncowka;
        int rzad = 0;
        int j = 0;

        if (num==0) slownie="zero";

        while (num>0)
        {
            koncowka=(num%10);
            num/=10;
            if ((j==0)&&(num%100!=0 || koncowka !=0)) slownie = x[rzad] + slownie;
            if ((j==0)&&(num%10!=1)) slownie = jednosci[koncowka] + slownie;
            if ((j==0)&&(num%10==1))
            {
                slownie = nascie[koncowka] + slownie;
                num/=10;
                j+=2;
                continue;
            }
            if (j==1) slownie = dziesiatki[koncowka] + slownie;
            if (j==2)
            {
                slownie = setki[koncowka] + slownie;
                j=-1;
                rzad++;
            }
            j++;
        }
        return slownie;
    }
}
