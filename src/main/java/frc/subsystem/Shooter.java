package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;
import frc.shooter.commands.LimelightTurret;

public class Shooter extends Subsystem{


	private ShooterCommand shooterCommand;
	private DrivesSensorInterface driveSensors;
	private ShooterSensorsInterfeace shooterSensors;
	private TalonSRX turretMotor;

	
	public Shooter(DrivesSensorInterface driveSensors, ShooterSensorsInterfeace shooterSensors) {
		this.driveSensors = driveSensors;
		this.shooterSensors = shooterSensors;
		shooterCommand = null;
		
		turretMotor = new TalonSRX(8);
	}
	
	@Override
	void execute() {
		if(shooterCommand != null) {
			ShooterOutput output = shooterCommand.execute();
			turretMotor.set(ControlMode.PercentOutput,output.getShooterAngle());
		}
	}


	@Override
	public boolean isDone() {
		return shooterCommand == null;
	}

	public void startLimelightAiming(){
		shooterCommand = new LimelightTurret(shooterSensors, driveSensors);
	} 

}
