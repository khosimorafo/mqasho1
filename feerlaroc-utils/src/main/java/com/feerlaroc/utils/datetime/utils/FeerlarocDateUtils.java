package com.feerlaroc.utils.datetime.utils;

import android.content.Context;
import android.net.ParseException;
import android.text.TextUtils;
import android.text.format.DateUtils;

import com.feerlaroc.utils.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by root on 2016/03/08.
 */
public class FeerlarocDateUtils extends DateUtils {

    private static final SimpleDateFormat[] ACCEPTED_TIMESTAMP_FORMATS = {
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH),
            new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.ENGLISH),
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH),
            new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH),
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.ENGLISH),
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH),
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH),
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH),
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z", Locale.ENGLISH)
    };

    private static final SimpleDateFormat VALID_IFMODIFIEDSINCE_FORMAT =
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


    private static final FeerlarocDateUtils holder = new FeerlarocDateUtils();
    public static FeerlarocDateUtils getInstance() {return holder;}

    public static Date parseTimestamp(String timestamp) {

        for (SimpleDateFormat format : ACCEPTED_TIMESTAMP_FORMATS) {
            // TODO: We shouldn't be forcing the time zone when parsing dates.
            //format.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                return format.parse(timestamp);
            } catch (ParseException ex) {
                continue;
            } catch (java.text.ParseException e) {
                continue;
            }
        }

        // All attempts to parse have failed
        return null;
    }

    public static boolean isValidFormatForIfModifiedSinceHeader(String timestamp) {
        try {
            return VALID_IFMODIFIEDSINCE_FORMAT.parse(timestamp)!=null;
        } catch (Exception ex) {
            return false;
        }
    }

    public static String getSimpleDate(long timestamp) {
        try {

            Date d = new Date(timestamp);

            return SIMPLE_DATE_FORMAT.format(d);
        } catch (Exception ex) {

            return SIMPLE_DATE_FORMAT.format(new Date());
        }
    }

    public static long timestampToMillis(String timestamp, long defaultValue) {
        if (TextUtils.isEmpty(timestamp)) {
            return defaultValue;
        }
        Date d = parseTimestamp(timestamp);
        return d == null ? defaultValue : d.getTime();
    }


    /**
     *
     * Return current date if not valid
     */
    public static long parseLongDate(long timestamp, long defaultValue) {

        Date d = new Date(timestamp);
        return d == null ? defaultValue : d.getTime();

    }

    /**
     * Returns "Today", "Tomorrow", "Yesterday", or a short date format.
     */
    public static String formatHumanFriendlyShortDate(final Context context, long timestamp) {

        long now = getCurrentTime();

        long dayOrd = timestamp / 86400000L;
        long nowOrd = now / 86400000L;

        if (dayOrd == nowOrd) {
            return context.getString(R.string.day_title_today);
        } else if (dayOrd == nowOrd - 1) {
            return context.getString(R.string.day_title_yesterday);
        } else if (dayOrd == nowOrd + 1) {
            return context.getString(R.string.day_title_tomorrow);
        } else {
            return formatShortDate(context, new Date(timestamp));
        }
    }

    /**
     * Format a {@code date} honoring the app preference for using Conference or device timezone.
     * {@code Context} is used to lookup the shared preference settings.
     */
    public static String formatShortDate(Context context, Date date) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        return DateUtils.formatDateRange(context, formatter, date.getTime(), date.getTime(),
                DateUtils.FORMAT_ABBREV_MONTH | DateUtils.FORMAT_SHOW_YEAR).toString();
    }

    /**
     * Format a {@code date} honoring the app preference for using Conference or device timezone.
     * {@code Context} is used to lookup the shared preference settings.
     */
    public static String formatFullDate(Context context, long date) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        return DateUtils.formatDateTime(context, date,
                DateUtils.FORMAT_SHOW_YEAR).toString();
    }
    /**
     * Return the {@link TimeZone} the app is set to use (either user or conference).
     *
     * @param context Context to be used to lookup the {@link android.content.SharedPreferences}.
     */
    public static TimeZone getDisplayTimeZone(Context context) {
        return TimeZone.getDefault();
    }

    public static long getCurrentTime(){
        return System.currentTimeMillis();
    }



}
