package top.jglo.hotel.util;

/**
 * Created by gjw on 2018/10/4.
 */
public class Random {
    public String beRandom() {
        String str = "";
        for (int i=0;i<4;i++) {
            int x = (int) (Math.random() * 10);
            str = str + x;
        }
        return str;
    }
}
