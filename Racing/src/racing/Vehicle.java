/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racing;

/**
 * Represents a racing vehicle with model, speed, durability, fuel,
 * and lifecycle condition.
 */

public class Vehicle {
    private String model;
    private double speed;
    private double durability;
    private double fuel;
    private VehicleCondition condition;

    /**
     * Creates a vehicle with the given model, speed, durability, and fuel.
     *
     * @param model the model name of the vehicle
     * @param speed the base speed of the vehicle
     * @param durability the durability level (0–100)
     * @param fuel the fuel level (0–100)
     */
    public Vehicle(String model, double speed, double durability, double fuel) {
        this.model = model;
        this.speed = speed;
        this.durability = durability;
        this.fuel = fuel;
        this.condition = VehicleCondition.OPTIMAL;
        updateCondition();  // could overridden if inherited - but safe for this task (no inheritance)
    }

    public String getModel() {
        return model;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDurability() {
        return durability;
    }

    public double getFuel() {
        return fuel;
    }

    public VehicleCondition getCondition() {
        return condition;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSpeed(double speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        this.speed = speed;
    }

    public void setDurability(double durability) {
        if (durability < 0) {
            this.durability = 0;
        } else if (durability > 100) {
            this.durability = 100;
        } else {
            this.durability = durability;
        }
        updateCondition();
    }

    public void setFuel(double fuel) {
        if (fuel < 0) {
            this.fuel = 0;
        } else if (fuel > 100) {
            this.fuel = 100;
        } else {
            this.fuel = fuel;
        }
        updateCondition();
    }

    public void setCondition(VehicleCondition condition) {
        this.condition = condition;
    }

    /**
     * Drives one lap with the given driver and track.
     * Fuel is consumed, durability is reduced, and condition is updated.
     *
     * @param driver the driver controlling the vehicle
     * @param track the race track affecting performance
     * @return the effective speed achieved during the lap
     */
    public double driveLap(Driver driver, RaceTrack track) {
        if (condition == VehicleCondition.RETIRED) {
            throw new IllegalStateException("Retired vehicle cannot drive.");
        }

        double lapSpeed = driver.effectiveSpeed(this, track);

        consumeFuel(track.fuelConsumption());
        reduceDurability(track.durabilityLoss());
        updateCondition();

        return lapSpeed;
    }

    /**
     * Reduces fuel by the given amount.
     *
     * @param amount the amount of fuel to consume
     */
    public void consumeFuel(double amount) {
        fuel -= amount;
        if (fuel < 0) {
            fuel = 0;
        }
        updateCondition();
    }

    /**
     * Reduces durability by the given amount.
     *
     * @param amount the amount of durability to reduce
     */
    public void reduceDurability(double amount) {
        durability -= amount;
        if (durability < 0) {
            durability = 0;
        }
        updateCondition();
    }

    /**
     * Repairs the vehicle by increasing durability.
     *
     * @param amount the amount of durability to restore
     */
    public void repair(double amount) {
        if (condition == VehicleCondition.RETIRED) {
            throw new IllegalStateException("Retired vehicle cannot be repaired.");
        }

        durability += amount;
        if (durability > 100) {
            durability = 100;
        }

        updateCondition();
    }

    /**
     * Refuels the vehicle.
     *
     * @param amount the amount of fuel to add
     */
    public void refuel(double amount) {
        fuel += amount;
        if (fuel > 100) {
            fuel = 100;
        }

        updateCondition();
    }

    /**
     * Updates the vehicle condition based on fuel and durability.
     */
    public void updateCondition() {
        if (durability <= 0) {
            condition = VehicleCondition.RETIRED;
        } else if (durability < 30 || fuel <= 0) {
            condition = VehicleCondition.CRITICAL;
        } else if (durability < 70) {
            condition = VehicleCondition.WORN;
        } else {
            condition = VehicleCondition.OPTIMAL;
        }
    }

    /**
     * Checks whether the vehicle is retired.
     *
     * @return true if the vehicle is retired, false otherwise
     */
    public boolean isRetired() {
        return condition == VehicleCondition.RETIRED;
    }

    
}
