package com.eComm.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtil {

    public static String parseDateUtc(String date, String sourceFormat, String targetFormat) {
        try {
            if (date != null && !date.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat(sourceFormat);
                sdf.setTimeZone(TimeZone.getDefault());
                Date strDate = sdf.parse(date);
                SimpleDateFormat sdf2 = new SimpleDateFormat(targetFormat.trim());
                sdf2.setTimeZone(TimeZone.getDefault());
                // return DateUtils.getRelativeTimeSpanString(strDate.getTime()).toString();
                return sdf2.format(strDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

/*
    public static String getTimeAgoTillOneDay(Context context, String timeString, String srcFormat, String targetformat) {
        Date date = null;
        String timeAgo = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(targetformat);
            Date startDate = formatter.parse(parseDateUtc(timeString, srcFormat, targetformat));
            long time = startDate.getTime();
            long diffInSeconds = getTimeDistanceInSeconds(time);
            long dim = diffInSeconds / 60;
            if (diffInSeconds == 0) {
                return context.getString(R.string.label_just_now);
            } else if (diffInSeconds > 0 && diffInSeconds <= 59) {
                timeAgo = diffInSeconds + "" + context.getResources().getString(R.string.date_util_unit_second);
            } else if (dim >= 1 && dim <= 59) {
                timeAgo = dim + " " + context.getResources().getString(R.string.date_util_unit_minute);
            } else if (dim >= 60 && dim <= 1439) {
                timeAgo = (Math.round(dim / 60)) + "" + context.getResources().getString(R.string.date_util_unit_hour);
            } else if (dim >= 1440 && dim <= 10079) {
                timeAgo = (Math.round(dim / 1440)) + "" + context.getResources().getString(R.string.date_util_unit_day);
            } else if (dim >= 10080 && dim <= 43799) {
                timeAgo = (Math.round(dim / 10080)) + "" + context.getResources().getString(R.string.date_util_unit_week);
            } else if (dim >= 43800 && dim <= 524159) {
                if ((Math.round(dim / 43800)) == 1)
                    timeAgo = (Math.round(dim / 43800)) + "" + context.getResources().getString(R.string.date_util_unit_month);
                else
                    timeAgo = (Math.round(dim / 43800)) + "" + context.getResources().getString(R.string.date_util_unit_months);
            } else {
                if ((Math.round(dim / 525599)) == 1)
                    timeAgo = (Math.round(dim / 525599)) + "" + context.getResources().getString(R.string.date_util_unit_year);
                else
                    timeAgo = (Math.round(dim / 525599)) + "" + context.getResources().getString(R.string.date_util_unit_years);
            }
        } catch (Exception e) {
            timeAgo = timeString;
            e.printStackTrace();
        }
        return timeAgo + " " + context.getString(R.string.ago);
    }
*/


    private static int getTimeDistanceInSeconds(long time) {
        long timeDistance = currentDate().getTime() - time;
        return Math.round((Math.abs(timeDistance) / 1000));
    }

    public static Date currentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        return calendar.getTime();
    }

    public static Date getUtcDateFromUTCMillis(long utcMillis) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ZZZZZ", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gmt = new Date(sdf.format(date));
        return gmt;

    }
}
