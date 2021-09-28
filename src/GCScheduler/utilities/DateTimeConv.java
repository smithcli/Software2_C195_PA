package GCScheduler.utilities;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeConv {
    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId LOCAL = ZoneId.systemDefault();
    private static final ZoneId BH = ZoneId.of("EST",ZoneId.SHORT_IDS);
    private static final DateTimeFormatter SCHEDULER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm");
    private static final Pattern TIMEPATTERN = Pattern.compile("^([0-1][0-9]|2[0-3]):?([0-5][0-9])$");

    public static ZonedDateTime strToDateUTC(String dateTime) {
        return ZonedDateTime.parse(dateTime, SCHEDULER.withZone(UTC));
    }

    public static ZonedDateTime strToDateLocal(String dateTime) {
        return ZonedDateTime.parse(dateTime, SCHEDULER.withZone(LOCAL));
    }

    public static String dateToStrUTC(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(UTC).format(SCHEDULER);
    }

    public static String dateToStrLocal(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(LOCAL).format(SCHEDULER);
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

    public static String getDATEformat(ZonedDateTime date) {
        return date.format(DATE);
    }

    public static String getTIMEformat(ZonedDateTime date) {
        return date.format(TIME);
    }

    public static boolean inBusinessHrs(ZonedDateTime date) {
        ZonedDateTime estDate = date.withZoneSameInstant(BH);
        ZonedDateTime open = estDate.withHour(7).withMinute(59);
        ZonedDateTime closed = estDate.withHour(22).withMinute(1);
        return estDate.isAfter(open) && estDate.isBefore(closed);
    }

    public static String timeToStrEST(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(BH).format(TIME);
    }

    public static Pattern getTimepattern() {
        return TIMEPATTERN;
    }

    public static boolean matchesTimePattern(String time) {
        Matcher test = TIMEPATTERN.matcher(time);
        return test.matches();
    }
}
