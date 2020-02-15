package top.jglo.hotel.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static int getNowTimestamp(){
        Long time = System.currentTimeMillis()/1000;
        return time.intValue();
    }
    /**
     * 日期
     *
     * @return
     */
    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String datetime=f.format(date);
        System.out.println(datetime);
        return datetime;
    }
    /**
     * 星期
     *
     * @return
     */
    public static int getWeek() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String datetime=f.format(date);
        System.out.println(datetime);
//        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        int[] weekDays = { 7,1,2,3,4,5,6 };
        // 获得一个日历
        Calendar cal = Calendar.getInstance();
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 指示一个星期中的某天。
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }
    /**
     * 日期转星期
     *
     * @param datetime
     * @return
     */
    public static int dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        int[] weekDays = { 7,1,2,3,4,5,6 };
        // 获得一个日历
        Calendar cal = Calendar.getInstance();
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 指示一个星期中的某天。
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }
    public static int formatTimestamp(String time,String...format ){

        String formatString = "yyyy-MM-dd HH:mm:ss";
        if(format.length==1){
            formatString = format[0];
        }

        SimpleDateFormat df = new SimpleDateFormat(formatString);
        if("".equals(time)){
            return 0;
        }else{
            Date date = null;
            try {
                date = df.parse(time);
            } catch (ParseException e) {
            }
            return (int) (date.getTime()/1000);
        }
    }

    public static String formatTimestamp(int timestamp,String... format){
        //String... excludeProperty表示不定参数，也就是调用这个方法的时候这里可以传入多个String对象
        if(timestamp==0){
            return "0";
        }
        String formatString = "yyyy-MM-dd";
        if(format.length==1){
            formatString = format[0];
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        return sdf.format(new Date(timestamp*1000L));
    }


    public static String formatTimestampToDate(int timestamp,String... format){
        //String... excludeProperty表示不定参数，也就是调用这个方法的时候这里可以传入多个String对象
        if(timestamp==0){
            return "0";
        }
        String formatString = null;
        if(format.length==1){
            formatString = format[0];
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        return sdf.format(new Date(timestamp*1000L));
    }

    public static int formatTimestampToInt(String timestamp) throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        format.setLenient(false);
        //要转换字符串 str_test
        if(timestamp == null||timestamp.isEmpty()){
            return 0;
        }
        try {
            //System.out.println((format.parse(timestamp).getTime()/1000));
            return  (int) (format.parse(timestamp).getTime()/1000);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }
    //导入EXCEL时，针对yyyyMMdd-HH的时间格式，转换成时间戳
    public static int formatTimestampToInt2(String timestamp) throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        format.setLenient(false);
        //要转换字符串 str_test
        if(timestamp == null||timestamp.isEmpty()){
            return 0;
        }
        try {
            //System.out.println((format.parse(timestamp).getTime()/1000));
            return  (int) (format.parse(timestamp).getTime()/1000);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 12345678;
        }
    }
    public static int formatTimestampToIntfor_DispatchTime(String timestamp) throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setLenient(false);
        //要转换字符串 str_test
        if(timestamp == null||timestamp.isEmpty()){
            return 0;
        }
        try {
            //System.out.println((format.parse(timestamp).getTime()/1000));
            return  (int) (format.parse(timestamp).getTime()/1000);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }
    public static int formatDateToStamp(String date) throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        format.setLenient(false);
        //要转换字符串 str_test
        if(date == null||date.isEmpty()){
            return 0;
        }
        try {
            Date date1 = format.parse(date);
            return (int) (date1.getTime()/1000);
            /*return (int) (Fn.time()-format.parse(date).getTime()/1000);*/
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return 0;
        }
    }

    public static int formatDateToStamp2(String date) throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        //要转换字符串 str_test
        if(date == null||date.isEmpty()){
            return 0;
        }
        try {
            Date date1 = format.parse(date);
            return (int) (date1.getTime()/1000);
            /*return (int) (Fn.time()-format.parse(date).getTime()/1000);*/
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }


    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
/*    	   if (value.contains("."))
    		   return true;
*/    	   return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args) {
        System.out.println(DateUtil.getWeek());
        System.out.println(DateUtil.formatTimestamp(DateUtil.getNowTimestamp()+60*60*24));
        System.out.println(DateUtil.formatTimestamp(DateUtil.getNowTimestamp(),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtil.dateToWeek("2017-01-01"));
    }
}
