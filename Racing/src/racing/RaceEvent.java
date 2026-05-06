/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents one race event on one track.
 */
public class RaceEvent {
    private RaceTrack track;
    private List<RaceEntry> entries;
    private List<RaceResult> results;

    /**
     * Creates a race event for the given track.
     *
     * @param track the race track on which the event takes place
     */
    public RaceEvent(RaceTrack track) {
        this.track = track;
        this.entries = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    public RaceTrack getTrack() {
        return track;
    }

    public List<RaceEntry> getEntries() {
        return entries;
    }

    public List<RaceResult> getResults() {
        return results;
    }

    public void setTrack(RaceTrack track) {
        this.track = track;
    }

    public void setEntries(List<RaceEntry> entries) {
        this.entries = entries;
    }

    public void setResults(List<RaceResult> results) {
        this.results = results;
    }

    /**
     * Adds one race entry.
     *
     * @param entry the race entry to add
     */
    public void addEntry(RaceEntry entry) {
        if (entries.contains(entry)) {
            throw new IllegalArgumentException("Entry already exists.");
        }
        entries.add(entry);
    }

    /**
     * Runs the race until every entry has finished or retired.
     */
    public void runRace() {
        results.clear();

        boolean allFinished = false;

        while (!allFinished) {
            allFinished = true;

            for (RaceEntry entry : entries) {
                if (!entry.isFinished(track)) {
                    entry.completeLap(track);
                }

                if (!entry.isFinished(track)) {
                    allFinished = false;
                }
            }
        }

        reportResults();
        distributePoints();
    }

    /**
     * Creates race results from finished and retired entries.
     *
     * @return a list of race results sorted by placement
     */
    public List<RaceResult> reportResults() {
        results.clear();

        List<RaceEntry> finishedEntries = new ArrayList<>();
        List<RaceEntry> retiredEntries = new ArrayList<>();

        for (RaceEntry entry : entries) {
            if (entry.isRetired()) {
                retiredEntries.add(entry);
            } else {
                finishedEntries.add(entry);
            }
        }

        finishedEntries.sort(Comparator.comparingDouble(RaceEntry::getTotalTime));

        int place = 1;

        for (RaceEntry entry : finishedEntries) {
            RaceResult result = new RaceResult(entry.getDriver(), place, entry.getTotalTime());
            results.add(result);
            place++;
        }

        for (RaceEntry entry : retiredEntries) {
            RaceResult result = new RaceResult(entry.getDriver(), place, entry.getTotalTime());
            result.setPoints(0);
            results.add(result);
            place++;
        }

        return results;
    }

    /**
     * Adds points from race results to drivers.
     */
    public void distributePoints() {
        for (RaceResult result : results) {
            result.getDriver().addPoints(result.getPoints());
        }
    }
}

