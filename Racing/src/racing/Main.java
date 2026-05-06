/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for running the racing tournament simulation.
 */
public class Main {

    /**
     * Starts the racing tournament simulation.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Tournament tournament = new Tournament();

        try {
            loadDrivers(tournament, "drivers.txt");
            loadVehicles(tournament, "vehicles.txt");
            loadMechanics(tournament, "mechanics.txt");
            loadTracks(tournament, "tracks.txt");

            tournament.prepareEvents();

            System.out.println("Race results:");

            for (RaceEvent event : tournament.getEvents()) {
                event.runRace();
                printRaceResults(event);
            }

            printFinalPodium(tournament.podium());

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Simulation error: " + e.getMessage());
        }
    }

    /**
     * Loads drivers from a text file and adds them to the tournament.
     *
     * Expected format:
     * Name Skill PreferredVehicle
     *
     * @param tournament the tournament to fill
     * @param fileName the name of the input file
     * @throws FileNotFoundException if the file cannot be found
     */
    private static void loadDrivers(Tournament tournament, String fileName)
            throws FileNotFoundException {

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                String name = scanner.next();
                int skill = scanner.nextInt();
                String preferredVehicle = scanner.next();

                Driver driver = new Driver(name, skill, preferredVehicle);
                tournament.addDriver(driver);
            }
        }
    }

    /**
     * Loads vehicles from a text file and adds them to the tournament.
     *
     * Expected format:
     * Model Speed Durability Fuel
     *
     * @param tournament the tournament to fill
     * @param fileName the name of the input file
     * @throws FileNotFoundException if the file cannot be found
     */
    private static void loadVehicles(Tournament tournament, String fileName)
            throws FileNotFoundException {

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                String model = scanner.next();
                double speed = scanner.nextDouble();
                double durability = scanner.nextDouble();
                double fuel = scanner.nextDouble();

                Vehicle vehicle = new Vehicle(model, speed, durability, fuel);
                tournament.addVehicle(vehicle);
            }
        }
    }

    /**
     * Loads mechanics from a text file and adds them to the tournament.
     *
     * Expected format:
     * Name Efficiency
     *
     * @param tournament the tournament to fill
     * @param fileName the name of the input file
     * @throws FileNotFoundException if the file cannot be found
     */
    private static void loadMechanics(Tournament tournament, String fileName)
            throws FileNotFoundException {

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                String name = scanner.next();
                int efficiency = scanner.nextInt();

                Mechanic mechanic = new Mechanic(name, efficiency);
                tournament.addMechanic(mechanic);
            }
        }
    }

    /**
     * Loads race tracks from a text file and adds them to the tournament.
     *
     * Expected format:
     * Name Difficulty Laps Weather
     *
     * @param tournament the tournament to fill
     * @param fileName the name of the input file
     * @throws FileNotFoundException if the file cannot be found
     */
    private static void loadTracks(Tournament tournament, String fileName)
            throws FileNotFoundException {

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                String name = scanner.next();
                int difficulty = scanner.nextInt();
                int laps = scanner.nextInt();
                Weather weather = parseWeather(scanner.next());

                RaceTrack track = new RaceTrack(name, difficulty, laps, weather);
                tournament.addTrack(track);
            }
        }
    }

    /**
     * Converts text from the input file into a Weather enum value.
     *
     * @param text the weather text from the input file
     * @return the matching Weather value
     */
    private static Weather parseWeather(String text) {
        switch (text.toUpperCase()) {
            case "CLEAR":
                return Weather.CLEAR;
            case "RAIN":
                return Weather.RAIN;
            case "FOG":
                return Weather.FOG;
            default:
                throw new IllegalArgumentException("Unknown weather: " + text);
        }
    }

    /**
     * Prints the results of one race event.
     *
     * @param event the race event whose results are printed
     */
    private static void printRaceResults(RaceEvent event) {
        System.out.println();
        System.out.println("Track: " + event.getTrack().getName());

        for (RaceResult result : event.getResults()) {
            System.out.printf("%d. %s - time: %.2f, points: %d%n",
                    result.getPlacement(),
                    result.getDriver().getName(),
                    result.getTotalTime(),
                    result.getPoints());
        }
    }

    /**
     * Prints the final podium of the tournament.
     *
     * @param podium the list of top drivers
     */
    private static void printFinalPodium(List<Driver> podium) {
        System.out.println();
        System.out.println("Final podium:");

        int place = 1;
        for (Driver driver : podium) {
            System.out.println(place + ". " + driver.getName()
                    + " - " + driver.getPoints() + " points");
            place++;
        }
    }
}
