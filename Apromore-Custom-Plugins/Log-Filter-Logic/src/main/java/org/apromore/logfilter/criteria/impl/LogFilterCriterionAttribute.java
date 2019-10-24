/*
 * Copyright © 2019 The University of Melbourne.
 *
 * This file is part of "Apromore".
 *
 * "Apromore" is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * "Apromore" is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program.
 * If not, see <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */

package org.apromore.logfilter.criteria.impl;

import org.apromore.logfilter.criteria.model.Action;
import org.apromore.logfilter.criteria.model.Containment;
import org.apromore.logfilter.criteria.model.Level;
import org.apromore.logfilter.criteria.model.LogFilterTypeSelector;
import org.apromore.logfilter.criteria.model.Type;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XAttributeTimestamp;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XTrace;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import java.util.Set;

public class LogFilterCriterionAttribute extends AbstractLogFilterCriterion {

    public LogFilterCriterionAttribute(Action action, Containment containment, Level level, String label, String attribute, Set<String> value) {
        super(action, containment, level, label, attribute, value);
    }
    @Override
    public boolean matchesCriterion(XTrace trace) {//2019-10-24

        if(level == Level.TRACE) {

            UnifiedMap<String, Boolean> matchMap = new UnifiedMap<>(); //2019-10-24

            for (XEvent event : trace) {
                if (containment == Containment.CONTAIN_ANY) {
                    if (isMatching(event)) return true;
                } else if (containment == Containment.CONTAIN_ALL) {
                    for(String v : value) {
                        if(isMatchingEventAttribute(event, attribute, v)) {
                            matchMap.put(event.getAttributes().get("concept:name").toString(), true);
                        }
                    }
                }
            }
            if(matchMap.size() >= value.size()) return true;
            else return false;
        }

        return false;
    }
//    @Override
//    public boolean matchesCriterion(XTrace trace) {
//        if(level == Level.TRACE) {
//            for (XEvent event : trace) {
//                if (containment == Containment.CONTAIN_ANY) {
//                    if (isMatching(event)) return true;
//                } else if (containment == Containment.CONTAIN_ALL) {
//                    if (!isMatching(event)) return false;
//                }
//            }
//            if(containment == Containment.CONTAIN_ANY) return false;
//            else return containment == Containment.CONTAIN_ALL;
//        }
//        return false;
//    }

    @Override
    public boolean matchesCriterion(XEvent event) {
        if(level == Level.EVENT) {
            return isMatching(event);
        }else return false;
    }

    private boolean isMatching(XEvent event) {
        XAttribute xAttribute = event.getAttributes().get(attribute);
        //if(attribute.equals(timestamp_code)) {
        if (LogFilterTypeSelector.getType(attribute) == Type.TIME_TIMESTAMP) {
            long start = 0;
            long end = Long.MAX_VALUE;
            for(String v : value) {
                if(v.startsWith(">")) start = Long.parseLong(v.substring(1));
                if(v.startsWith("<")) end = Long.parseLong(v.substring(1));
            }
            long t = ((XAttributeTimestamp) xAttribute).getValueMillis();
            return start <= t && t <= end;
        }else {
            if (xAttribute != null) {
                String xAttributeValue = xAttribute.toString();
                return value.contains(xAttributeValue);
            }
        }
        return false;
    }

    private boolean isMatchingEventAttribute(XEvent xEvent, String attributeKey, String attributeValue) {//2019-10-24
        if(xEvent.getAttributes().containsKey(attributeKey)) {
            if(xEvent.getAttributes().get(attributeKey).toString().equals(attributeValue)){
                return true;
            }else{
                return false;
            }
        }else return false;
    }
}
