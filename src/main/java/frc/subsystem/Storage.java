package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.IO;
import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;
import frc.storage.command.PrepareForShooting;

public class Storage extends Subsystem{

	private StorageCommand storageCommand;
	private StorageSensorInterface sensors;
	
	private short numOfBallsAquired;
	
	private TalonSRX primaryBeltMotor;
	private TalonSRX secondaryBeltMotor;
	
	private static final double STORAGE_MAX_VOLTAGE = 12.0;
	
	public Storage() {
		sensors = null;
		primaryBeltMotor = new TalonSRX(IO.STORAGE_MOTOR_1);
		primaryBeltMotor.setInverted(true);
		secondaryBeltMotor = new TalonSRX(IO.STORAGE_MOTOR_2);
		configureMotor(primaryBeltMotor);
		configureMotor(secondaryBeltMotor);
	}
	
	private static void configureMotor(TalonSRX motor) {
		motor.configVoltageCompSaturation(STORAGE_MAX_VOLTAGE);
		motor.enableVoltageCompensation(true);
	}
	
	@Override
	void execute() {
		if(storageCommand != null) {
			StorageOutput output = storageCommand.execute();
			numOfBallsAquired = output.getNumOfBallsAquired();
			//Set Motor Values
			primaryBeltMotor.set(ControlMode.PercentOutput, output.getPrimaryOutput());
			secondaryBeltMotor.set(ControlMode.PercentOutput, output.getSecondaryOutput());
			if(output.isCommandFinished()) {
				//TURN OFF MOTORS
				primaryBeltMotor.set(ControlMode.PercentOutput, 0);
				secondaryBeltMotor.set(ControlMode.PercentOutput, 0);
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