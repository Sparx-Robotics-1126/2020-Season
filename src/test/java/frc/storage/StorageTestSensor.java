package frc.storage;

public class StorageTestSensor implements StorageSensorInterface {

	
	public boolean TestIntakeSensor;
	public boolean TestIndexSensor;
	public boolean TestShootSensor;
	
	
	@Override
	public boolean getIntakeSensor() {
		// TODO Auto-generated method stub
		return TestIntakeSensor;
	}

	@Override
	public boolean getIndexSensor() {
		// TODO Auto-generated method stub
		return TestIndexSensor;
	}

	@Override
	public boolean getShootSensor() {
		// TODO Auto-generated method stub
		return TestShootSensor;
	}
}
