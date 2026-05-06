/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package racing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents the whole racing tournament.
 */
public class Tournament {
    private List<Driver> drivers;
    private List<Vehicle> vehicles;
    private List<Mechanic> mechanics;
    private List<RaceTrack> tracks;
    private List<RaceEvent> events;

    /**
     * Creates an empty tournament.
     */
    public Tournament() {
        this.drivers = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.mechanics = new ArrayList<>();
        this.tracks = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Mechanic> getMechanics() {
        return mechanics;
    }

    public List<RaceTrack> getTracks() {
        return tracks;
    }

    public List<RaceEvent> getEvents() {
        return events;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setMechanics(List<Mechanic> mechanics) {
        this.mechanics = mechanics;
    }

    public void setTracks(List<RaceTrack> tracks) {
        this.tracks = tracks;
    }

    public void setEvents(List<RaceEvent> events) {
        this.events = events;
    }

    /**
     * Adds a driver to the tournament.
     *
     * @param driver the driver to add
     */
    public void addDriver(Driver driver) {
        if (drivers.contains(driver)) {
            throw new IllegalArgumentException("Driver already exists.");
        }
        drivers.add(driver);
    }

    /**
     * Adds a vehicle to the tournament.
     *
     * @param vehicle the vehicle to add
     */
    public void addVehicle(Vehicle vehicle) {
        if (vehicles.contains(vehicle)) {
            throw new IllegalArgumentException("Vehicle already exists.");
        }
        vehicles.add(vehicle);
    }

    /**
     * Adds a mechanic to the tournament.
     *
     * @param mechanic the mechanic to add
     */
    public void addMechanic(Mechanic mechanic) {
        if (mechanics.contains(mechanic)) {
            throw new IllegalArgumentException("Mechanic already exists.");
        }
        mechanics.add(mechanic);
    }

    /**
     * Adds a race track to the tournament.
     *
     * @param track the race track to add
     */
    public void addTrack(RaceTrack track) {
        if (tracks.contains(track)) {
            throw new IllegalArgumentException("Track already exists.");
        }
        tracks.add(track);
    }

    /**
     * Prepares one race event for each track by assigning drivers,
     * vehicles, and mechanics.
     */
    public void prepareEvents() {
        if (drivers.isEmpty() || vehicles.isEmpty() || mechanics.isEmpty() || tracks.isEmpty()) {
            throw new IllegalStateException("Tournament data is incomplete.");
        }

        events.clear();

        for (RaceTrack track : tracks) {
            RaceEvent event = new RaceEvent(track);

            for (int i = 0; i < drivers.size(); i++) {
                Driver driver = drivers.get(i);
                
                // make sure each race has fresh vehicle instance
                Vehicle baseVehicle = vehicles.get(i % vehicles.size());

                Vehicle vehicle = new Vehicle(
                        baseVehicle.getModel(),
                        baseVehicle.getSpeed(),
                        baseVehicle.getDurability(),
                        baseVehicle.getFuel()
                );

                Mechanic mechanic = mechanics.get(i % mechanics.size());

                RaceEntry entry = new RaceEntry(driver, vehicle, mechanic);
                event.addEntry(entry);
            }

            events.add(event);
        }
    }

    /**
     * Runs all race events and prints the final podium.
     */
    public void runTournament() {
        prepareEvents();

        for (RaceEvent event : events) {
            event.runRace();
        }

        List<Driver> finalPodium = podium();
        System.out.println("Final podium:");
        for (Driver driver : finalPodium) {
            System.out.println(driver.getName() + " - " + driver.getPoints() + " points");
        }
    }

    /**
     * Returns the top three drivers based on accumulated points.
     *
     * @return a list of the top three drivers sorted by points in descending order
     */
    public List<Driver> podium() {
        List<Driver> sortedDrivers = new ArrayList<>(drivers);
        sortedDrivers.sort(Comparator.comparingInt(Driver::getPoints).reversed());

        List<Driver> result = new ArrayList<>();

        int limit = Math.min(3, sortedDrivers.size());
        for (int i = 0; i < limit; i++) {
            result.add(sortedDrivers.get(i));
        }

        return result;
    }
}

