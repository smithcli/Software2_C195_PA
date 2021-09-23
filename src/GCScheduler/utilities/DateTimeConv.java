package GCScheduler.utilities;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConv {
    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId LOCAL = ZoneId.systemDefault();
//    private static final ZoneId businessHrs = ZoneId.of("EST");
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static ZonedDateTime strToDateUTC(String dateTime) {
        return ZonedDateTime.parse(dateTime, FORMAT.withZone(UTC));
    }

    public static ZonedDateTime strToDateLocal(String dateTime) {
        return ZonedDateTime.parse(dateTime, FORMAT.withZone(LOCAL));
    }

    public static String dateToStrUTC(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(UTC).format(FORMAT);
    }

    public static String dateToStrLocal(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(LOCAL).format(FORMAT);
    }

    public static ZonedDateTime utcToLocal(ZonedDateTime date) {
        return date.withZoneSameInstant(LOCAL);
    }

    public static ZonedDateTime localToUTC(ZonedDateTime date) {
        return date.withZoneSameInstant(UTC);
    }

    public static String getWeekOfYear(ZonedDateTime date) {
        String isoWeek = date.format(DateTimeFormatter.ISO_WEEK_DATE);
        return isoWeek.substring(6,8);
    }
}
