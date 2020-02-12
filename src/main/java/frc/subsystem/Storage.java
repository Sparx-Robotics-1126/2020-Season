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
	
	private TalonSRX motor1;
	private TalonSRX motor2;
	
	private static final double STORAGE_MAX_VOLTAGE = 12.0;
	
	public Storage() {
		sensors = null;
		motor1 = new TalonSRX(IO.STORAGE_MOTOR_1);
		motor2 = new TalonSRX(IO.STORAGE_MOTOR_2);
		motor2.setInverted(true);
		configureMotor(motor1, motor2);
		motor1 = motor2;
		
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
			motor1.set(ControlMode.PercentOutput, -output.getOutput());
			if(output.isCommandFinished()) {
				//TURN OFF MOTORS
				motor1.set(ControlMode.PercentOutput, 0);
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