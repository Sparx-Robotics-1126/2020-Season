package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.drives.DrivesSensorInterface;
import frc.robot.IO;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;
import frc.shooter.commands.LimelightTurret;

public class Shooter extends Subsystem{


	private ShooterCommand shooterCommand;
	private ShooterCommand turretCommand;
	private DrivesSensorInterface driveSensors;
	private ShooterSensorsInterfeace shooterSensors;
	private boolean readyToShoot;
	
	private TalonSRX turretMotor;
	
	public Shooter(DrivesSensorInterface driveSensors, ShooterSensorsInterfeace shooterSensors) {
		this.driveSensors = driveSensors;
		this.shooterSensors = shooterSensors;
		shooterCommand = null;
		
		turretMotor = new TalonSRX(IO.SHOOTER_TURRET_MOTOR);
	}
	
	@Override
	void execute() {
		if(shooterCommand != null) {
			//TURRET
			ShooterOutput turretOutput = turretCommand.execute();
			turretMotor.set(ControlMode.PercentOutput, turretOutput.getOutputValue());
			
			//SHOOTER
			ShooterOutput shooterOutput = shooterCommand.execute();
			
			readyToShoot = shooterOutput.isReadyToShoot() && turretOutput.isReadyToShoot();
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
