package frc.storage;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.IO;

public class StorageSensors implements StorageSensorInterface {

    private DigitalInput intake = new DigitalInput(IO.STORAGE_INTAKE);
    private DigitalInput index = new DigitalInput(IO.STORAGE_INDEX);
    private DigitalInput shoot = new DigitalInput(IO.STORAGE_SHOOTING);
	

	@Override
	public boolean getIntakeSensor() {
        return !intake.get();
	}

	@Override
	public boolean getIndexSensor() {
		return !index.get();
	}

	@Override
	public boolean getShootSensor() {
		return !shoot.get();
	}

}