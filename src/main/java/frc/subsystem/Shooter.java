package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.drives.DrivesSensorInterface;
import frc.robot.IO;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensors;
import frc.shooter.ShooterSensorsInterfeace;

public class Shooter extends Subsystem{


	private ShooterCommand shooterCommand;
	private ShooterCommand turretCommand;
	private DrivesSensorInterface driveSensors;
	private ShooterSensorsInterfeace shooterSensors;
	private boolean readyToShoot;
	private TalonSRX FlywheelMotorAlpha;
	private TalonSRX FlywheelMotorBeta;
	
	public Shooter(DrivesSensorInterface driveSensors) {
		this.driveSensors = driveSensors;
		this.shooterSensors = new ShooterSensors();;
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
		FlywheelMotorAlpha.set(ControlMode.PercentOutput, -0.64);
		FlywheelMotorBeta.set(ControlMode.PercentOutput, 0.64);
		System.out.println(shooterSensors.getShooterAngleToRobot() + " angle");
		System.out.println(shooterSensors.getShooterSpeed() + " speed");
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			//TODO: handle exception
		}
		//System.out.println(shooterSensors.getShooterAngleToRobot() + " angle");
		SmartDashboard.putNumber("shooter angle", shooterSensors.getShooterAngleToRobot());
		
	
	}

	@Override
	public boolean isDone() {
		return readyToShoot || shooterCommand == null;
	}

}
