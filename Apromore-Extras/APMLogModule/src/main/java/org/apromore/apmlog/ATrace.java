package org.apromore.apmlog;

import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XTrace;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ATrace implements Serializable {

    private String caseId = "";
    public long caseIdDigit = 0;
    private int caseVariantId = 0;
    private long startTimeMilli = -1;
    private long endTimeMilli = -1;
    private long duration = 0;
    private boolean hasActivity = false;
    private long totalProcessingTime = 0;
    private long averageProcessingTime = 0;
    private long maxProcessingTime = 0;
    private long totalWaitingTime = 0;
    private long averageWaitingTime = 0;
    private long maxWaitingTime = 0;
    private double caseUtilization = 0.0;

    public String startTimeString, endTimeString, durationString;

    private List<AActivity> activityList;
    private List<AEvent> eventList;
    private UnifiedMap<String, UnifiedMap<String, Integer>> eventAttributeValueFreqMap; // 2019-10-24
    private UnifiedMap<String, String> attributeMap;
    private List<String> activityNameList;
    private UnifiedSet<String> eventNameSet;

    public ATrace(XTrace xTrace) {

        activityList = new ArrayList<>();
        eventList = new ArrayList<>();
        eventAttributeValueFreqMap = new UnifiedMap<>();
        attributeMap = new UnifiedMap<>();

        XAttributeMap xAttributeMap = xTrace.getAttributes();
        for(String key : xAttributeMap.keySet()) {
            if(key.toLowerCase().equals("concept:name")) {
                this.caseId = xAttributeMap.get(key).toString();
                if(this.caseId.matches("-?\\d+(\\.\\d+)?")) this.caseIdDigit = new Long(caseId);
            } else {
                this.attributeMap.put(key, xAttributeMap.get(key).toString());
            }
        }
        /**
         * DO NOT TAKE THE CASE:VARIANT IN THE ORIGINAL XLOG
         */
//        if(xTrace.getAttributes().containsKey("case:variant")) caseVariantId = new Integer(xTrace.getAttributes().get("case:variant").toString());
        // ELSE SET THE VARIANT ID from APMLog
        initStats(xTrace);
    }



    private void initStats(XTrace xTrace) {



        activityNameList = new ArrayList<>();
        eventNameSet = new UnifiedSet<>();

        // initiate the bitSet with fixed size
//        validEventIndex = new BitSet(xTrace.size());

        if(containsActivity(xTrace)) {
            this.hasActivity = true;
            long waitCount = 0;
            long processCount = 0;

            UnifiedSet<XEvent> markedXEvent = new UnifiedSet<>();

            for(int i=0; i<xTrace.size(); i++) {
                XEvent xEvent = xTrace.get(i);
                AEvent iAEvent = new AEvent(xEvent);

//                validEventIndex.set(i, true);

                long eventTime = iAEvent.getTimestampMilli();
                if(startTimeMilli == -1 || startTimeMilli > eventTime) {
                    startTimeMilli = eventTime;
                }
                if(endTimeMilli == -1 || endTimeMilli < eventTime) {
                    endTimeMilli = eventTime;
                }


                this.eventList.add(iAEvent);

                this.eventNameSet.put(iAEvent.getName());

                fillEventAttributeValueFreqMap(iAEvent);



                if(iAEvent.getLifecycle().equals("start")) {

                    markedXEvent.put(xEvent);

                    String startEventName = iAEvent.getName();

                    /**
                     * Find the waiting time
                     */
                    long iTime = iAEvent.getTimestampMilli();

                    if(i > 0) {
                        for(int j=(i-1); j >=0; j--) {
                            XEvent preEvent = xTrace.get(j);
                            AEvent preAEvent = new AEvent(preEvent);
                            if(preAEvent.getLifecycle().equals("complete")) {
                                long preTime = preAEvent.getTimestampMilli();
                                if(iTime > preTime) {
                                    long waitingTime = iTime - preTime;
                                    this.totalWaitingTime += waitingTime;
                                    waitCount += 1;
                                    if(waitingTime > this.maxWaitingTime) {
                                        this.maxWaitingTime = waitingTime;
                                    }
                                }
                            }
                        }
                    }

                    boolean hasComplete = false;

                    /**
                     * Find the duration
                     */
                    if((i+1) <= (xTrace.size()-1)) {
                        for(int j=(i+1); j < xTrace.size(); j++) {
                            AEvent jAEvent = new AEvent(xTrace.get(j));
                            if(jAEvent.getName().equals(startEventName) &&
                                    jAEvent.getLifecycle().equals("complete")) {

                                long endTime = jAEvent.getTimestampMilli();
                                if(endTime > iTime) {
                                    long processTime = endTime - iTime;
                                    this.totalProcessingTime += processTime;
                                    if(processTime > this.maxProcessingTime) this.maxProcessingTime = processTime;
                                    processCount += 1;

                                    List<AEvent> aEventList = new ArrayList<>();
                                    aEventList.add(iAEvent);
                                    aEventList.add(jAEvent);
                                    AActivity aActivity = new AActivity(aEventList);
                                    this.activityList.add(aActivity);
                                    this.activityNameList.add(aActivity.getName());
                                    markedXEvent.put(xTrace.get(j));
                                    hasComplete = true;
                                }

                                break;
                            }
                        }
                    }

//                    if(!hasComplete) { //consider as instant event
//                        List<AEvent> aEventList = new ArrayList<>();
//                        aEventList.add(iAEvent);
//                        AActivity aActivity = new AActivity(aEventList);
//                        this.activityList.add(aActivity);
//                        this.activityNameList.add(aActivity.getName());
//                    }
                }



                if( !markedXEvent.contains(xEvent) && iAEvent.getLifecycle().toLowerCase().equals("complete")) {
                    List<AEvent> aEventList = new ArrayList<>();
                    aEventList.add(iAEvent);
                    AActivity aActivity = new AActivity(aEventList);
                    this.activityList.add(aActivity);
                    this.activityNameList.add(aActivity.getName());
                }
            }
            if(this.totalProcessingTime > 0 && processCount > 0) this.averageProcessingTime = this.totalProcessingTime / processCount;
            if(this.totalWaitingTime > 0 && waitCount > 0) this.averageWaitingTime = this.totalWaitingTime / waitCount;
        }else{
            for(int i=0; i<xTrace.size(); i++) {
                XEvent xEvent = xTrace.get(i);
                AEvent iAEvent = new AEvent(xEvent);


//                validEventIndex.set(i, true);
                if(iAEvent.getLifecycle().toLowerCase().equals("complete")) {

                    long eventTime = iAEvent.getTimestampMilli();
                    if (startTimeMilli == -1 || startTimeMilli > eventTime) startTimeMilli = eventTime;
                    if (endTimeMilli == -1 || endTimeMilli < eventTime) endTimeMilli = eventTime;

                    this.eventList.add(iAEvent);
                    this.eventNameSet.put(iAEvent.getName());

                    fillEventAttributeValueFreqMap(iAEvent);

                    List<AEvent> aEventList = new ArrayList<>();
                    aEventList.add(iAEvent);
                    AActivity aActivity = new AActivity(aEventList);
                    this.activityList.add(aActivity);
                    this.activityNameList.add(aActivity.getName());
                }
            }
        }


        if(endTimeMilli > startTimeMilli) {
            this.duration = endTimeMilli - startTimeMilli;
            if(containsActivity(xTrace)) {
                this.caseUtilization = (double) this.totalProcessingTime / this.duration;
            }else{
                this.caseUtilization = 1.0;
            }
        }

        this.startTimeString = timestampStringOf(millisecondToZonedDateTime(startTimeMilli));
        this.endTimeString = timestampStringOf(millisecondToZonedDateTime(endTimeMilli));
        this.durationString = convertMilliseconds(duration);
    }

    private void fillEventAttributeValueFreqMap(AEvent aEvent) {
        for(String key : aEvent.getAttributeMap().keySet()) {
            String iAValue = aEvent.getAttributeMap().get(key);
            if (this.eventAttributeValueFreqMap.containsKey(key)) {
                UnifiedMap<String, Integer> valueFreqMap = this.eventAttributeValueFreqMap.get(key);
                if(valueFreqMap.containsKey(iAValue)) {
                    int freq = valueFreqMap.get(iAValue) + 1;
                    valueFreqMap.put(iAValue, freq);
                    this.eventAttributeValueFreqMap.put(key, valueFreqMap);
                }else{
                    valueFreqMap.put(iAValue, 1);
                    this.eventAttributeValueFreqMap.put(key, valueFreqMap);
                }
            }else{
                UnifiedMap<String, Integer> valueFreqMap = new UnifiedMap<>();
                valueFreqMap.put(iAValue, 1);
                this.eventAttributeValueFreqMap.put(key, valueFreqMap);
            }
        }
    }

    public UnifiedMap<String, UnifiedMap<String, Integer>> getEventAttributeValueFreqMap() {
        return eventAttributeValueFreqMap;
    }

    private boolean containsActivity(XTrace xTrace) {
        for(int i=0; i<xTrace.size(); i++) {
            AEvent iEvent = new AEvent(xTrace.get(i));
            if(iEvent.getLifecycle().equals("start")) {
                this.hasActivity = true;
                return true;
            }
        }
        return false;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseVariantId(int caseVariantId) {
        this.caseVariantId = caseVariantId;
    }

    public int getCaseVariantId() {
        return caseVariantId;
    }

    public int getEventSize() {
        return this.eventList.size();
    }

    public long getStartTimeMilli() {
        return startTimeMilli;
    }

    public long getEndTimeMilli() {
        return endTimeMilli;
    }

    public long getDuration() {
        return duration;
    }

    public boolean isHasActivity() {
        return hasActivity;
    }

    public List<AActivity> getActivityList() {
        return activityList;
    }

    public List<String> getActivityNameList() {
        List<String> actNameList = new ArrayList<>();
        for(int i=0; i < this.activityList.size(); i++) {
            actNameList.add(this.activityList.get(i).getName());
        }
        return actNameList;
    }

    public UnifiedSet<String> getEventNameSet() {
        return this.eventNameSet;
    }

    public UnifiedMap<String, String> getAttributeMap() {
        return attributeMap;
    }

    public List<AEvent> getEventList() {
        return eventList;
    }

    public int size() {
        return this.eventList.size();
    }

    public AEvent get(int index) {
        return this.eventList.get(index);
    }

    public long getTotalProcessingTime() {
        return totalProcessingTime;
    }

    public long getAverageProcessingTime() {
        return averageProcessingTime;
    }

    public long getMaxProcessingTime() {
        return maxProcessingTime;
    }

    public long getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public long getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public long getMaxWaitingTime() {
        return maxWaitingTime;
    }

    public double getCaseUtilization() {
        return caseUtilization;
    }

    public String getStartTimeString() {
        return startTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public String getDurationString() {
        return durationString;
    }

    public long getCaseIdDigit() {
        return caseIdDigit;
    }

    public ATrace(String caseId, int caseVariantId,
                  long startTimeMilli, long endTimeMilli,
                  boolean hasActivity, long duration,
                  long totalProcessingTime, long averageProcessingTime, long maxProcessingTime,
                  long totalWaitingTime, long averageWaitingTime, long maxWaitingTime,
                  double caseUtilization,
                  List<AActivity> activityList,
                  List<AEvent> eventList,
                  UnifiedMap<String, UnifiedMap<String, Integer>> eventAttributeValueFreqMap,
                  UnifiedMap<String, String> attributeMap,
                  List<String> activityNameList,
                  UnifiedSet<String> eventNameSet) {
        this.caseId = caseId;
        if(this.caseId.matches("-?\\d+(\\.\\d+)?")) this.caseIdDigit = new Long(caseId);
        this.caseVariantId = caseVariantId;
        this.startTimeMilli = startTimeMilli;
        this.endTimeMilli = endTimeMilli;
        this.hasActivity = hasActivity;
        this.duration = duration;
        this.totalProcessingTime = totalProcessingTime;
        this.averageProcessingTime = averageProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.totalWaitingTime = totalWaitingTime;
        this.averageWaitingTime = averageWaitingTime;
        this.maxWaitingTime = maxWaitingTime;
        this.caseUtilization = caseUtilization;
        this.activityList = activityList;
        this.eventList = eventList;
        this.eventAttributeValueFreqMap = eventAttributeValueFreqMap;
        this.attributeMap = attributeMap;
        this.activityNameList = activityNameList;
        this.eventNameSet = eventNameSet;

        this.startTimeString = timestampStringOf(millisecondToZonedDateTime(startTimeMilli));
        this.endTimeString = timestampStringOf(millisecondToZonedDateTime(endTimeMilli));
        this.durationString = convertMilliseconds(duration);
    }

    public static String timestampStringOf(ZonedDateTime zdt){
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS");
        return zdt.format(formatter);
    }

    public static ZonedDateTime millisecondToZonedDateTime(long millisecond){
        Instant i = Instant.ofEpochMilli(millisecond);
        ZonedDateTime z = ZonedDateTime.ofInstant(i, ZoneId.systemDefault());
        return z;
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

        if (years > 1.0D) return decimalFormat.format(years) + " yrs";
        if (months > 1.0D) return decimalFormat.format(months) + " mths";
        if (weeks > 1.0D) return decimalFormat.format(weeks) + " wks";
        if (days > 1.0D) return decimalFormat.format(days) + " d";
        if (hours > 1.0D) return decimalFormat.format(hours) + " hrs";
        if (minutes > 1.0D) return decimalFormat.format(minutes) + " mins";
        if (seconds > 1.0D) return decimalFormat.format(seconds) + " secs";
        if (milliseconds > 1.0D) return decimalFormat.format(milliseconds) + " millis";

        return "instant";
    }

}
