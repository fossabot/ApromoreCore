package org.apromore.logfilter.criteria.impl;

import org.apromore.logfilter.criteria.model.*;
import org.deckfour.xes.extension.std.XTimeExtension;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XAttributeTimestamp;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XTrace;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;

public class LogFilterCriterionDurationRange extends AbstractLogFilterCriterion {
    public LogFilterCriterionDurationRange(Action action, Containment containment, Level level, String label, String attribute, Set<String> value) {
        super(action, containment, level, label, attribute, value);
    }

    @Override
    protected boolean matchesCriterion(XEvent event) {
        return false; // events have no duration
    }

    @Override
    public boolean matchesCriterion(XTrace trace) {
        long greaterThan = 0;
        long lesserThan = Long.MAX_VALUE;

        for(String v : value) {
            if(v.startsWith(">")) {
                int spaceIndex = v.indexOf(" ");
                String numberString = v.substring(1, spaceIndex);
                BigDecimal doubleValue = new BigDecimal(numberString);
                String unit = v.substring(spaceIndex + 1);
                BigDecimal unitValue = unitStringToBigDecimal(unit);
                BigDecimal gValue = doubleValue.multiply(unitValue);
                greaterThan = gValue.longValue();

            }
            if(v.startsWith("<")){

                int spaceIndex = v.indexOf(" ");
                String numberString = v.substring(1, spaceIndex);
                BigDecimal doubleValue = new BigDecimal(numberString);
                String unit = v.substring(spaceIndex + 1);
                BigDecimal unitValue = unitStringToBigDecimal(unit);
                BigDecimal lValue = doubleValue.multiply(unitValue);
                lesserThan = lValue.longValue();
            }
        }

        long s = epochMilliOf(zonedDateTimeOf(trace.get(0)));
        long e = epochMilliOf(zonedDateTimeOf(trace.get(trace.size()-1)));
        long dur = e - s;

        if(dur < greaterThan) return false;
        else if(dur > lesserThan) return false;
        else return true;

    }



    public long epochMilliOf(ZonedDateTime zonedDateTime){

        long s = zonedDateTime.toInstant().toEpochMilli();
        return s;
    }

    public ZonedDateTime zonedDateTimeOf(XEvent xEvent) {
        XAttribute da =
                xEvent.getAttributes().get(XTimeExtension.KEY_TIMESTAMP);
        Date d = ((XAttributeTimestamp) da).getValue();
        ZonedDateTime z =
                ZonedDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());
        return z;
    }


    private BigDecimal unitStringToBigDecimal(String s) {
        if(s.equals("Years")) return new BigDecimal("31536000000");
        if(s.equals("Months")) return new BigDecimal("2678400000");
        if(s.equals("Weeks")) return new BigDecimal("604800000");
        if(s.equals("Days")) return new BigDecimal("86400000");
        if(s.equals("Hours")) return new BigDecimal("3600000");
        if(s.equals("Minutes")) return new BigDecimal("60000");
        if(s.equals("Seconds")) return new BigDecimal("1000");
        return new BigDecimal(0);
    }


    public static String convertMilliseconds(long milliseconds) {
        DecimalFormat decimalFormat = new DecimalFormat("##############0.##");
        double seconds = milliseconds / 1000.0D;
        double minutes = seconds / 60.0D;
        double hours = minutes / 60.0D;
        double days = hours / 24.0D;
        double weeks = days / 7.0D;
        double months = days / 31.0D;
        double years = days / 365.0D;

        if (years > 1.0D) {
            return decimalFormat.format(years) + " yrs";
        }

        if (months > 1.0D) {
            return decimalFormat.format(months) + " mths";
        }

        if (weeks > 1.0D) {
            return decimalFormat.format(weeks) + " wks";
        }

        if (days > 1.0D) {
            return decimalFormat.format(days) + " d";
        }

        if (hours > 1.0D) {
            return decimalFormat.format(hours) + " hrs";
        }

        if (minutes > 1.0D) {
            return decimalFormat.format(minutes) + " mins";
        }

        if (seconds > 1.0D) {
            return decimalFormat.format(seconds) + " secs";
        }

        if (milliseconds > 1.0D) {
            return decimalFormat.format(milliseconds) + " millis";
        }

        return "instant";
    }
}
