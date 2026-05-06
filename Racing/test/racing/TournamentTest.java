/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package racing;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TournamentTest {
    
    private Tournament tournament;
    private Driver maksud, farizi, silva, extra;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        tournament = new Tournament();
        
        maksud = new Driver("Maksud", 8, "Ferrari");
        farizi = new Driver("Farizi", 6, "BMW");
        silva = new Driver("Silva", 7, "BMW");
        extra = new Driver("Extra", 5, "Porsche");
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddDriver() {
        tournament.addDriver(maksud);
        
        assertEquals("Tournament has one driver after adding", 1, tournament.getDrivers().size());
        assertEquals("Added driver is stored", maksud, tournament.getDrivers().get(0));
    }

    @Test
    public void testAddVehicle() {
        Vehicle vehicle = new Vehicle("Ferrari", 100, 90, 100);
        
        tournament.addVehicle(vehicle);
        
        assertEquals("Tournament has one vehicle after adding", 1, tournament.getVehicles().size());
    }

    @Test
    public void testPrepareEvents() {
        tournament.addDriver(maksud);
        tournament.addDriver(farizi);
        
        tournament.addVehicle(new Vehicle("Ferrari", 100, 90, 100));
        tournament.addVehicle(new Vehicle("BMW", 85, 80, 100));
        
        tournament.addMechanic(new Mechanic("Mike", 5));
        
        tournament.addTrack(new RaceTrack("Desert_Drift", 10, 1, Weather.CLEAR));
        tournament.addTrack(new RaceTrack("Urban_Dash", 12, 1, Weather.RAIN));
        
        tournament.prepareEvents();
        
        assertEquals("One race event is created for each track", 2, tournament.getEvents().size());
    }

    @Test
    public void testPodium() {
        maksud.addPoints(30);
        silva.addPoints(21);
        farizi.addPoints(15);
        extra.addPoints(2);
        
        tournament.addDriver(maksud);
        tournament.addDriver(farizi);
        tournament.addDriver(silva);
        tournament.addDriver(extra);
        
        List<Driver> podium = tournament.podium();
        
        assertEquals("Podium contains top three drivers", 3, podium.size());
        assertEquals("First place is driver with highest points", maksud, podium.get(0));
        assertEquals("Second place is driver with second highest points", silva, podium.get(1));
        assertEquals("Third place is driver with third highest points", farizi, podium.get(2));
    }

    @Test(expected = IllegalStateException.class)
    public void testPrepareEventsIncompleteTournament() {
        tournament.prepareEvents();
    }
    
    // extra Test to make sure prepareEvents() always creates a fresh vehicle instance
    @Test
    public void testPrepareEventsCreatesVehicleCopies() {
        tournament.addDriver(maksud);

        Vehicle originalVehicle = new Vehicle("Ferrari", 100, 90, 100);
        tournament.addVehicle(originalVehicle);

        tournament.addMechanic(new Mechanic("Mike", 5));
        tournament.addTrack(new RaceTrack("Desert_Drift", 10, 1, Weather.CLEAR));

        tournament.prepareEvents();

        Vehicle entryVehicle = tournament.getEvents().get(0).getEntries().get(0).getVehicle();

        assertNotSame("Race entry should receive a copy of the vehicle, not the original object",
                originalVehicle, entryVehicle);

        assertEquals("Copied vehicle has same model",
                originalVehicle.getModel(), entryVehicle.getModel());
        assertEquals("Copied vehicle has same speed",
                originalVehicle.getSpeed(), entryVehicle.getSpeed(), 0.001);
    }
}
