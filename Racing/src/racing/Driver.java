/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racing;

/**
 * Represents a racing driver with skill, preferred vehicle model,
 * and tournament points.
 */
public class Driver {
    private String name;
    private int skill;
    private String preferredVehicleModel;
    private int points;

    /**
     * Creates a driver.
     *
     * @param name the name of the driver
     * @param skill the skill level of the driver
     * @param preferredVehicleModel the model of vehicle preferred by the driver
     */
    public Driver(String name, int skill, String preferredVehicleModel) {
        this.name = name;
        this.skill = skill;
        this.preferredVehicleModel = preferredVehicleModel;
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public int getSkill() {
        return skill;
    }

    public String getPreferredVehicleModel() {
        return preferredVehicleModel;
    }

    public int getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkill(int skill) {
        if (skill < 0) {
            throw new IllegalArgumentException("Skill cannot be negative.");
        }
        this.skill = skill;
    }

    public void setPreferredVehicleModel(String preferredVehicleModel) {
        this.preferredVehicleModel = preferredVehicleModel;
    }

    public void setPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
        this.points = points;
    }

    /**
     * Calculates the effective speed of the driver with a vehicle on a track.
     *
     * @param vehicle the vehicle being driven
     * @param track the race track affecting performance
     * @return the calculated effective speed
     */
    public double effectiveSpeed(Vehicle vehicle, RaceTrack track) {
        double result = vehicle.getSpeed();

        result += skill;

        if (vehicle.getModel().equals(preferredVehicleModel)) {
            result *= 1.10;
        }

        result *= track.speedMultiplier();
        
        // changed chain of if-elses to switch
        switch (vehicle.getCondition()) {
            case WORN:
                result *= 0.85;
                break;
            case CRITICAL:
                result *= 0.60;
                break;
            case RETIRED:
                result = 0;
                break;
            default:
                // OPTIMAL → no change
                break;
        }

        return result;
    }

    /**
     * Adds tournament points to the driver.
     *
     * @param points the number of points to add
     */
    public void addPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Added points cannot be negative.");
        }
        this.points += points;
    }

    /**
     * Determines whether the driver needs a pit stop.
     *
     * @param vehicle the vehicle currently being driven
     * @param track the race track conditions
     * @return true if a pit stop is needed, false otherwise
     */
    public boolean needsPitStop(Vehicle vehicle, RaceTrack track) {
        return vehicle.getCondition() == VehicleCondition.CRITICAL
                || vehicle.getFuel() < 20
                || track.requiresPitStop();
    }
}

