package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.IO;
import frc.shooter.ShooterSensors;
import frc.storage.ShootBall;
import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;
import frc.storage.StorageSensors;

public class Storage extends Subsystem{

	private StorageCommand storageCommand;
	private StorageSensorInterface sensors;
	
	private short numOfBallsAquired;
	
	private TalonSRX primaryBeltMotor;
	private TalonSRX secondaryBeltMotor;
	
	private static final double STORAGE_MAX_VOLTAGE = 12.0;
	
	public Storage() {
		numOfBallsAquired = 3; //START WITH 3 in AUTO
		sensors = new StorageSensors();
		primaryBeltMotor = new TalonSRX(IO.STORAGE_MOTOR_1);
		primaryBeltMotor.setInverted(false);
		secondaryBeltMotor = new TalonSRX(IO.STORAGE_MOTOR_2);
		secondaryBeltMotor.setInverted(true);
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

	public void shoot(){
		storageCommand = new ShootBall(sensors, numOfBallsAquired, 1.0);
	}

}