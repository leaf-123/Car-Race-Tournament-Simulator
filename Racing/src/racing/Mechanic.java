/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racing;

/**
 * Represents a mechanic who can repair and refuel vehicles.
 */
public class Mechanic {
    private String name;
    private int efficiency;

    /**
     * Creates a mechanic.
     *
     * @param name the name of the mechanic
     * @param efficiency the efficiency level of the mechanic
     */
    public Mechanic(String name, int efficiency) {
        this.name = name;
        this.efficiency = efficiency;
    }

    public String getName() {
        return name;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEfficiency(int efficiency) {
        if (efficiency < 0) {
            throw new IllegalArgumentException("Efficiency cannot be negative.");
        }
        this.efficiency = efficiency;
    }

    /**
     * Repairs a vehicle using mechanic efficiency.
     *
     * @param vehicle the vehicle to be repaired
     */
    public void repair(Vehicle vehicle) {
        double repairAmount = efficiency * 10.0;
        vehicle.repair(repairAmount);
    }

    /**
     * Refuels a vehicle using mechanic efficiency.
     *
     * @param vehicle the vehicle to be refueled
     */
    public void refuel(Vehicle vehicle) {
        double fuelAmount = efficiency * 10.0;
        vehicle.refuel(fuelAmount);
    }

    /**
     * Performs a full pit stop service (repair and refuel).
     *
     * @param vehicle the vehicle to be serviced
     */
    public void service(Vehicle vehicle) {
        repair(vehicle);
        refuel(vehicle);
    }
}
