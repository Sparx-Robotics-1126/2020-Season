package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.drives.DrivesSensorInterface;
import frc.robot.IO;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensors;
import frc.shooter.ShooterSensorsInterfeace;
import frc.shooter.command.ScanForTarget;
import frc.shooter.command.TestFlywheel;

public class Shooter extends Subsystem{


	private ShooterCommand shooterCommand;
	private ShooterCommand turretCommand;
	private DrivesSensorInterface driveSensors;
	private ShooterSensorsInterfeace shooterSensors;
	private boolean readyToShoot;
	private TalonSRX FlywheelMotorAlpha;
	private TalonSRX FlywheelMotorBeta;
	private TalonSRX TurretMotor;

	
	public Shooter(DrivesSensorInterface driveSensors) {
		this.driveSensors = driveSensors;
		this.shooterSensors = new ShooterSensors();
		shooterCommand = null;
		turretCommand = null;
		FlywheelMotorAlpha = new TalonSRX(IO.LEFT_FLYWHEEL_1);
		FlywheelMotorBeta = new TalonSRX(IO.RIGHT_FLYWHEEL_1);
		TurretMotor = new TalonSRX(IO.TURRET_MOTOR);
	}
	
	@Override
	void execute() {
		if(shooterCommand != null) {
			ShooterOutput shooterOutput = shooterCommand.execute();
			ShooterOutput turretOutput = turretCommand.execute();
			readyToShoot = shooterOutput.isReadyToShoot();// && turretOutput.isReadyToShoot();
 			FlywheelMotorAlpha.set(ControlMode.PercentOutput, -shooterOutput.getOutputValue());
			 FlywheelMotorBeta.set(ControlMode.PercentOutput, shooterOutput.getOutputValue());
			 TurretMotor.set(ControlMode.PercentOutput, turretOutput.getOutputValue());
		} 

	} 

	@Override
	public boolean isDone() {
		return readyToShoot || shooterCommand == null;
	}

}
