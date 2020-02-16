
package frc.storage;
import frc.storage.StorageCommand;


public class ShootBall extends StorageCommand {
	private short numBalls;
	private double outPutValue;
	private boolean oldShootSensor;
	
	public ShootBall(StorageSensorInterface sensors, short numballs, double outputValue) {
		super(sensors);
		this.numBalls = numballs;
		this.oldShootSensor = false;
		this.outPutValue = outputValue;
	}
	
	 public StorageOutput execute() {
		 StorageOutput returnValue;
		 
		 if (this.oldShootSensor == false) {
		 	if (sensors.getShootSensor() == true) {
		 		this.oldShootSensor = true;
		 		returnValue = new StorageOutput(this.outPutValue, this.numBalls);
		 	}
		 	else {
		 		returnValue = new StorageOutput(this.outPutValue, this.numBalls);
		 	}
		 }
		 else {
			 if(sensors.getShootSensor() == true) {
				 returnValue = new StorageOutput(this.outPutValue, this.numBalls);
			 }
			 else {
				 returnValue = new StorageOutput(0,--this.numBalls, true);
				 this.oldShootSensor = false;
			 }
		 }
		 return returnValue;
	 }
}