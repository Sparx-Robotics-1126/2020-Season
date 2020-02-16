package frc.storage;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShootBallTest {
/**
 * MAXIMUM TESTING ACHEIVED
 */
	@Test
	public void ShooterSenserFalseAndNoBallsInChamberTest() {
		//creating new object using constructor from StorageTestSensor called "testSensor"
		StorageTestSensor testSensor = new StorageTestSensor();
		//setting testSensor's TestShootSensor value to false and make it so that whenever testSensor object is called, it will refer to the TestShootSensor value in the testSensor object
		testSensor.testShootSensor = false; 
		//creating new object using ShootBall's constructor called shootObject and setting it's sensor value to that of testSensor, which in turn refers to TestShootSensor, which is false, which sets shootObject's sensor value to false
		ShootBall shootObject = new ShootBall(testSensor, (short) 2, 0.5);
		// using the object shootObject to execute a function in it called "execute". when the object shootObject creates an object, it will be called outPut.
		StorageOutput outPut = shootObject.execute();
		//calls for shootObject to execute again to complete the test
		outPut = shootObject.execute();
		
		//the boolean of object outPut should return false for commandFinished
		assertEquals("boolean value of object outPut should return False for commandFinished (first test)", false , outPut.isCommandFinished());
		//the motor speed of object outPut should return aprox 0.5 for Output
		assertEquals("Speed value of object outPut should return aprox 0.5 for Output(first test)", 0.5, outPut.getOutput(), 0.001);
		//the number of balls value of object outPut should return 0 for numOfBalls
		assertEquals("the number of balls value of object outPut should return 0 for numOfBalls(first test)", 2, outPut.getNumOfBallsAquired(), 0);
	}
	@Test
	public void ShooterSensorTrueAndnoBallsInChamberTest() {
		//creating new object using constructor from StorageTestSensor called "testSensor2"
		StorageTestSensor testSensor2 = new StorageTestSensor();
		//setting testSensor2's TestShooterSensor value to true and make it so that whenever testSensor2 object is called, it will refer to the TestShootSensor value in the testSensor2 object
		testSensor2.testShootSensor = true;
		// creating new object using ShootBall's constructor called shotObject2 and setting it's sensor value to that of testSensor2, which in turn refers to TestShootSensor, which is true, which sets shootObject2's sensor value to true
		ShootBall shootObject2 = new ShootBall(testSensor2, (short) 2, 0.5);
		//using the object shootObject2 to execute a function in it called "execute". when the object shootObject2 creates and object, it will be called outPut2.
		StorageOutput outPut2 = shootObject2.execute();
		//calls for shootObject2 to execute again to complete the test
		outPut2 = shootObject2.execute();
		
		//the boolean of object outPut2 should return false for commandFinished.
		assertEquals("boolean value of object outPut2 should return true for commandFinished (second test)", false , outPut2.isCommandFinished());
		//the motor speed of object outPut2 should return aprox 0.5 for output
		assertEquals("Speed value of object outPut2 should return 0 for Output(second test)", 0.5 , outPut2.getOutput(), 0.001);
		//the number of balls value of object outPut2 should return 2 for numOfBalls
		assertEquals("the number of balls value of object outPut2 should return 0 for numOfBalls(second test)", 2, outPut2.getNumOfBallsAquired(), 0);
	}
	@Test
	public void ShooterSensorFalseAndOneBallInChamberTest() {
		//creating new object using constructor from StorageTestSensor called "testSensor3"
		StorageTestSensor testSensor3 = new StorageTestSensor();
		//setting testSensor3's TestShooterSensor value to true and make it so that whenever testSensor3 object is called, it will refer to the TestShootSensor value in the testSensor3 object
		testSensor3.testShootSensor = true;
		// creating new object using ShootBall's constructor called shotObject3 and setting it's sensor value to that of testSensor3, which in turn refers to TestShootSensor, which is true, which sets shootObject3's sensor value to true
		ShootBall shootObject3 = new ShootBall(testSensor3, (short) 2, 0.5);
		//using the object shootObject3 to execute a function in it called "execute". when the object shootObject3 creates and object, it will be called outPut3.
		StorageOutput outPut3 = shootObject3.execute();
		//setting testSensor3's TestShooterSensor value to false to make sure that the section of code that we want to access can be accessed on the second execute.
		testSensor3.testShootSensor = false;
		//calls for shootObject3 to execute again to complete the test
		outPut3 = shootObject3.execute();
		
		//the boolean of object outPut3 should return true for commandFinished.
		assertEquals("boolean value of object outPut3 should return true for commandFinished (third test)", true , outPut3.isCommandFinished());
		//the motor speed of object outPut3 should return 0 for output
		assertEquals("Speed value of object outPut3 should return 0 for Output(third test)", 0 , outPut3.getOutput(), 0);
		//the number of balls value of object outPut3 should return 0 for numOfBalls
		assertEquals("the number of balls value of object outPut3 should return 0 for numOfBalls(third test)", 1, outPut3.getNumOfBallsAquired(), 0);
	}
	@Test
	public void ShooterSenserTrueThenStorageIsFull() {
		//creating new object using constructor from StorageTestSensor called "testSensor4"
		StorageTestSensor testSensor4 = new StorageTestSensor();
		//setting testSensor4's TestShootSensor value to false and make it so that whenever testSensor4 object is called, it will refer to the TestShootSensor value in the testSensor4 object
		testSensor4.testShootSensor = false; 
		//creating new object using ShootBall's constructor called shootObject4 and setting it's sensor value to that of testSensor4, which in turn refers to TestShootSensor, which is false, which sets shootObject4's sensor value to false
		ShootBall shootObject4 = new ShootBall(testSensor4, (short) 2, 0.5);
		// using the object shootObject4 to execute a function in it called "execute". when the object shootObject4 creates an object, it will be called outPut4.
		StorageOutput outPut4 = shootObject4.execute();
		//setting testSensor4's TestShooterSensor value to true to make sure that the section of code that we want to access can be accessed on the second execute.
		testSensor4.testShootSensor = true;
		//calls for shootObject4 to execute again to complete the test
		outPut4 = shootObject4.execute();
		
		//the boolean of object outPut4 should return false for commandFinished
		assertEquals("boolean value of object outPut4 should return False for commandFinished (fourth test)", false , outPut4.isCommandFinished());
		//the motor speed of object outPut4 should return aprox 0.5 for Output
		assertEquals("Speed value of object outPut4 should return aprox 0.5 for Output(fourth test)", 0.5, outPut4.getOutput(), 0.001);
		//the number of balls value of object outPut4 should return 0 for numOfBalls
		assertEquals("the number of balls value of object outPut4 should return 0 for numOfBalls(fourth test)", 2, outPut4.getNumOfBallsAquired(), 0);
	}
	
}
