/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racing;

/**
 * Represents the result of one driver in one race.
 */
public class RaceResult {
    private Driver driver;
    private int placement;
    private double totalTime;
    private int points;

    /**
     * Creates a race result and calculates points from placement.
     *
     * @param driver the driver who participated in the race
     * @param placement the finishing position of the driver
     * @param totalTime the total time taken by the driver
     */
    public RaceResult(Driver driver, int placement, double totalTime) {
        this.driver = driver;
        this.placement = placement;
        this.totalTime = totalTime;
        this.points = calculatePoints(placement);
    }

    public Driver getDriver() {
        return driver;
    }

    public int getPlacement() {
        return placement;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public int getPoints() {
        return points;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setPlacement(int placement) {
        if (placement <= 0) {
            throw new IllegalArgumentException("Placement must be positive.");
        }
        this.placement = placement;
        this.points = calculatePoints(placement);
    }

    public void setTotalTime(double totalTime) {
        if (totalTime < 0) {
            throw new IllegalArgumentException("Total time cannot be negative.");
        }
        this.totalTime = totalTime;
    }

    public void setPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
        this.points = points;
    }

    /**
     * Calculates points based on placement.
     *
     * @param placement the finishing position
     * @return the number of points awarded for the placement
     */
    public static int calculatePoints(int placement) {
        // chain of if-elses changed to switch
        switch (placement) {
            case 1:
                return 10;
            case 2:
                return 7;
            case 3:
                return 5;
            case 4:
                return 3;
            case 5:
                return 1;
            default:
                return 0;
        }
    }

    /**
     * Returns a string representation of the race result.
     *
     * @return a formatted string containing placement, driver name, time, and points
     */
    @Override
    public String toString() {
        return placement + ". " + driver.getName()
                + " - time: " + totalTime
                + ", points: " + points;
    }
}

