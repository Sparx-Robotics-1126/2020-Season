package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.drives.DrivesSensorInterface;
import frc.robot.IO;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensors;
import frc.shooter.ShooterSensorsInterfeace;
import frc.shooter.commands.ScanForTarget;
import frc.shooter.commands.ScannerTarget;
import frc.shooter.commands.ShooterSpeed;
import frc.shooter.commands.StopShooter;
import frc.shooter.commands.TestFlywheel;
import frc.shooter.commands.CenterTurretCommand;
import frc.shooter.commands.LimelightTurret;

public class Shooter extends Subsystem{


	private ShooterCommand shooterCommand;
	private ShooterCommand turretCommand;
	private DrivesSensorInterface driveSensors;
	private ShooterSensorsInterfeace shooterSensors;
	private boolean readyToShoot;
	private TalonSRX flywheelMotorAlpha;
	private TalonSRX turretMotor;
	
	private final double KF = .108;
	private final double KP = 	.9;
	private final double KI  = 	0;
	private final double KD = 	0;

	public Shooter(DrivesSensorInterface driveSensors) {		
		this.driveSensors = driveSensors;
		flywheelMotorAlpha = new TalonSRX(IO.SHOOTER_FLYWHEEL_1);
		
		TalonSRX flywheelMotorBeta = new TalonSRX(IO.SHOOTER_FLYWHEEL_2);
		flywheelMotorBeta.set(ControlMode.Follower,flywheelMotorAlpha.getDeviceID());
		flywheelMotorBeta.setInverted(true);

		flywheelMotorAlpha.configFactoryDefault();
		flywheelMotorAlpha.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,30);
		
		flywheelMotorAlpha.setSensorPhase(true);
		flywheelMotorAlpha.configNominalOutputForward(0);
		flywheelMotorAlpha.configNominalOutputReverse(0);
		flywheelMotorAlpha.configPeakOutputForward(1);
		flywheelMotorAlpha.configPeakOutputReverse(0);
		
		flywheelMotorAlpha.config_kF(0,KF,30);
		flywheelMotorAlpha.config_kP(0,KP,30);
		flywheelMotorAlpha.config_kI(0,KI,30);
		flywheelMotorAlpha.config_kD(0,KD,30);

		this.shooterSensors = new ShooterSensors(flywheelMotorAlpha);
		shooterSensors.enableLimelight(false);

		turretMotor = new TalonSRX(IO.SHOOTER_TURRET_MOTOR);
		
		shooterCommand = null;
		turretCommand = null;
	}
	
	@Override
	void execute() {
		if(shooterCommand != null && turretCommand != null) {
			ShooterOutput shooterOutput = shooterCommand.execute();
			ShooterOutput turretOutput = turretCommand.execute();
			readyToShoot = shooterOutput.isReadyToShoot() && turretOutput.isReadyToShoot();
			SmartDashboard.putBoolean("Ready to shoot: Shooter", shooterOutput.isReadyToShoot());
			SmartDashboard.putBoolean("Ready to shoot: Turret", turretOutput.isReadyToShoot());
			SmartDashboard.putBoolean("Ready to shoot", readyToShoot);
			flywheelMotorAlpha.set(ControlMode.Velocity, (1024/10.0)*shooterOutput.getOutputValue());
			turretMotor.set(ControlMode.PercentOutput, turretOutput.getOutputValue());
			if(turretOutput.isCommandComplete()) {
				shooterCommand = null;
				turretCommand = null;
				flywheelMotorAlpha.set(ControlMode.PercentOutput, 0);
				turretMotor.set(ControlMode.PercentOutput, 0);
				shooterSensors.enableLimelight(false);
			}
		}
		SmartDashboard.putNumber("Current Shooter Speed", shooterSensors.getShooterSpeed());
	} 

	@Override
	public boolean isDone() {
		return shooterCommand == null && turretCommand == null;
	}
	
	public boolean isReadyToShoot() {
		return readyToShoot;
	}

	public void startLimelightAiming(){
		shooterSensors.enableLimelight(true);
		shooterCommand = new ShooterSpeed(shooterSensors,driveSensors);
		turretCommand = new ScannerTarget(shooterSensors, driveSensors,new ScanForTarget(shooterSensors, driveSensors),new LimelightTurret(shooterSensors,driveSensors));
	} 

	public void stopShootingMotors() {
		shooterCommand = new TestFlywheel(shooterSensors, driveSensors);
		turretCommand = new StopShooter(shooterSensors, driveSensors);
	}

	public void centerTurret() {
		shooterCommand = new TestFlywheel(shooterSensors, driveSensors);
		turretCommand = new CenterTurretCommand(shooterSensors, driveSensors);
	}

	public void testShooter(){
		shooterCommand = new TestFlywheel(shooterSensors, driveSensors);
		turretCommand = new ScanForTarget(shooterSensors, driveSensors);
	}

}
