package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.IO;
import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;

public class Storage extends Subsystem{

	private StorageCommand storageCommand;
	private StorageSensorInterface sensors;
	
	private short numOfBallsAquired;
	
	private TalonSRX motorMaster;
	
	private static final double STORAGE_MAX_VOLTAGE = 12.0;
	
	public Storage() {
		sensors = null;
		motorMaster = new TalonSRX(IO.RIGHT_MOTOR_1);
		TalonSRX motorSlave = new TalonSRX(IO.LEFT_MOTOR_1);
		motorSlave.setInverted(true);
		configureMotor(motorMaster, motorSlave);
		
		
		this.sensors = null;
		
	}
	private static void configureMotor(TalonSRX master, TalonSRX slave) {
		int masterId = master.getDeviceID();
		master.set(ControlMode.PercentOutput, 0);
		master.configVoltageCompSaturation(STORAGE_MAX_VOLTAGE);
		master.enableVoltageCompensation(true);
		slave.set(ControlMode.Follower, masterId);
	}
	
	@Override
	void execute() {
		if(storageCommand != null) {
			StorageOutput output = storageCommand.execute();
			numOfBallsAquired = output.getNumOfBallsAquired();
			//Set Motor Values
			motorMaster.set(ControlMode.PercentOutput, output.getOutput());
			if(output.isCommandFinished()) {
				//TURN OFF MOTORS
				motorMaster.set(ControlMode.PercentOutput, 0);
				storageCommand = null;
			}
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