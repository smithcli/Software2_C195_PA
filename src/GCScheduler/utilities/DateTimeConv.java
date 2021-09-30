package GCScheduler.utilities;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides Date Time conversions with formats specific to the application.
 */
public class DateTimeConv {
    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId LOCAL = ZoneId.systemDefault();
    private static final ZoneId BH = ZoneId.of("EST",ZoneId.SHORT_IDS);
    private static final DateTimeFormatter SCHEDULER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm");
    private static final Pattern TIMEPATTERN = Pattern.compile("^([0-1][0-9]|2[0-3]):?([0-5][0-9])$");

    /**
     * Creates a ZonedDateTime object in UTC from string with format yyyy-MM-dd HH:mm:ss
     * @param dateTime String to parse
     * @return UTC ZonedDateTime object
     */
    public static ZonedDateTime strToDateUTC(String dateTime) {
        return ZonedDateTime.parse(dateTime, SCHEDULER.withZone(UTC));
    }

    /**
     * Creates a ZonedDateTime object in Local time from string with format yyyy-MM-dd HH:mm:ss
     * @param dateTime String to parse
     * @return Local ZonedDateTime object
     */
    public static ZonedDateTime strToDateLocal(String dateTime) {
        return ZonedDateTime.parse(dateTime, SCHEDULER.withZone(LOCAL));
    }

    /**
     * Reads the ZonedDateTime object in UTC time with format yyyy-MM-dd HH:mm:ss
     * @param dateTime ZonedDateTime object
     * @return yyyy-MM-dd HH:mm:ss UTC
     */
    public static String dateToStrUTC(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(UTC).format(SCHEDULER);
    }

    /**
     * Reads the ZonedDateTime object in Local time with format yyyy-MM-dd HH:mm:ss
     * @param dateTime ZonedDateTime object
     * @return yyyy-MM-dd HH:mm:ss Local
     */
    public static String dateToStrLocal(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(LOCAL).format(SCHEDULER);
    }

    /**
     * Returns the dateTime in local time.
     * @param date DateTime to convert to local
     * @return Local ZonedDateTime
     */
    public static ZonedDateTime utcToLocal(ZonedDateTime date) {
        return date.withZoneSameInstant(LOCAL);
    }

    /**
     * Returns the dateTime in UTC time.
     * @param date DateTime to convert to UTC
     * @return UTC ZonedDateTime.
     */
    public static ZonedDateTime localToUTC(ZonedDateTime date) {
        return date.withZoneSameInstant(UTC);
    }

    /**
     * Gets the ISO Week of the Year
     * @param date ZonedDateTime object
     * @return Two digit week of year.
     */
    public static String getWeekOfYear(ZonedDateTime date) {
        String isoWeek = date.format(DateTimeFormatter.ISO_WEEK_DATE);
        return isoWeek.substring(6,8);
    }

    /**
     * Reads the date in the format of yyyy-MM-dd
     * @param date ZonedDateTime object
     * @return yyyy-MM-dd
     */
    public static String getDATEformat(ZonedDateTime date) {
        return date.format(DATE);
    }

    /**
     * Reads the time in the format of HH:mm
     * @param date ZonedDateTime object
     * @return HH:mm
     */
    public static String getTIMEformat(ZonedDateTime date) {
        return date.format(TIME);
    }

    /**
     * Determines if the time is within Business hours of 08:00 - 22:00 (EST -5:00)
     * @param date ZonedDateTime object
     * @return true if in Business hours.
     */
    public static boolean inBusinessHrs(ZonedDateTime date) {
        ZonedDateTime estDate = date.withZoneSameInstant(BH);
        ZonedDateTime open = estDate.withHour(7).withMinute(59);
        ZonedDateTime closed = estDate.withHour(22).withMinute(1);
        return estDate.isAfter(open) && estDate.isBefore(closed);
    }

    /**
     * Converts time to EST and prints it in format HH:mm
     * @param dateTime ZonedDateTime object
     * @return HH:mm (EST-5:00)
     */
    public static String timeToStrEST(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(BH).format(TIME);
    }

    /**
     * Gets the Pattern of the Regular expression.
     * @return Regex Pattern
     */
    public static Pattern getTimepattern() {
        return TIMEPATTERN;
    }

    /**
     * Determines if the time matches the regular expression.
     * @param time Time to check
     * @return true if matches.
     */
    public static boolean matchesTimePattern(String time) {
        Matcher test = TIMEPATTERN.matcher(time);
        return test.matches();
    }
}
