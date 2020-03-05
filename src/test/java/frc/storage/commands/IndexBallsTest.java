package frc.storage.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import frc.health.HealthReport;
import frc.storage.StorageOutput;
import frc.storage.StorageTestSensor;
import frc.storage.command.IndexBalls;


public class IndexBallsTest {
   
    @Test
    public void testBallEnteringIndexer(){
        StorageTestSensor tsa = new StorageTestSensor();
        IndexBalls dexter = new IndexBalls(tsa, (short) 0);

        tsa.testShootSensor = false;
        tsa.testIntakeSensor = false; 
        tsa.testIndexSensor  = true;
        StorageOutput output = dexter.execute();
        assertEquals(1, output.getPrimaryOutput(), 0);
        assertEquals(0.1, output.getSecondaryOutput(), 0);
    }

    @Test
    public void testNoBallEntering(){
        StorageTestSensor tsa = new StorageTestSensor();
        IndexBalls dexter = new IndexBalls(tsa, (short) 0);

        tsa.testShootSensor = false;
        tsa.testIntakeSensor = false; 
        tsa.testIndexSensor  = false;
        StorageOutput output = dexter.execute();
        assertEquals(0, output.getPrimaryOutput(), 0);
        assertEquals(0, output.getSecondaryOutput(), 0);
    }

    @Test
    public void testBallEnteringRobot(){
        StorageTestSensor tsa = new StorageTestSensor();
        IndexBalls dexter = new IndexBalls(tsa, (short) 0);

        tsa.testIntakeSensor = true; 
        StorageOutput output = dexter.execute();
        assertEquals(1, output.getNumOfBallsAquired(), 0);

        tsa.testIntakeSensor = true; 
        output = dexter.execute();
        assertEquals(1, output.getNumOfBallsAquired(), 0);

        tsa.testIntakeSensor = false; 
        output = dexter.execute();
        assertEquals(1, output.getNumOfBallsAquired(), 0);

        tsa.testIntakeSensor = true; 
        output = dexter.execute();
        assertEquals(2, output.getNumOfBallsAquired(), 0);
    }
    
}