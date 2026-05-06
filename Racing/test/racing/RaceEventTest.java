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

public class RaceEventTest {
    
    private RaceTrack track;
    private RaceEvent event;
    private Driver driver1, driver2;
    private Vehicle vehicle1, vehicle2;
    private Mechanic mechanic;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        track = new RaceTrack("Desert_Drift", 10, 1, Weather.CLEAR);
        event = new RaceEvent(track);
        
        driver1 = new Driver("Maksud", 8, "Ferrari");
        driver2 = new Driver("Farizi", 6, "BMW");
        
        vehicle1 = new Vehicle("Ferrari", 100, 90, 100);
        vehicle2 = new Vehicle("BMW", 85, 80, 100);
        
        mechanic = new Mechanic("Mike", 5);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddEntry() {
        RaceEntry entry = new RaceEntry(driver1, vehicle1, mechanic);
        
        event.addEntry(entry);
        
        assertEquals("RaceEvent contains one entry after adding", 1, event.getEntries().size());
    }

    @Test
    public void testRunRace() {
        event.addEntry(new RaceEntry(driver1, vehicle1, mechanic));
        event.addEntry(new RaceEntry(driver2, vehicle2, mechanic));
        
        event.runRace();
        
        assertEquals("Race creates result for each entry", 2, event.getResults().size());
        assertTrue("Winner receives more points than second place",
                event.getResults().get(0).getPoints() > event.getResults().get(1).getPoints());
    }

    @Test
    public void testReportResults() {
        event.addEntry(new RaceEntry(driver1, vehicle1, mechanic));
        event.addEntry(new RaceEntry(driver2, vehicle2, mechanic));
        event.runRace();
        
        List<RaceResult> results = event.reportResults();
        
        assertEquals("Report results returns all results", 2, results.size());
    }

    @Test
    public void testDistributePoints() {
        event.addEntry(new RaceEntry(driver1, vehicle1, mechanic));
        event.addEntry(new RaceEntry(driver2, vehicle2, mechanic));
        event.runRace();
        
        assertTrue("Driver points are distributed after race", driver1.getPoints() > 0);
    }
}
