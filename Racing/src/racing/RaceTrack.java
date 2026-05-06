/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racing;

/**
 * Represents a race track with difficulty, laps, and weather.
 */
public class RaceTrack {
    private String name;
    private int difficulty;
    private int laps;
    private Weather weather;

    /**
     * Creates a race track.
     *
     * @param name the name of the track
     * @param difficulty the difficulty level of the track
     * @param laps the number of laps in the race
     * @param weather the weather conditions on the track
     */
    public RaceTrack(String name, int difficulty, int laps, Weather weather) {
        this.name = name;
        this.difficulty = difficulty;
        this.laps = laps;
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getLaps() {
        return laps;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(int difficulty) {
        if (difficulty < 0) {
            throw new IllegalArgumentException("Difficulty cannot be negative.");
        }
        this.difficulty = difficulty;
    }

    public void setLaps(int laps) {
        if (laps <= 0) {
            throw new IllegalArgumentException("Laps must be positive.");
        }
        this.laps = laps;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    /**
     * Calculates fuel consumption for one lap.
     *
     * @return the amount of fuel consumed per lap
     */
    public double fuelConsumption() {
        double amount = difficulty * 1.0;

        if (weather == Weather.RAIN) {
            amount *= 1.2;
        } else if (weather == Weather.FOG) {
            amount *= 1.1;
        }

        return amount;
    }

    /**
     * Calculates durability loss for one lap.
     *
     * @return the amount of durability lost per lap
     */
    public double durabilityLoss() {
        double amount = difficulty * 0.8;

        if (weather == Weather.RAIN) {
            amount *= 1.2;
        } else if (weather == Weather.FOG) {
            amount *= 1.1;
        }

        return amount;
    }

    /**
     * Returns the speed multiplier caused by weather.
     *
     * @return the speed multiplier based on weather conditions
     */
    public double speedMultiplier() {
        // chain of if-elses changed to switch
        switch (weather) {
            case CLEAR:
                return 1.0;
            case RAIN:
                return 0.85;
            case FOG:
                return 0.70;
            default:
                throw new IllegalStateException("Unknown weather.");
        }
    }

    /**
     * Checks whether weather requires a pit stop.
     *
     * @return true if a pit stop is required due to weather, false otherwise
     */
    public boolean requiresPitStop() {
        return weather == Weather.FOG;
    }
}

