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

public class DriverTest {
    
    private Driver maksud;
    private Driver farizi;
    private Vehicle ferrari;
    private Vehicle bmw;
    private Vehicle criticalCar;
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
        maksud = new Driver("Maksud", 8, "Ferrari");
        farizi = new Driver("Farizi", 6, "BMW");
        ferrari = new Vehicle("Ferrari", 100, 90, 100);
        bmw = new Vehicle("BMW", 85, 80, 100);
        criticalCar = new Vehicle("Porsche", 95, 20, 100);
        clearTrack = new RaceTrack("Desert_Drift", 10, 3, Weather.CLEAR);
        rainTrack = new RaceTrack("Urban_Dash", 12, 4, Weather.RAIN);
        fogTrack = new RaceTrack("Mountain_Pass", 15, 3, Weather.FOG);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testEffectiveSpeed() {
        assertEquals("Preferred vehicle gives 10 percent speed bonus",
                118.8, maksud.effectiveSpeed(ferrari, clearTrack), 0.001);
        
        assertEquals("Rain reduces effective speed",
                85.085, farizi.effectiveSpeed(bmw, rainTrack), 0.001);
    }

    @Test
    public void testAddPoints() {
        assertEquals("Driver starts with 0 points", 0, maksud.getPoints());
        
        maksud.addPoints(10);
        maksud.addPoints(7);
        
        assertEquals("Points are accumulated", 17, maksud.getPoints());
    }

    @Test
    public void testNeedsPitStop() {
        assertTrue("Critical vehicle needs pit stop",
                maksud.needsPitStop(criticalCar, clearTrack));
        
        assertTrue("Fog track requires pit stop",
                maksud.needsPitStop(ferrari, fogTrack));
        
        assertFalse("Healthy vehicle on clear track does not need pit stop",
                maksud.needsPitStop(ferrari, clearTrack));
    }
}

