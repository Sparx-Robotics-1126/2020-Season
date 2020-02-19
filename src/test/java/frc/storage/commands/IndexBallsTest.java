package frc.storage.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import frc.storage.StorageOutput;
import frc.storage.StorageTestSensor;
import frc.storage.command.IndexBalls;


public class IndexBallsTest {
   
    @Test
    public void testBallEntering(){
        StorageTestSensor tsa = new StorageTestSensor();
        IndexBalls dexter = new IndexBalls(tsa, (short) 0);

        tsa.testShootSensor = false;
        tsa.testIntakeSensor = true; 
        tsa.testIndexSensor  = false;
        StorageOutput output = dexter.execute();
        assertEquals(output.getPrimaryOutput(),0,0);
        assertEquals(output.getNumOfBallsAquired(),1);
    }

    @Test 
    public void shootingBall(){
        StorageTestSensor tsa = new StorageTestSensor();
        IndexBalls dexter = new IndexBalls(tsa,(short)1);

        tsa.testIntakeSensor = false; 
        tsa.testIndexSensor  = false;
        tsa.testShootSensor  = true;
        StorageOutput output = dexter.execute();
        assertEquals(output.getPrimaryOutput(),0,0);
        assertEquals(output.getNumOfBallsAquired(),0);    
    } 
   
    @Test
    public void testingEverything(){
        StorageTestSensor tsa = new StorageTestSensor();
        IndexBalls dexter = new IndexBalls(tsa,(short)0);

        tsa.testIntakeSensor = true; 
        tsa.testIndexSensor  = false;
        tsa.testShootSensor  = false;
        StorageOutput output = dexter.execute();
        assertEquals(output.getPrimaryOutput(),0,0);
        assertEquals(output.getNumOfBallsAquired(),1);

        tsa.testIntakeSensor = false; 
        tsa.testIndexSensor  = true;
        output = dexter.execute();
        assertEquals(output.getPrimaryOutput(),.2,0);
        assertEquals(output.getNumOfBallsAquired(),1,0);

        tsa.testIntakeSensor = true;
        tsa.testIndexSensor = true;
        output = dexter.execute();
        assertEquals(output.getPrimaryOutput(),.2,0);
        assertEquals(output.getNumOfBallsAquired(),2,0);

        tsa.testIntakeSensor = false;
        tsa.testIndexSensor = false;
        output = dexter.execute();
        assertEquals(output.getPrimaryOutput(),0,0);
        assertEquals(output.getNumOfBallsAquired(),2,0);

        tsa.testShootSensor = true;
        output = dexter.execute();
        assertEquals(output.getPrimaryOutput(),0,0);
        assertEquals(output.getNumOfBallsAquired(),1,0);
        

    }
}