package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.drives.DrivesSensorInterface;
import frc.robot.IO;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensors;
import frc.shooter.ShooterSensorsInterfeace;
import frc.shooter.commands.LimelightTurret;

public class Shooter extends Subsystem{


	private ShooterCommand shooterCommand;
	private ShooterCommand turretCommand;
	private DrivesSensorInterface driveSensors;
	private ShooterSensorsInterfeace shooterSensors;
	private boolean readyToShoot;
	private TalonSRX flywheelMotorAlpha;
	private TalonSRX flywheelMotorBeta;
	private TalonSRX turretMotor;

	
	public Shooter(DrivesSensorInterface driveSensors) {
		this.driveSensors = driveSensors;
		this.shooterSensors = new ShooterSensors();
		shooterCommand = null;
		turretCommand = null;
		flywheelMotorAlpha = new TalonSRX(IO.SHOOTER_FLYWHEEL_1);
		flywheelMotorBeta = new TalonSRX(IO.SHOOTER_FLYWHEEL_2);
		turretMotor = new TalonSRX(IO.SHOOTER_TURRET_MOTOR);
	}
	
	@Override
	void execute() {
		if(shooterCommand != null) {
			ShooterOutput shooterOutput = shooterCommand.execute();
			ShooterOutput turretOutput = turretCommand.execute();
			readyToShoot = shooterOutput.isReadyToShoot() && turretOutput.isReadyToShoot();
 			flywheelMotorAlpha.set(ControlMode.PercentOutput, -shooterOutput.getOutputValue());
			flywheelMotorBeta.set(ControlMode.PercentOutput, shooterOutput.getOutputValue());
			turretMotor.set(ControlMode.PercentOutput, turretOutput.getOutputValue());
		} 

	} 


	@Override
	public boolean isDone() {
		return readyToShoot || shooterCommand == null;
	}

	public void startLimelightAiming(){
		shooterCommand = new LimelightTurret(shooterSensors, driveSensors);
	} 

}
