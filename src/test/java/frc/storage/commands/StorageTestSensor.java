package frc.storage.commands;

import frc.storage.StorageSensorInterface;

public class StorageTestSensor implements StorageSensorInterface {

	
	public boolean testIntakeSensor;
	public boolean testIndexSensor;
	public boolean testShootSensor;
	
	
	@Override
	public boolean getIntakeSensor() {
		// TODO Auto-generated method stub
		return testIntakeSensor;
	}

	@Override
	public boolean getIndexSensor() {
		// TODO Auto-generated method stub
		return testIndexSensor;
	}

	@Override
	public boolean getShootSensor() {
		// TODO Auto-generated method stub
		return testShootSensor;
	}
}
