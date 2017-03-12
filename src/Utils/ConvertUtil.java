package Utils;

/**
 * Created by janusz on 21.02.17.
 */
public class ConvertUtil {
    private static String jednosci[] = {"", " jeden", " dwa", " trzy", " cztery", " pięć", " sześć", " siedem", " osiem", " dziewięć"};
    private static String nascie[] = {"dziesięć", " jedenaście", " dwanaście", " trzynaście", " czternaście", " piętnaście", " szesnaście", " siedemnaście", " osiemnaście", " dziewiętnaście"};
    private static String dziesiatki[] ={"", " dziesięć", " dwadzieścia", " trzydzieści", " czterdzieści", " pięćdziesiąt", " sześćdziesiąt", " siedemdziesiąt", " osiemdziesiąt", " dziewięćdziesiąt"};
    private static String setki[] = {"", " sto", " dwieście", " trzysta", " czterysta", " pięćset", " sześćset", " siedemset", " osiemset", " dziewięćset"};
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

    public static String NumToText(Double num) {
        return NumToText(num.intValue()) + " " + ((Double)((num*100)%100)).intValue() + "/100";
    }
}
