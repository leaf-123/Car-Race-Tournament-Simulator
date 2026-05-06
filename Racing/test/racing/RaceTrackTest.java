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

public class RaceTrackTest {
    
    private RaceTrack clearTrack;
    private RaceTrack rainTrack;
    private RaceTrack fogTrack;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        clearTrack = new RaceTrack("Desert_Drift", 10, 3, Weather.CLEAR);
        rainTrack = new RaceTrack("Urban_Dash", 12, 4, Weather.RAIN);
        fogTrack = new RaceTrack("Mountain_Pass", 15, 3, Weather.FOG);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFuelConsumption() {
        assertEquals("Clear weather uses base fuel consumption",
                10.0, clearTrack.fuelConsumption(), 0.001);
        
        assertEquals("Rain increases fuel consumption",
                14.4, rainTrack.fuelConsumption(), 0.001);
    }

    @Test
    public void testDurabilityLoss() {
        assertEquals("Clear weather uses base durability loss",
                8.0, clearTrack.durabilityLoss(), 0.001);
        
        assertEquals("Fog increases durability loss",
                13.2, fogTrack.durabilityLoss(), 0.001);
    }

    @Test
    public void testSpeedMultiplier() {
        assertEquals("Clear weather has no speed penalty", 1.0, clearTrack.speedMultiplier(), 0.001);
        assertEquals("Rain reduces speed", 0.85, rainTrack.speedMultiplier(), 0.001);
        assertEquals("Fog reduces speed further", 0.70, fogTrack.speedMultiplier(), 0.001);
    }

    @Test
    public void testRequiresPitStop() {
        assertFalse("Clear weather does not require pit stop", clearTrack.requiresPitStop());
        assertFalse("Rain does not directly require pit stop", rainTrack.requiresPitStop());
        assertTrue("Fog requires pit stop", fogTrack.requiresPitStop());
    }
}

