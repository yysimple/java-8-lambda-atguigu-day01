package cn.huntercat.lambda;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/17 20:49
 */
public class DataTest {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDate localDate = LocalDate.parse("20160305", dtf);
        //System.out.println(localDate);
        //test1();
        //test2();
        //test3();
        test4();
    }

    /**
     * LocalDate LocalTime LocalDateTime
     */
    public static void test1 () {
        // 获取本地时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        // 拼接时间
        LocalDateTime localDateTime1 = LocalDateTime.of(2015, 10, 25, 13, 52, 55);
        System.out.println(localDateTime);

        // 加两年
        LocalDateTime localDateTime2 = localDateTime.plusYears(2);
        System.out.println(localDateTime2);

        // 减两个月
        LocalDateTime localDateTime3 = localDateTime.minusMonths(3);
        System.out.println(localDateTime3);

        // 获取详细时间
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime.getMinute());
        System.out.println(localDateTime.getSecond());
    }

    /**
     * Instant: 时间戳 （Unix元年：1970年1月1日 到 某个时间的毫秒值）
     *
     */
    public static void test2() {
        Instant instant = Instant.now();
        System.out.println(instant);// 获取的是UTC时区

        OffsetDateTime odt = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        System.out.println(instant.toEpochMilli());
        Instant instant1 = Instant.ofEpochSecond(60);
        System.out.println(instant1);
    }

    /**
     * Duration：计算两个时间之间的间隔
     * period：计算两个日期之间的间隔
     */
    public static void test3() {
        Instant start = Instant.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());


        LocalDate localDate1 = LocalDate.of(2015, 12, 23);
        LocalDate localDate2 = LocalDate.now();

        Period period = Period.between(localDate1, localDate2);
        System.out.println(period);

        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());

    }

    public static void test4() {

    }
}
