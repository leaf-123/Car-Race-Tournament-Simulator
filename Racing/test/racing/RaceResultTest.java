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

public class RaceResultTest {
    
    private Driver driver;
    private RaceResult firstPlace;
    private RaceResult secondPlace;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        driver = new Driver("Maksud", 8, "Ferrari");
        firstPlace = new RaceResult(driver, 1, 25.25);
        secondPlace = new RaceResult(driver, 2, 31.14);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCalculatePoints() {
        assertEquals("First place receives 10 points", 10, RaceResult.calculatePoints(1));
        assertEquals("Second place receives 7 points", 7, RaceResult.calculatePoints(2));
        assertEquals("Third place receives 5 points", 5, RaceResult.calculatePoints(3));
        assertEquals("Fourth place receives 3 points", 3, RaceResult.calculatePoints(4));
        assertEquals("Fifth place receives 1 point", 1, RaceResult.calculatePoints(5));
        assertEquals("Other placements receive 0 points", 0, RaceResult.calculatePoints(6));
    }

    @Test
    public void testRaceResultStoresPoints() {
        assertEquals("RaceResult calculates points from placement", 10, firstPlace.getPoints());
        assertEquals("RaceResult calculates points from placement", 7, secondPlace.getPoints());
    }

    @Test
    public void testToString() {
        assertTrue("toString contains driver name", firstPlace.toString().contains("Maksud"));
        assertTrue("toString contains points", firstPlace.toString().contains("points"));
    }
}
