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

public class MechanicTest {
    
    private Mechanic mike;
    private Mechanic anna;
    private Vehicle damagedCar;
    private Vehicle lowFuelCar;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        mike = new Mechanic("Mike", 5);
        anna = new Mechanic("Anna", 7);
        damagedCar = new Vehicle("Porsche", 95, 40, 100);
        lowFuelCar = new Vehicle("BMW", 85, 80, 30);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRepair() {
        mike.repair(damagedCar);
        
        assertEquals("Repair amount depends on mechanic efficiency",
                90.0, damagedCar.getDurability(), 0.001);
    }

    @Test
    public void testRefuel() {
        mike.refuel(lowFuelCar);
        
        assertEquals("Refuel amount depends on mechanic efficiency",
                80.0, lowFuelCar.getFuel(), 0.001);
    }

    @Test
    public void testService() {
        damagedCar.consumeFuel(60);
        anna.service(damagedCar);
        
        assertEquals("Service repairs vehicle", 100.0, damagedCar.getDurability(), 0.001);
        assertEquals("Service refuels vehicle", 100.0, damagedCar.getFuel(), 0.001);
    }
}

