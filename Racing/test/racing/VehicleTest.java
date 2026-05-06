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

public class VehicleTest {
    
    private Vehicle ferrari;
    private Vehicle wornCar;
    private Vehicle criticalCar;
    private Driver driver;
    private RaceTrack clearTrack;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        ferrari = new Vehicle("Ferrari", 100, 90, 100);
        wornCar = new Vehicle("BMW", 85, 60, 100);
        criticalCar = new Vehicle("Porsche", 95, 20, 100);
        driver = new Driver("Maksud", 8, "Ferrari");
        clearTrack = new RaceTrack("Desert_Drift", 10, 3, Weather.CLEAR);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConsumeFuel() {
        ferrari.consumeFuel(20);

        assertEquals("Fuel is reduced by the given amount", 80.0, ferrari.getFuel(), 0.001);

        ferrari.consumeFuel(100);

        assertEquals("Fuel cannot go below 0", 0.0, ferrari.getFuel(), 0.001);
        assertEquals("Vehicle becomes critical when fuel reaches 0",
                VehicleCondition.CRITICAL, ferrari.getCondition());
    }

    @Test
    public void testReduceDurability() {
        ferrari.reduceDurability(40);
        
        assertEquals("Durability is reduced by the given amount", 50.0, ferrari.getDurability(), 0.001);
        assertEquals("Vehicle becomes worn below 70 durability",
                VehicleCondition.WORN, ferrari.getCondition());
        
        ferrari.reduceDurability(30);
        
        assertEquals("Vehicle becomes critical below 30 durability",
                VehicleCondition.CRITICAL, ferrari.getCondition());
    }

    @Test
    public void testRepair() {
        criticalCar.repair(60);
        
        assertEquals("Repair increases durability", 80.0, criticalCar.getDurability(), 0.001);
        assertEquals("Vehicle returns to optimal when durability is high enough",
                VehicleCondition.OPTIMAL, criticalCar.getCondition());
    }

    @Test
    public void testRefuel() {
        ferrari.consumeFuel(80);
        ferrari.refuel(40);
        
        assertEquals("Refuel increases fuel", 60.0, ferrari.getFuel(), 0.001);
        
        ferrari.refuel(100);
        
        assertEquals("Fuel is capped at 100", 100.0, ferrari.getFuel(), 0.001);
    }

    @Test
    public void testDriveLap() {
        double speed = ferrari.driveLap(driver, clearTrack);
        
        assertTrue("Driving one lap returns positive speed", speed > 0);
        assertEquals("Fuel is consumed after driving", 90.0, ferrari.getFuel(), 0.001);
        assertEquals("Durability is reduced after driving", 82.0, ferrari.getDurability(), 0.001);
    }

    @Test
    public void testIsRetired() {
        assertFalse("New vehicle with fuel and durability is not retired", ferrari.isRetired());

        ferrari.consumeFuel(100);

        assertFalse("Vehicle is not retired only because fuel reaches 0", ferrari.isRetired());
        assertEquals("Vehicle becomes critical when fuel reaches 0",
                VehicleCondition.CRITICAL, ferrari.getCondition());

        ferrari.reduceDurability(100);

        assertTrue("Vehicle is retired when durability reaches 0", ferrari.isRetired());
    }
    
    // extra Test to check if pit-stop & refuel logic works
    @Test
    public void testRefuelFromZeroFuelCriticalState() {
        ferrari.consumeFuel(100);

        assertEquals("Vehicle is critical when fuel reaches 0",
                VehicleCondition.CRITICAL, ferrari.getCondition());

        ferrari.refuel(50);

        assertEquals("Fuel is restored by refuel", 50.0, ferrari.getFuel(), 0.001);
        assertEquals("Vehicle returns to optimal after refuel if durability is high",
                VehicleCondition.OPTIMAL, ferrari.getCondition());
    }
}

