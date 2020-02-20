package frc.storage;

import frc.storage.StorageSensorInterface;

public class StorageTestSensor implements StorageSensorInterface {

	
	public boolean testIntakeSensor;
	public boolean testIndexSensor;
	public boolean testShootSensor;
	
	
	@Override
	public boolean getIntakeSensor() {
		// TODO Auto-generated method stub
		System.out.println("Intake: " + testIntakeSensor);
		return testIntakeSensor;
	}

	@Override
	public boolean getIndexSensor() {
		// TODO Auto-generated method stub
		System.out.println("Index: " + testIndexSensor);
		return testIndexSensor;
	}

	@Override
	public boolean getShootSensor() {
		// TODO Auto-generated method stub
		System.out.println("Shoot: " + testShootSensor);
		return testShootSensor;
	}
}
