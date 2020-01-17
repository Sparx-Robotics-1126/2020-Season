package frc.subsystem;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;

public class Storage extends Subsystem{

	private StorageCommand storageCommand;
	private StorageSensorInterface sensors;
	
	private short numOfBallsAquired;
	
	public Storage() {
		this.sensors = null;
	}
	
	@Override
	void execute() {
		if(storageCommand != null) {
			StorageOutput output = storageCommand.execute();
			numOfBallsAquired = output.getNumOfBallsAquired();
			//Set Motor Values
			storageCommand = null;
		}
	}

	@Override
	public boolean isDone() {
		return storageCommand == null;
	}
	
	public short getNumberOfBallsAquired() {
		return numOfBallsAquired;
	}

}
