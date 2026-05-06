/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racing;

/**
 * Represents one driver's participation in one race,
 * connecting driver, vehicle, mechanic, and race progress.
 */
public class RaceEntry {
    private Driver driver;
    private Vehicle vehicle;
    private Mechanic mechanic;
    private int completedLaps;
    private double totalTime;
    private boolean retired;

    /**
     * Creates a race entry.
     *
     * @param driver the driver participating in the race
     * @param vehicle the vehicle used in the race
     * @param mechanic the mechanic assigned for this race
     */
    public RaceEntry(Driver driver, Vehicle vehicle, Mechanic mechanic) {
        this.driver = driver;
        this.vehicle = vehicle;
        this.mechanic = mechanic;
        this.completedLaps = 0;
        this.totalTime = 0;
        this.retired = false;
    }

    public Driver getDriver() {
        return driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public int getCompletedLaps() {
        return completedLaps;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public void setCompletedLaps(int completedLaps) {
        if (completedLaps < 0) {
            throw new IllegalArgumentException("Completed laps cannot be negative.");
        }
        this.completedLaps = completedLaps;
    }

    public void setTotalTime(double totalTime) {
        if (totalTime < 0) {
            throw new IllegalArgumentException("Total time cannot be negative.");
        }
        this.totalTime = totalTime;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    /**
     * Completes one lap for this race entry.
     *
     * @param track the race track where the lap is performed
     */
    public void completeLap(RaceTrack track) {
        if (retired) {
            throw new IllegalStateException("Retired race entry cannot continue.");
        }

        double lapSpeed = vehicle.driveLap(driver, track);

        if (lapSpeed <= 0 || vehicle.isRetired()) {
            retired = true;
            return;
        }

        double lapTime = 1000.0 / lapSpeed;
        totalTime += lapTime;
        completedLaps++;

        if (driver.needsPitStop(vehicle, track) && !retired) {
            pitStop();
        }
    }

    /**
     * Performs a pit stop using the assigned mechanic.
     */
    public void pitStop() {
        mechanic.service(vehicle);

        if (vehicle.getCondition() == VehicleCondition.RETIRED) {
            retired = true;
        }
    }

    /**
     * Checks whether this entry has finished the race.
     *
     * @param track the race track used to determine required laps
     * @return true if the race is finished or the entry is retired, false otherwise
     */
    public boolean isFinished(RaceTrack track) {
        return completedLaps >= track.getLaps() || retired;
    }
}

