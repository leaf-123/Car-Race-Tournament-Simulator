/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package racing;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RaceEntryTest {
    
    private Driver driver;
    private Vehicle vehicle;
    private Mechanic mechanic;
    private RaceTrack track;
    private RaceEntry entry;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        driver = new Driver("Maksud", 8, "Ferrari");
        vehicle = new Vehicle("Ferrari", 100, 90, 100);
        mechanic = new Mechanic("Mike", 5);
        track = new RaceTrack("Desert_Drift", 10, 3, Weather.CLEAR);
        entry = new RaceEntry(driver, vehicle, mechanic);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCompleteLap() {
        entry.completeLap(track);
        
        assertEquals("Completing one lap increases completed laps", 1, entry.getCompletedLaps());
        assertTrue("Completing one lap increases total time", entry.getTotalTime() > 0);
    }

    @Test
    public void testPitStop() {
        Vehicle criticalCar = new Vehicle("BMW", 85, 20, 40);
        RaceEntry criticalEntry = new RaceEntry(driver, criticalCar, mechanic);
        
        criticalEntry.pitStop();
        
        assertTrue("Pit stop improves vehicle durability", criticalCar.getDurability() > 20);
        assertFalse("Vehicle should not be retired after successful pit stop", criticalEntry.isRetired());
    }

    @Test
    public void testIsFinished() {
        RaceTrack shortTrack = new RaceTrack("Short_Track", 10, 1, Weather.CLEAR);
        RaceEntry shortEntry = new RaceEntry(driver, vehicle, mechanic);
        
        assertFalse("Entry is not finished before completing required laps", shortEntry.isFinished(shortTrack));
        
        shortEntry.completeLap(shortTrack);
        
        assertTrue("Entry is finished after completing required laps", shortEntry.isFinished(shortTrack));
    }
}
