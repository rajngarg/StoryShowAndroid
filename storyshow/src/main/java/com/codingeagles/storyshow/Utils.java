package com.codingeagles.storyshow;

import java.util.Date;

public class Utils {

    public static String getDurationBetweenDates(Date d1, Date d2) {

        long diff = d1.getTime() - d2.getTime();
        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        StringBuilder formattedDiff = new StringBuilder();
        if (days != 0) {
            if (Math.abs(days) == 1)
                return formattedDiff.append(Math.abs(days) + " day ago").toString();
            else
                return formattedDiff.append(Math.abs(days) + " days ago").toString();
        }
        if (hours != 0) {
            if (Math.abs(hours) == 1)
                return formattedDiff.append(Math.abs(hours) + " hour ago").toString();
            else
                return formattedDiff.append(Math.abs(hours) + " hours ago").toString();
        }
        if (minutes != 0) {
            if (Math.abs(minutes) == 1)
                return formattedDiff.append(Math.abs(minutes) + " minute ago").toString();
            else
                return formattedDiff.append(Math.abs(minutes) + " minutes ago").toString();
        }
        if (seconds != 0) {
            if (Math.abs(seconds) == 1)
                return formattedDiff.append(Math.abs(seconds) + " second ago").toString();
            else
                return formattedDiff.append(Math.abs(seconds) + " seconds ago").toString();
        }

        return "";
    }

}
