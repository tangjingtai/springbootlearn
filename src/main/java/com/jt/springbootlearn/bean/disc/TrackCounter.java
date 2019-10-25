package com.jt.springbootlearn.bean.disc;

import java.util.HashMap;
import java.util.Map;

public class TrackCounter {
    private Map<Integer, Integer> trackCounts = new HashMap<>();

    public void countTracker(int trackNumber) {
        int count = getTrackCount(trackNumber);
        trackCounts.put(trackNumber, count + 1);
    }

    public int getTrackCount(int trackNumber) {
        return trackCounts.getOrDefault(trackNumber, 0);
    }
}
