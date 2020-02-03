package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.drives.DrivesSensorInterface;
import frc.robot.IO;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class Shooter extends Subsystem{


	private ShooterCommand shooterCommand;
	private ShooterCommand turretCommand;
	private DrivesSensorInterface driveSensors;
	private ShooterSensorsInterfeace shooterSensors;
	private boolean readyToShoot;
	private TalonSRX FlywheelMotorAlpha;
	private TalonSRX FlywheelMotorBeta;
	
	public Shooter(DrivesSensorInterface driveSensors, ShooterSensorsInterfeace shooterSensors) {
		this.driveSensors = driveSensors;
		this.shooterSensors = shooterSensors;
		shooterCommand = null;
		FlywheelMotorAlpha = new TalonSRX(IO.LEFT_FLYWHEEL_1);
		FlywheelMotorBeta = new TalonSRX(IO.RIGHT_FLYWHEEL_1);
	}
	
	@Override
	void execute() {
		if(shooterCommand != null) {
			ShooterOutput shooterOutput = shooterCommand.execute();
			ShooterOutput turretOutput = turretCommand.execute();
			readyToShoot = shooterOutput.isReadyToShoot() && turretOutput.isReadyToShoot();
 			FlywheelMotorAlpha.set(ControlMode.PercentOutput, shooterOutput.getOutputValue());
 			FlywheelMotorBeta.set(ControlMode.PercentOutput, shooterOutput.getOutputValue());
		} 
	}

	@Override
	public boolean isDone() {
		return readyToShoot || shooterCommand == null;
	}

}
