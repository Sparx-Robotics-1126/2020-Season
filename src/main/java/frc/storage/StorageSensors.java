package frc.storage;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.IO;

public class StorageSensors implements StorageSensorInterface {

    private DigitalInput irRoller = new DigitalInput(IO.STORAGE_IR_ROLLER);
    private DigitalInput irStorageTop = new DigitalInput(IO.STORAGE_IR_TOP);
    private DigitalInput irStorageBottom = new DigitalInput(IO.STORAGE_IR_BOTTOM);
	

	@Override
	public boolean getIntakeSensor() {
        return irRoller.get();
	}

	@Override
	public boolean getIndexSensor() {
		return irStorageBottom.get();
	}

	@Override
	public boolean getShootSensor() {
		return irStorageTop.get();
	}

}